package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMummy extends ModelBase {
    ModelRenderer base;

    public ModelMummy() {
        super();
        textureWidth = 32;
        textureHeight = 16;
        base = new ModelRenderer(this,0, 0);
        base.addBox(-4F, 16F, -4F, 8,8,8);
    }

    @Override
    public void render (Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        base.render(f5);
    }
}
