package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Mummy.MOD_ID, version = Mummy.MOD_VERSION)
public class Mummy {
    public static final String MOD_ID = "Mummy";
    public static final String MOD_VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EntityRegistry.registerModEntity(new ResourceLocation("mummy"),EntityMummy.class, "mummy", 0, this, 50, 1, true);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        /*
        if (event.getSide().isClient()) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new IRenderFactory<Mummy.EntityMummy>() {
                @Override
                public Render<? super Mummy.EntityMummy> createRenderFor(RenderManager manager) {
                    return new RenderMummy(manager, new ModelMummy(), 0.3f);
                }
            });
        }*/


        if (event.getSide().isClient()) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new IRenderFactory<EntityMummy>() {
                @Override
                public Render<? super EntityMummy> createRenderFor(RenderManager manager) {
                    return new RenderMummy(manager, new ModelMummy(), 0.3f);
                }
            });
        }

    }
}
