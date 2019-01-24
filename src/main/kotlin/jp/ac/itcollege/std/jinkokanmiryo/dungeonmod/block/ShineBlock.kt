package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummy
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.Mobs
import net.minecraft.block.Block
import net.minecraft.block.BlockFurnace
import net.minecraft.block.BlockPortal.AXIS
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.world.World
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.util.NonNullList
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.registries.IForgeRegistry
import javax.annotation.Nullable
import net.minecraft.util.DamageSource
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.math.BlockPos
import net.minecraft.pathfinding.PathNodeType
import net.minecraft.world.IBlockAccess
import net.minecraft.util.EnumFacing


object ShineBlock: Block(Material.ROCK) {
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

        //ブロックの明るさ
        this.setLightLevel(1.0F)

        this.setSoundType(SoundType.STONE)

        this.unlocalizedName = "shine_block"
        this.registryName = ResourceLocation(DungeonMod.ID, "shine_block")

        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
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
        registry.register(ShineBlock)
    }

    //落下ダメージ無効
    override fun onFallenUpon(worldIn: World, pos: BlockPos, entityIn: Entity, fallDistance: Float) {
        if (entityIn.isSneaking) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance)
        } else {
            entityIn.fall(fallDistance, 0.0f)
        }
    }

}