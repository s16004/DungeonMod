package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Failnaught
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityAnkhesenamen
import net.minecraft.client.model.ModelBiped
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumHand
import net.minecraft.util.EnumHandSide
import net.minecraft.util.math.MathHelper

class ModelAnkhesenamen(modelSize: Float, p_i1168_2: Boolean)
    : ModelBiped(modelSize, 0.0f, 64, if (p_i1168_2) 32 else 64) {

    constructor() : this(0.0f, false)

    override fun setRotationAngles(limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scaleFactor: Float, entityIn: Entity) {

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn)
        val itemstack = (entityIn as EntityLivingBase).heldItemMainhand
        val archer = entityIn as EntityAnkhesenamen

        if (archer.isSwingingArms() && (itemstack.isEmpty || itemstack.item !== Failnaught)) {
            val f = MathHelper.sin(this.swingProgress * Math.PI.toFloat())
            val f1 = MathHelper.sin((1.0f - (1.0f - this.swingProgress) * (1.0f - this.swingProgress)) * Math.PI.toFloat())
            this.bipedRightArm.rotateAngleZ = 0.0f
            this.bipedLeftArm.rotateAngleZ = 0.0f
            this.bipedRightArm.rotateAngleY = -(0.1f - f * 0.6f)
            this.bipedLeftArm.rotateAngleY = 0.1f - f * 0.6f
            this.bipedRightArm.rotateAngleX = -(Math.PI.toFloat() / 2f)
            this.bipedLeftArm.rotateAngleX = -(Math.PI.toFloat() / 2f)
            this.bipedRightArm.rotateAngleX -= f * 1.2f - f1 * 0.4f
            this.bipedLeftArm.rotateAngleX -= f * 1.2f - f1 * 0.4f
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067f) * 0.05f
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067f) * 0.05f
        }
    }

    override fun setLivingAnimations(entitylivingbaseIn: EntityLivingBase, limbSwing: Float, limbSwingAmount: Float, partialTickTime: Float) {
        this.rightArmPose = ModelBiped.ArmPose.EMPTY
        this.leftArmPose = ModelBiped.ArmPose.EMPTY
        val itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND)

        if (itemstack.item === Failnaught && (entitylivingbaseIn as EntityAnkhesenamen).isSwingingArms()) {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
                this.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW
            } else {
                this.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW
            }
        }

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime)
    }

    override fun postRenderArm(scale: Float, side: EnumHandSide) {
        val f = if (side == EnumHandSide.RIGHT) 1.0f else -1.0f
        val modelrenderer = this.getArmForSide(side)
        modelrenderer.rotationPointX += f
        modelrenderer.postRender(scale)
        modelrenderer.rotationPointX -= f
    }
}