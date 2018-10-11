package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = FrostyRod.ID, name = FrostyRod.Name, version = FrostyRod.Version, modLanguage = "kotlin", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
@Mod.EventBusSubscriber
class FrostyRod
{
    companion object
    {
        const val ID = "frostyrod"
        const val Name = "jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.FrostyRod"
        const val Version = "1.0.1"

        private val ctab = object : CreativeTabs("frostyrod")
        {
            override fun getTabIconItem() = ItemStack(Items.STICK)
        }

        @SubscribeEvent @JvmStatic
        fun register(e: RegistryEvent.Register<Item>) {
            e.registry.register(ItemRod.setCreativeTab(ctab))
        }
        @Mod.EventHandler
    fun construction(event: FMLConstructionEvent) {
            MinecraftForge.EVENT_BUS.register(this)
        }
        @SubscribeEvent @JvmStatic
    fun registerModels(e: ModelRegistryEvent) {
            ModelLoader.setCustomModelResourceLocation(ItemRod, 0, ModelResourceLocation(ItemRod.registryName!!,"inventory"))
        }
    }
}