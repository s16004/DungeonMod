package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ModelMummy : ModelBase() {
    internal var base: ModelRenderer

    init {
        textureWidth = 32
        textureHeight = 16
        base = ModelRenderer(this, 0, 0)
        base.addBox(-4f, 16f, -4f, 8, 8, 8)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        base.render(f5)
    }
}