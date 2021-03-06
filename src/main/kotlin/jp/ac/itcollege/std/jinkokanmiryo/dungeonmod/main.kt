package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block.*
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Vanargand
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Soul_of_Grim
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.*
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.Mobs
import net.minecraft.block.Block
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
import net.minecraft.world.gen.structure.template.TemplateManager

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
            e.registry.register(LightBlock.setCreativeTab(ctab))
            e.registry.register(OsareBlock.setCreativeTab(ctab))
            e.registry.register(ShineBlock.setCreativeTab(ctab))
            e.registry.register(BreakBlock.setCreativeTab(ctab))

        }

        @SubscribeEvent
        @JvmStatic
        fun registeritem(e: RegistryEvent.Register<Item>) {
            e.registry.register(Vanargand.setCreativeTab(ctab))
            e.registry.register(Vanargand_Handle.setCreativeTab(ctab))
            e.registry.register(Vanargand_Crystal.setCreativeTab(ctab))
            e.registry.register(SoulEater.setCreativeTab(ctab))
            e.registry.register(Gae_Buaifnech.setCreativeTab(ctab))
            e.registry.register(Soul_of_Grim.setCreativeTab(ctab))
            e.registry.register(Scorpion_Tail.setCreativeTab(ctab))
            e.registry.register(Failnaught.setCreativeTab(ctab))
        }

        @SubscribeEvent
        @JvmStatic
        fun registerEntity(event: RegistryEvent.Register<EntityEntry>) {
            Mobs.registerMobs(event.registry)
        }

        var pyramidBlock: Block? = null
        var damageBlock: Block? = null
        var lightBlock: Block? = null
        var osareBlock: Block? = null
        var shineBlock: Block? = null
        var breakBlock: Block? = null

        // 各種描画関連の登録
        @SubscribeEvent
        @JvmStatic
        @SideOnly(Side.CLIENT)
        fun registerModels(e: ModelRegistryEvent) {
            ModelLoader.setCustomModelResourceLocation(Vanargand, 0, ModelResourceLocation(Vanargand.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Vanargand_Handle, 0, ModelResourceLocation(Vanargand_Handle.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Vanargand_Crystal, 0, ModelResourceLocation(Vanargand_Crystal.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(SoulEater, 0, ModelResourceLocation(SoulEater.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Gae_Buaifnech, 0, ModelResourceLocation(Gae_Buaifnech.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Soul_of_Grim, 0, ModelResourceLocation(Soul_of_Grim.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Scorpion_Tail, 0, ModelResourceLocation(Scorpion_Tail.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Failnaught, 0, ModelResourceLocation(Failnaught.registryName!!, "inventory"))
            Mobs.registerModels()


            //ブロック
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(pyramidBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:pyramid_block_item_model", "inventory"))

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(damageBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:damage_block_item_model", "inventory"))

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:light_block_item_model", "inventory"))

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(osareBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:osare_block_item_model", "inventory"))


            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(shineBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:shine_block_item_model", "inventory"))

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(breakBlock), 0,
                    net.minecraft.client.renderer.block.model.ModelResourceLocation("$MOD_ID:break_block_item_model", "inventory"))

        }

        @Mod.EventHandler
        fun construction(event: FMLConstructionEvent) {
            MinecraftForge.EVENT_BUS.register(this)
        }
    }
}
