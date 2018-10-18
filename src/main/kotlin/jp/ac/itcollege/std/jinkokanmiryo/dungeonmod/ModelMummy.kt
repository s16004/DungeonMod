package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ModelMummy : ModelBase() {
    /*
    internal var base: ModelRenderer

    init {
        textureWidth = 32
        textureHeight = 16
        base = ModelRenderer(this, 0, 0)
        base.addBox(-4f, 16f, -4f, 16, 16, 16)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        base.render(f5)
    }
    */
    var bottom: ModelRenderer
    var base: ModelRenderer
    var top: ModelRenderer

    init{
        // テクスチャの縦と横のサイズ
        textureWidth = 32
        textureHeight = 64
        // モデルの形を作る
        base = ModelRenderer(this,0, 14)
        base.addBox(0f, 0f, 0f, 10, 8, 10)

        bottom = ModelRenderer(this,32, 2)
        bottom.addBox(1f, 8f, 1f, 8, 8, 8)

        top = ModelRenderer(this,40, 18)
        top.addBox(2f, 16f, 2f, 6, 8, 6)
    }

    override fun render(entity: Entity?, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        // 描画
        base.render(f5)
        bottom.render(f5)
        top.render(f5)
    }
}