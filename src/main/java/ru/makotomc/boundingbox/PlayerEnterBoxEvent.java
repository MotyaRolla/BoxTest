package ru.makotomc.boundingbox;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class PlayerEnterBoxEvent {
    private Vec3d previousVec3d;
    private Vec3d futureVec3d;
    PlayerEntity player;
    public PlayerEnterBoxEvent(Vec3d previousVec3d, Vec3d futureVec3d, PlayerEntity player){
        this.previousVec3d = previousVec3d;
        this.futureVec3d = futureVec3d;
        this.player = player;
    }
    public boolean isEntering(Box box){
        return !box.contains(previousVec3d) && box.contains(futureVec3d);
    }
}
