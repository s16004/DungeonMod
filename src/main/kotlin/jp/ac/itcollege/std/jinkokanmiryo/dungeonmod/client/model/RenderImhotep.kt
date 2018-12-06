package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model

import net.minecraft.client.model.ModelBiped
import net.minecraft.entity.Entity
import net.minecraft.util.math.MathHelper
import kotlin.math.PI

class ModelImhotep(modelSize: Float, p_i1168_2: Boolean)
    : ModelBiped(modelSize, 0.0f, 64, if (p_i1168_2) 32 else 64) {

    constructor() : this(0.0f, false)
    override fun setRotationAngles(limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scaleFactor: Float, entityIn: Entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn)
        val f = MathHelper.sin(swingProgress * PI.toFloat())
        val f1 = MathHelper.sin((1.0f - (1.0f - swingProgress) * (1.0f - swingProgress)) * PI.toFloat())
        bipedRightArm.rotateAngleZ = 0.0f
        bipedLeftArm.rotateAngleZ = 0.0f
        bipedRightArm.rotateAngleY = -(0.1f - f * 0.6f)
        bipedLeftArm.rotateAngleY = 0.1f - f * 0.6f
        val f2 = -PI.toFloat() / 1.5f
        bipedRightArm.rotateAngleX = f2 - f2
        bipedLeftArm.rotateAngleX = f2 - f2
        bipedRightArm.rotateAngleX += f * 1.2f - f1 * 0.4f
        bipedLeftArm.rotateAngleX += f * 1.2f - f1 * 0.4f
        bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f
        bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067f) * 0.05f
        bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067f) * 0.05f
    }
}