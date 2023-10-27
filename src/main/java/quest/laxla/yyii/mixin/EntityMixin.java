package quest.laxla.yyii.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	public abstract World getWorld();

	@Shadow
	public abstract @Nullable Entity moveToWorld(ServerWorld destination);

	@Shadow
	public abstract void limitFallDistance();

	@ModifyExpressionValue(
		method = "checkBlockCollision()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getZ()I",
			ordinal = 0
		)
	)
	int yyii$checkBlockCollision(int original, @Local(ordinal = 1) int y) {
		final var world = getWorld();

		if (world.isOutOfHeightLimit(y)) {
			if (!world.isClient()) {
				// TODO: Move Up/Down in dimension stack
			}

			return Integer.MAX_VALUE;
		} else return original;
	}
}
