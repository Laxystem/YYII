@file:JvmName("YyiiUtils")
@file:ModInternal

package quest.laxla.yyii

import net.fabricmc.fabric.api.util.TriState
import net.minecraft.registry.SimpleRegistry
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.world.dimension.DimensionType
import org.quiltmc.loader.api.ModInternal
import quest.laxla.yyii.internal.SimpleRegistryAccessor
import xyz.nucleoid.fantasy.RuntimeWorldConfig
import kotlin.math.absoluteValue

@JvmSynthetic
internal operator fun String.not() = Identifier(Common.NAMESPACE, this)

internal val Identifier.flattened: String
    get() = "$namespace/$path"

@JvmSynthetic
internal infix fun Identifier.flattenedWithIndex(index: Int): Identifier? {
    val descriptor = when {
        index > 0 -> "positive"
        index < 0 -> "negative"
        else -> return null
    }

    return !"${flattened}/$descriptor/${index.absoluteValue}"
}

fun copy(
    world: ServerWorld
): RuntimeWorldConfig = RuntimeWorldConfig().apply {
    seed = world.seed
    generator = world.chunkManager.chunkGenerator
    timeOfDay = world.timeOfDay
    difficulty = world.difficulty
    isFlat = TriState.of(world.isFlat)
}

internal fun createDimensionType(
    base: DimensionType, minimumY: Int, height: Int = base.height
) = DimensionType(
    base.fixedTime,
    base.hasSkyLight,
    base.hasCeiling,
    base.ultraWarm,
    base.natural,
    base.coordinateScale,
    base.bedWorks,
    base.respawnAnchorWorks,
    minimumY,
    height,
    base.logicalHeight.coerceAtMost(height),
    base.infiniburn,
    base.effectsLocation,
    base.ambientLight,
    base.monsterSettings
)

@get:JvmSynthetic
@set:JvmSynthetic
internal var SimpleRegistry<*>.isFrozen
    get() = (this as SimpleRegistryAccessor).`yyii$isFrozen`
    set(value) {
        (this as SimpleRegistryAccessor).`yyii$isFrozen` = value
    }
