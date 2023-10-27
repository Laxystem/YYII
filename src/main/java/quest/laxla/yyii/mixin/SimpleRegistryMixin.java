package quest.laxla.yyii.mixin;

import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import quest.laxla.yyii.internal.SimpleRegistryAccessor;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin implements SimpleRegistryAccessor {

	@Shadow
	private boolean frozen;

	public boolean getYyii$isFrozen() {
		return frozen;
	}

	public void setYyii$isFrozen(boolean isFrozen) {
		this.frozen = isFrozen;
	}
}
