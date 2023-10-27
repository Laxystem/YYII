package quest.laxla.yyii.internal

import net.minecraft.registry.SimpleRegistry
import org.quiltmc.qsl.base.api.util.InjectedInterface

@Suppress("PropertyName")
@InjectedInterface(SimpleRegistry::class)
interface SimpleRegistryAccessor {

    var `yyii$isFrozen`: Boolean get() = MIXIN()
        set(value) = MIXIN("Tried to assign $value")
}
