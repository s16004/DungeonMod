package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMummy extends RenderLiving<EntityLiving> {

    public static final ResourceLocation texture = new ResourceLocation("resources/assets.dungeonmod/textures/mummy.png");

    public RenderMummy (RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super (rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return texture;
    }
}