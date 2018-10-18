package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import net.minecraft.client.model.ModelBase
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.entity.EntityLiving
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class RenderMummy(rendermanagerIn: RenderManager, modelbaseIn: ModelBase, shadowsizeIn: Float) : RenderLiving<EntityLiving>(rendermanagerIn, modelbaseIn, shadowsizeIn) {

    override fun getEntityTexture(entity: EntityLiving): ResourceLocation? {
        return texture
    }

    companion object {

        val texture = ResourceLocation("resources/assets.dungeonmod/textures/mummy.png")
    }
}