package com.izako.hunterx.entities.goals.hanzo;

import com.izako.hunterx.entities.HanzoEntity;
import com.izako.hunterx.items.entities.NeedleEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class DartThrowGoal extends Goal {

	public HanzoEntity hanzo;
	public DartThrowGoal(HanzoEntity hanzo) {
		this.hanzo = hanzo;
	}
	@Override
	public boolean shouldExecute() {
		return this.hanzo.getAttackTarget() != null && this.hanzo.getDistanceSq(this.hanzo.getAttackTarget()) > 4 && this.hanzo.world.getRandom().nextInt(100) > 98&& this.hanzo.ticksExisted % 120 == 0;
	}
	@Override
	public void startExecuting() {
    	NeedleEntity needle = new NeedleEntity(NeedleEntity.TYPE, this.hanzo, this.hanzo.world);
        float offsetX = (float) ((this.hanzo.getAttackTarget().getPosX() - this.hanzo.getPosX()) / 10);
        float offsetY = (float) ((this.hanzo.getAttackTarget().getPosY() - this.hanzo.getPosY()) / 10);
        float offsetZ = (float) ((this.hanzo.getAttackTarget().getPosZ() - this.hanzo.getPosZ()) / 10);

        Vec3d aim = new Vec3d(offsetX,offsetY,offsetZ);
        needle.setPosition(this.hanzo.getPosX() + aim.x, this.hanzo.getPosY() + this.hanzo.getEyeHeight(), this.hanzo.getPosZ() + aim.z);
        needle.setMotion(aim.x *2, aim.y *2, aim.z *2);
       

        needle.paralysisNeedle = true;
        this.hanzo.world.addEntity(needle);

	}

}
