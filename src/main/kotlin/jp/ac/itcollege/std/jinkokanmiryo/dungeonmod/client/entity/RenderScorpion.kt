package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelScorpion
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityScorpion
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
open class RenderScorpion<T : EntityScorpion>(renderManagerIn: RenderManager) : RenderLiving<T>(renderManagerIn, ModelScorpion(), 1.0f) {

    init {
        //this.addLayer<EntityLivingBase, LayerSpiderEyes<*>>(LayerSpiderEyes(this))
    }

    override fun getDeathMaxRotation(entityLivingBaseIn: T): Float {
        return 180.0f
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    override fun getEntityTexture(entity: T): ResourceLocation {
        return SPIDER_TEXTURES
    }

    companion object {
        private val SPIDER_TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/entity/scorpion.png")
    }
}