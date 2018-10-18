package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.client.registry.IRenderFactory
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.EntityRegistry

@Mod(modid = Mummy.MOD_ID, version = Mummy.MOD_VERSION)
class Mummy {

    companion object {
        const val MOD_ID = "mummy"
        const val MOD_VERSION = "1.0"
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        EntityRegistry.registerModEntity(ResourceLocation("mummy"), EntityMummy::class.java, "mummy", 0, this, 50, 1, true)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {

        if (event.side.isClient) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMummy::class.java) { manager -> RenderMummy(manager, ModelMummy(), 0.3f) }
        }


        if (event.side.isClient) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMummy::class.java) { manager -> RenderMummy(manager, ModelMummy(), 0.3f) }
        }

    }

}
