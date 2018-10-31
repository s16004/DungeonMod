package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelMummy
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummy
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.util.ResourceLocation

class RenderMummyArcher(renderManager: RenderManager) : RenderBiped<EntityMummy>(renderManager, ModelMummy(), 0.5f) {
    companion object {
        val MUMMY_TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/mummy.png")
    }

    val layerBipedArmor = object : LayerBipedArmor(this) {
        override fun initArmor() {
            modelLeggings = ModelMummy(0.5f, true)
            modelArmor = ModelMummy(1.0f, true)
        }
    }

    init {
        addLayer(layerBipedArmor)
    }

    override fun getEntityTexture(entity: EntityMummy): ResourceLocation? {
        return MUMMY_TEXTURES
    }
}