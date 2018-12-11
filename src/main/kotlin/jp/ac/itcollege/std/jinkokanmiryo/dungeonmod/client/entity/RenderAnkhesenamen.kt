package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelAnkhesenamen
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityAnkhesenamen
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummyArcher
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.ModelMummyArcher
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.util.ResourceLocation

class RenderAnkhesenamen(renderManager: RenderManager) : RenderBiped<EntityAnkhesenamen>(renderManager, ModelAnkhesenamen(), 0.5f) {
    companion object {
        val TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/entity/ankhesenamen.png")
    }

    val layerBipedArmor = object : LayerBipedArmor(this) {
        override fun initArmor() {
            modelLeggings = ModelAnkhesenamen(0.5f, true)
            modelArmor = ModelAnkhesenamen(1.0f, true)
        }
    }

    init {
        addLayer(layerBipedArmor)
    }

    override fun getEntityTexture(entity: EntityAnkhesenamen): ResourceLocation? {
        return TEXTURES
    }
}