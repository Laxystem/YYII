package quest.laxla.yyii

import com.mojang.serialization.Lifecycle
import net.minecraft.registry.*
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.world.dimension.DimensionType
import org.quiltmc.loader.api.ModInternal
import xyz.nucleoid.fantasy.Fantasy
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor

/**
 * YYII's custom minecraft server event handler!
 */
@ModInternal
internal class KansasServer private constructor(val server: MinecraftServer) {
    private val worlds = mutableMapOf<ServerWorld, MultiFloorWorld>()
    val wizard: Fantasy = Fantasy.get(server)!!
    val dimensionTypeRegistry = server.registryManager[RegistryKeys.DIMENSION_TYPE]!!

    @ModInternal
    companion object {
        private val kansas = mutableMapOf<MinecraftServer, KansasServer>()
        operator fun get(server: MinecraftServer) = kansas.computeIfAbsent(server) { KansasServer(server) }
        operator fun get(world: ServerWorld) = KansasServer[world.server].getUnsafe(world)

        const val FLOOR_HEIGHT = 128
        const val OPTIMIZED_FLOOR_HEIGHT = 64
        const val FLOOR_DIVISOR = 16

        /**
         * An inlined implementation of [MultiFloorWorld.getFloorIndexAt] for [StandardMultiFloorWorld],
         * allowing tests.
         */
        @Suppress("NOTHING_TO_INLINE")
        internal inline fun getFloorIndexAt(y: Int, bottomY: Int, height: Int): Int {
            val topY = bottomY + height - 1

            return when {
                y < bottomY -> floor((y - bottomY).toDouble() / FLOOR_HEIGHT).toInt()
                y > topY -> ceil((y - topY).toDouble() / FLOOR_HEIGHT).toInt()

                else -> 0
            }
        }

        @Suppress("NOTHING_TO_INLINE")
        internal inline fun getFloorIndexAtOptimized(y: Int, bottomY: Int, height: Int): Int {
            val topY = bottomY + height - 1

            return when {
                y < bottomY -> {
                    val standardY = bottomY - OPTIMIZED_FLOOR_HEIGHT

                    if (y < standardY) floor((y - standardY).toDouble() / FLOOR_HEIGHT).toInt() - 1
                    else -1
                }
                y > topY -> {
                    val standardY = topY + OPTIMIZED_FLOOR_HEIGHT

                    if (y > standardY) ceil((y - standardY).toDouble() / FLOOR_HEIGHT).toInt() + 1
                    else 1
                }

                else -> 0
            }
        }
    }

    fun onWorldLoad() {

    }

    fun register(identifier: Identifier, dimension: DimensionType): RegistryKey<DimensionType> {
        val registry = dimensionTypeRegistry as SimpleRegistry<DimensionType>

        server.playerManager.saveAllPlayerData() // we don't mess with things before saving thank you very much
        val wasFrozen = registry.isFrozen
        registry.isFrozen = false

        val key = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, identifier)
        registry.register(key, dimension, Lifecycle.stable())

        registry.isFrozen = wasFrozen

        // TODO: fix "Ignoring heightmap data for chunk [x, y], size does not match; expected: 32, got: 52"

        return key
    }

    private fun getUnsafe(world: ServerWorld) = worlds.computeIfAbsent(world) {
        if (world.height > FLOOR_HEIGHT) OptimizedMultiFloorWorld(world) else StandardMultiFloorWorld(world)
    }

    operator fun get(world: ServerWorld) = if (world.server === server) getUnsafe(world)
    else error("Encountered a Dorothy situation: Given world belongs to a different server.")

    override fun equals(other: Any?): Boolean = other is KansasServer && this.server === other.server
    override fun hashCode(): Int = server.hashCode()
    override fun toString(): String = server.toString()

    abstract inner class MultiFloorWorld internal constructor(val floorZero: ServerWorld) {
        private val floors: MutableMap<Int, ServerWorld> = mutableMapOf(0 to floorZero)

        val positiveFloorID: String
        val negativeFloorID: String

        init {
            val identifier = floorZero.registryKey.value.flattened

            positiveFloorID = "$identifier/positive"
            negativeFloorID = "$identifier/negative"

            // make sure some things are to allow for further memory optimisations
            if (floorZero.height % FLOOR_DIVISOR != 0 || floorZero.bottomY % FLOOR_DIVISOR != 0) error(
                "Tf is this? Refusing to handle dimension whose height, minimum, or maximum Y cannot be divided by 16 " +
                    "(if you need this limitation to be removed, please refer to YYII's issue tracker. " +
                    "We will be delighted to hear your use-case)"
            )
        }

        val positiveBaseDimensionType: DimensionType? = dimensionTypeRegistry[!positiveFloorID]
        val negativeBaseDimensionType: DimensionType? = dimensionTypeRegistry[!negativeFloorID]

        val hasPositiveFloors get() = positiveBaseDimensionType != null
        val hasNegativeFloors get() = negativeBaseDimensionType != null

        operator fun get(index: Int): ServerWorld? = if (supportsFloor(index)) floors.computeIfAbsent(index) {
            val dimensionID = computeFloorID(index)
            wizard.getOrOpenPersistentWorld(
                dimensionID,
                copy(floorZero).setDimensionType(
                    if (dimensionTypeRegistry.containsId(dimensionID))
                        RegistryKey.of(RegistryKeys.DIMENSION_TYPE, dimensionID)
                    else register(
                        dimensionID,
                        createDimensionType(
                            base = floorZero.dimension,
                            minimumY = floorZero.topY,
                            height = computeFloorHeight(index)
                        )
                    )
                )
            ).asWorld().also {
                worlds[it] = this
            }
        } else null

        fun supportsFloor(index: Int) =
            index == 0 || (index > 0 && hasPositiveFloors) || (index < 0 && hasNegativeFloors)

        fun supportsY(y: Int) = when {
            y < floorZero.bottomY -> hasNegativeFloors
            y >= floorZero.topY -> hasPositiveFloors

            else -> true
        }

        abstract fun getFloorIndexAt(y: Int): Int
        abstract fun computeFloorHeight(index: Int): Int

        fun getFloorAt(y: Int) = this[getFloorIndexAt(y)]

        fun computeFloorID(index: Int): Identifier = when {
            index < 0 -> !"$positiveFloorID/${index.absoluteValue}"
            index > 0 -> !"$negativeFloorID/$index"
            else -> floorZero.registryKey.value!!
        }
    }

    internal inner class StandardMultiFloorWorld(floorZero: ServerWorld) : MultiFloorWorld(floorZero) {
        override fun computeFloorHeight(index: Int) = if (index == 0) floorZero.height else FLOOR_HEIGHT
        override fun getFloorIndexAt(y: Int): Int = Companion.getFloorIndexAt(y, floorZero.bottomY, floorZero.height)
    }

    /**
     * Shrinks zero-adjacent floors to make world-gen and rendering faster at floor zero.
     */
    internal inner class OptimizedMultiFloorWorld internal constructor(floorZero: ServerWorld) : MultiFloorWorld(floorZero) {
        override fun getFloorIndexAt(y: Int): Int = getFloorIndexAtOptimized(y, floorZero.bottomY, floorZero.height)

        override fun computeFloorHeight(index: Int): Int = when (index) {
            0 -> floorZero.height
            1, -1 -> OPTIMIZED_FLOOR_HEIGHT
            else -> FLOOR_HEIGHT
        }
    }

}
