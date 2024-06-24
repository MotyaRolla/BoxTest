package ru.makotomc.boundingbox.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.makotomc.boundingbox.PlayerEnterBoxCallback;
import ru.makotomc.boundingbox.PlayerEnterBoxEvent;

@Mixin(Entity.class)
public class PlayerMixin {

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    private void onEnterZone(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) (Object) this;
            Vec3d previousVec3d = player.getPos();
            Vec3d futureVec3d = player.getPos().add(movement.x, movement.y, movement.z);

            PlayerEnterBoxEvent event = new PlayerEnterBoxEvent(previousVec3d, futureVec3d, player);
            if (!PlayerEnterBoxCallback.EVENT.invoker().enterBox(event)) {
                player.teleport(previousVec3d.x, previousVec3d.y, previousVec3d.z);
                ci.cancel();
            }
        }
    }
}

