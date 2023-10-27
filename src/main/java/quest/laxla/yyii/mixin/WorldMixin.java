package quest.laxla.yyii.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quest.laxla.yyii.Common;

@SuppressWarnings("UnusedMixin") // DEPRECATED
@Mixin(World.class)
@Deprecated
public abstract class WorldMixin implements HeightLimitView {
	@Shadow
	public abstract BlockState getBlockState(BlockPos pos);

	@Inject(
		method = "getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"),
		cancellable = true
	)
	void yyii$getBlockState(BlockPos pos, @NotNull CallbackInfoReturnable<BlockState> cir) {
		var newY = (pos.getY() - getBottomY()) % getHeight();
		if (newY < 0) newY += getHeight();
		newY += getBottomY();

		Common.INSTANCE.getLogger().debug("{} -> {}", pos.getY(), newY);

		cir.setReturnValue(getBlockState(new BlockPos(pos.getX(), newY, pos.getZ())));
	}

}
