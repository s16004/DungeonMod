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


@Mod(modid = SoulEater.ID, name = SoulEater.Name, version = SoulEater.Version, modLanguage = "kotlin", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
@Mod.EventBusSubscriber
//剣追加
class SoulEater
{
    companion object
    {
        const val ID = "souleater"
        const val Name = "SoulEater"
        const val Version = "1.0.1"

        private val ctab = object : CreativeTabs("soulEater")
        {
            override fun getTabIconItem() = ItemStack(Items.DIAMOND_SWORD)
        }

        @SubscribeEvent @JvmStatic
        fun register(e: RegistryEvent.Register<Item>) {
            e.registry.register(ItemSword.setCreativeTab(ctab))
        }

        @Mod.EventHandler
        fun construction(event: FMLConstructionEvent) {
            MinecraftForge.EVENT_BUS.register(this)
        }

        @SubscribeEvent
        @JvmStatic
        fun registerModels(e: ModelRegistryEvent) {
            ModelLoader.setCustomModelResourceLocation(ItemSword, 0, ModelResourceLocation(ItemSword.registryName!!,"inventory"))
        }


    }
}