package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelImhotep
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelMummy
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityImhotep
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummy
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.util.ResourceLocation

class RenderImhotep(renderManager: RenderManager) : RenderBiped<EntityImhotep>(renderManager, ModelImhotep(), 0.5f) {
    companion object {
        val IMHOTEP_TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/entity/imhotep.png")
    }

    val layerBipedArmor = object : LayerBipedArmor(this) {
        override fun initArmor() {
            modelLeggings = ModelImhotep(0.5f, true)
            modelArmor = ModelImhotep(1.0f, true)
        }
    }

    init {
        addLayer(layerBipedArmor)
    }

    override fun getEntityTexture(entity: EntityImhotep): ResourceLocation? {
        return IMHOTEP_TEXTURES
    }
}