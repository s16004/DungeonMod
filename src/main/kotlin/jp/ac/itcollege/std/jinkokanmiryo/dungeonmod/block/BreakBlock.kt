package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.block.Block
import net.minecraft.block.BlockCactus.AGE
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry
import java.util.*


object BreakBlock : Block(Material.ROCK) {
    init {
        // クリエイティブタブ
        this.setCreativeTab(CreativeTabs.DECORATIONS)
        // 採掘したときの固さ。大きいほど採掘が遅い
        this.setHardness(3.0f)
        // 爆発耐性
        this.setResistance(30.0f)
        // デフォルトのStateを設定
        this.setDefaultState(this.blockState.getBaseState())

        //破壊耐性
        this.setBlockUnbreakable()

        this.setSoundType(SoundType.STONE)

        this.unlocalizedName = "break_block"
        this.registryName = ResourceLocation(DungeonMod.ID, "break_block")

        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
    }


    @SideOnly(Side.CLIENT)
    override fun getSubBlocks(tab: CreativeTabs, list: NonNullList<ItemStack>) {
        if (tab === this.getCreativeTabToDisplayOn()) {
            list.add(ItemStack(this, 1, 0))
        }
    }


    // @SideOnly(Side.CLIENT)
    //fun addInformation(stack: ItemStack, @Nullable world: World, tooltip: List<String>, advanced: ITooltipFlag) {
    //}

    fun registerBlocks(registry: IForgeRegistry<Block>) {
        registry.register(BreakBlock)
    }



}