package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block.DamageBlock
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block.PyramidBlock
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.FrostyRod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Soul_of_Grim
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.*
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.Mobs
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
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.common.ForgeVersion.MOD_ID



@Mod(modid = DungeonMod.ID, name = DungeonMod.Name, version = DungeonMod.Version, modLanguage = "kotlin", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
@Mod.EventBusSubscriber
class DungeonMod {
    companion object {
        const val ID = "dungeonmod"
        const val Name = "DungeonMod"
        const val Version = "1.0.1"

        private val ctab = object : CreativeTabs("dungeonmod") {
            override fun getTabIconItem() = ItemStack(Items.STICK)
        }

        @SubscribeEvent
        @JvmStatic
        fun registerBlock(e: RegistryEvent.Register<Block>) {
            e.registry.register(PyramidBlock.setCreativeTab(ctab))
            e.registry.register(DamageBlock.setCreativeTab(ctab))
        }

        @SubscribeEvent
        @JvmStatic
        fun registeritem(e: RegistryEvent.Register<Item>) {
            e.registry.register(FrostyRod.setCreativeTab(ctab))
            e.registry.register(SoulEater.setCreativeTab(ctab))
            e.registry.register(Gae_Buaifnech.setCreativeTab(ctab))
            e.registry.register(Soul_of_Grim.setCreativeTab(ctab))
            e.registry.register(Scorpion_Tail.setCreativeTab(ctab))
        }

        @SubscribeEvent
        @JvmStatic
        fun registerEntity(event: RegistryEvent.Register<EntityEntry>) {
            Mobs.registerMobs(event.registry)
        }

        var pyramidBlock: Block? = null
        val damageBlock: Block? = null

        // 各種描画関連の登録
        @SubscribeEvent
        @JvmStatic
        @SideOnly(Side.CLIENT)
        fun registerModels(e: ModelRegistryEvent) {
            ModelLoader.setCustomModelResourceLocation(FrostyRod, 0, ModelResourceLocation(FrostyRod.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(SoulEater, 0, ModelResourceLocation(SoulEater.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Gae_Buaifnech, 0, ModelResourceLocation(Gae_Buaifnech.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Soul_of_Grim, 0, ModelResourceLocation(Soul_of_Grim.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Scorpion_Tail, 0, ModelResourceLocation(Scorpion_Tail.registryName!!, "inventory"))
            Mobs.registerModels()
            //ブロック
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(pyramidBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:pyramid_block_item_model", "inventory"))

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(damageBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:damage_block_item_model", "inventory"))

        }

        @Mod.EventHandler
        fun construction(event: FMLConstructionEvent) {
            MinecraftForge.EVENT_BUS.register(this)
        }
    }
}