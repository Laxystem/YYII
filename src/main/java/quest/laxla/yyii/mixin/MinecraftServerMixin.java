package quest.laxla.yyii.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quest.laxla.yyii.KansasServer;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Inject(method = "loadWorld()V", at = @At("RETURN"))
	void yyii$loadWorld(CallbackInfo ci) {
		KansasServer.Companion.get((MinecraftServer) (Object) this).onWorldLoad();
	}
}
