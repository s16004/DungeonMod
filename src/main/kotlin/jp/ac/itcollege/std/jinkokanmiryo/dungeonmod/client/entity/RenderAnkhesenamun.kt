package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.model.ModelAnkhesenamun
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityAnkhesenamun
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.util.ResourceLocation

class RenderAnkhesenamun(renderManager: RenderManager) : RenderBiped<EntityAnkhesenamun>(renderManager, ModelAnkhesenamun(), 0.5f) {
    companion object {
        val TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/entity/ankhesenamun.png")
    }

    val layerBipedArmor = object : LayerBipedArmor(this) {
        override fun initArmor() {
            modelLeggings = ModelAnkhesenamun(0.5f, true)
            modelArmor = ModelAnkhesenamun(1.0f, true)
        }
    }

    init {
        addLayer(layerBipedArmor)
    }

    override fun getEntityTexture(entity: EntityAnkhesenamun): ResourceLocation? {
        return TEXTURES
    }
}