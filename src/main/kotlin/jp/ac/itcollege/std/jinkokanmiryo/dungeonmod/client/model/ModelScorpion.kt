package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import net.minecraft.util.math.MathHelper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ModelScorpion : ModelBase() {
    /** The spider's head box  */
    var spiderHead: ModelRenderer
    /** The spider's neck box  */
    var spiderNeck: ModelRenderer
    /** The spider's body box  */
    var spiderBody: ModelRenderer
    /** Spider's first leg  */
    var spiderLeg1: ModelRenderer
    /** Spider's second leg  */
    var spiderLeg2: ModelRenderer
    /** Spider's third leg  */
    var spiderLeg3: ModelRenderer
    /** Spider's fourth leg  */
    var spiderLeg4: ModelRenderer
    /** Spider's fifth leg  */
    var spiderLeg5: ModelRenderer
    /** Spider's sixth leg  */
    var spiderLeg6: ModelRenderer
    /** Spider's seventh leg  */
    var spiderLeg7: ModelRenderer
    /** Spider's eight leg  */
    var spiderLeg8: ModelRenderer

    init {
        val f = 0.0f
        val i = 15
        this.spiderHead = ModelRenderer(this, 32, 4)
        this.spiderHead.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8, 0.0f)
        this.spiderHead.setRotationPoint(0.0f, 15.0f, -3.0f)
        this.spiderNeck = ModelRenderer(this, 0, 0)
        this.spiderNeck.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f)
        this.spiderNeck.setRotationPoint(0.0f, 15.0f, 0.0f)
        this.spiderBody = ModelRenderer(this, 0, 12)
        this.spiderBody.addBox(-5.0f, -7.0f, -6.0f, 10, 8, 12, 0.0f)
        this.spiderBody.setRotationPoint(0.0f, 15.0f, 9.0f)
        this.spiderLeg1 = ModelRenderer(this, 18, 0)
        this.spiderLeg1.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg1.setRotationPoint(-4.0f, 15.0f, 2.0f)
        this.spiderLeg2 = ModelRenderer(this, 18, 0)
        this.spiderLeg2.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg2.setRotationPoint(4.0f, 15.0f, 2.0f)
        this.spiderLeg3 = ModelRenderer(this, 18, 0)
        this.spiderLeg3.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg3.setRotationPoint(-4.0f, 15.0f, 1.0f)
        this.spiderLeg4 = ModelRenderer(this, 18, 0)
        this.spiderLeg4.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg4.setRotationPoint(4.0f, 15.0f, 1.0f)
        this.spiderLeg5 = ModelRenderer(this, 18, 0)
        this.spiderLeg5.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg5.setRotationPoint(-4.0f, 15.0f, 0.0f)
        this.spiderLeg6 = ModelRenderer(this, 18, 0)
        this.spiderLeg6.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg6.setRotationPoint(4.0f, 15.0f, 0.0f)
        this.spiderLeg7 = ModelRenderer(this, 18, 0)
        this.spiderLeg7.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg7.setRotationPoint(-4.0f, 15.0f, -1.0f)
        this.spiderLeg8 = ModelRenderer(this, 18, 0)
        this.spiderLeg8.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2, 0.0f)
        this.spiderLeg8.setRotationPoint(4.0f, 15.0f, -1.0f)
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    override fun render(entityIn: Entity, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scale: Float) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn)
        this.spiderHead.render(scale)
        this.spiderNeck.render(scale)
        this.spiderBody.render(scale)
        this.spiderLeg1.render(scale)
        this.spiderLeg2.render(scale)
        this.spiderLeg3.render(scale)
        this.spiderLeg4.render(scale)
        this.spiderLeg5.render(scale)
        this.spiderLeg6.render(scale)
        this.spiderLeg7.render(scale)
        this.spiderLeg8.render(scale)
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    override fun setRotationAngles(limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scaleFactor: Float, entityIn: Entity) {
        this.spiderHead.rotateAngleY = netHeadYaw * 0.017453292f
        this.spiderHead.rotateAngleX = headPitch * 0.017453292f
        val f = Math.PI.toFloat() / 4f
        this.spiderLeg1.rotateAngleZ = -(Math.PI.toFloat() / 4f)
        this.spiderLeg2.rotateAngleZ = Math.PI.toFloat() / 4f
        this.spiderLeg3.rotateAngleZ = -0.58119464f
        this.spiderLeg4.rotateAngleZ = 0.58119464f
        this.spiderLeg5.rotateAngleZ = -0.58119464f
        this.spiderLeg6.rotateAngleZ = 0.58119464f
        this.spiderLeg7.rotateAngleZ = -(Math.PI.toFloat() / 4f)
        this.spiderLeg8.rotateAngleZ = Math.PI.toFloat() / 4f
        val f1 = -0.0f
        val f2 = 0.3926991f
        this.spiderLeg1.rotateAngleY = Math.PI.toFloat() / 4f
        this.spiderLeg2.rotateAngleY = -(Math.PI.toFloat() / 4f)
        this.spiderLeg3.rotateAngleY = 0.3926991f
        this.spiderLeg4.rotateAngleY = -0.3926991f
        this.spiderLeg5.rotateAngleY = -0.3926991f
        this.spiderLeg6.rotateAngleY = 0.3926991f
        this.spiderLeg7.rotateAngleY = -(Math.PI.toFloat() / 4f)
        this.spiderLeg8.rotateAngleY = Math.PI.toFloat() / 4f
        val f3 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount
        val f4 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + Math.PI.toFloat()) * 0.4f) * limbSwingAmount
        val f5 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + Math.PI.toFloat() / 2f) * 0.4f) * limbSwingAmount
        val f6 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + Math.PI.toFloat() * 3f / 2f) * 0.4f) * limbSwingAmount
        val f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount
        val f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + Math.PI.toFloat()) * 0.4f) * limbSwingAmount
        val f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + Math.PI.toFloat() / 2f) * 0.4f) * limbSwingAmount
        val f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + Math.PI.toFloat() * 3f / 2f) * 0.4f) * limbSwingAmount
        this.spiderLeg1.rotateAngleY += f3
        this.spiderLeg2.rotateAngleY += -f3
        this.spiderLeg3.rotateAngleY += f4
        this.spiderLeg4.rotateAngleY += -f4
        this.spiderLeg5.rotateAngleY += f5
        this.spiderLeg6.rotateAngleY += -f5
        this.spiderLeg7.rotateAngleY += f6
        this.spiderLeg8.rotateAngleY += -f6
        this.spiderLeg1.rotateAngleZ += f7
        this.spiderLeg2.rotateAngleZ += -f7
        this.spiderLeg3.rotateAngleZ += f8
        this.spiderLeg4.rotateAngleZ += -f8
        this.spiderLeg5.rotateAngleZ += f9
        this.spiderLeg6.rotateAngleZ += -f9
        this.spiderLeg7.rotateAngleZ += f10
        this.spiderLeg8.rotateAngleZ += -f10
    }
}