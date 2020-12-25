package com.izako.hunterx.entities.goals.hanzo;

import com.izako.hunterx.entities.HanzoEntity;
import com.izako.hunterx.items.entities.ShurikenEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class ShurikenThrowGoal extends Goal {

	public HanzoEntity hanzo;
	public ShurikenThrowGoal(HanzoEntity hanzo) {
		this.hanzo = hanzo;
	}
	@Override
	public boolean shouldExecute() {
		return this.hanzo.getAttackTarget() != null && this.hanzo.getDistanceSq(this.hanzo.getAttackTarget()) > 4 && this.hanzo.world.getRandom().nextInt(100) > 80&& this.hanzo.ticksExisted % 40 == 0;
	}
	@Override
	public void startExecuting() {
    	ShurikenEntity shuriken = new ShurikenEntity(ShurikenEntity.TYPE, this.hanzo, this.hanzo.world);
        float offsetX = (float) ((this.hanzo.getAttackTarget().getPosX() - this.hanzo.getPosX()) / 10);
        float offsetY = (float) ((this.hanzo.getAttackTarget().getPosY() - this.hanzo.getPosY()) / 10);
        float offsetZ = (float) ((this.hanzo.getAttackTarget().getPosZ() - this.hanzo.getPosZ()) / 10);

        Vec3d aim = new Vec3d(offsetX,offsetY,offsetZ);
        shuriken.setPosition(this.hanzo.getPosX() + aim.x, this.hanzo.getPosY() + this.hanzo.getEyeHeight(), this.hanzo.getPosZ() + aim.z);
    	shuriken.setMotion(aim.x *2, aim.y *2, aim.z *2);
       

        this.hanzo.world.addEntity(shuriken);

	}

}
