package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummyArcher
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.ModelMummyArcher
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.util.ResourceLocation

class RenderMummyArcher(renderManager: RenderManager) : RenderBiped<EntityMummyArcher>(renderManager, ModelMummyArcher(), 0.5f) {
    companion object {
        val MUMMY_TEXTURES = ResourceLocation("${DungeonMod.ID}:textures/mummy.png")
    }

    val layerBipedArmor = object : LayerBipedArmor(this) {
        override fun initArmor() {
            modelLeggings = ModelMummyArcher(0.5f, true)
            modelArmor = ModelMummyArcher(1.0f, true)
        }
    }

    init {
        addLayer(layerBipedArmor)
    }

    override fun getEntityTexture(entity: EntityMummyArcher): ResourceLocation? {
        return MUMMY_TEXTURES
    }
}