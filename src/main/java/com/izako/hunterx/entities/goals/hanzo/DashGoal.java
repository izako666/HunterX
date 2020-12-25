package com.izako.hunterx.entities.goals.hanzo;

import com.izako.hunterx.entities.HanzoEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class DashGoal extends Goal {
	public HanzoEntity hanzo;
	public DashGoal(HanzoEntity hanzo) {
		this.hanzo = hanzo;
	}
	@Override
	public boolean shouldExecute() {
		return this.hanzo.getAttackTarget() != null && this.hanzo.getDistanceSq(this.hanzo.getAttackTarget()) > 5 && this.hanzo.world.getRandom().nextInt(100) > 93 && this.hanzo.ticksExisted % 80 == 0;
	}
	@Override
	public void startExecuting() {

        float offsetX = (float) ((this.hanzo.getAttackTarget().getPosX() - this.hanzo.getPosX()) / 2);
        float offsetY = (float) ((this.hanzo.getAttackTarget().getPosY() - this.hanzo.getPosY()) / 4);
        float offsetZ = (float) ((this.hanzo.getAttackTarget().getPosZ() - this.hanzo.getPosZ()) / 2);

        Vec3d aim = new Vec3d(offsetX,offsetY,offsetZ);

        this.hanzo.setMotion(this.hanzo.getMotion().add(aim));
	}
}
