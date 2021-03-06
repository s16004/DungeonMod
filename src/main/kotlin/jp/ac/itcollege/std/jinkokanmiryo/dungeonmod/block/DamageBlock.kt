package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.DamageSource
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry


object DamageBlock : Block(Material.ROCK) {
    init {
        // クリエイティブタブ
        this.setCreativeTab(CreativeTabs.DECORATIONS)
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)

        // 採掘したときの固さ。大きいほど採掘が遅い
        this.setHardness(3.0f)
        // 爆発耐性
        this.setResistance(30.0f)
        // デフォルトのStateを設定
        this.setDefaultState(this.blockState.getBaseState())

        this.setSoundType(SoundType.STONE)

        //破壊耐性
        this.setBlockUnbreakable()

        this.unlocalizedName = "damage_block"
        this.registryName = ResourceLocation(DungeonMod.ID, "damage_block")

    }


    @SideOnly(Side.CLIENT)
    override fun getSubBlocks(tab: CreativeTabs, list: NonNullList<ItemStack>) {
        if (tab === this.getCreativeTabToDisplayOn()) {
            list.add(ItemStack(this, 1, 0))
        }
    }


    fun registerBlocks(registry: IForgeRegistry<Block>) {
        registry.register(DamageBlock)
    }

    override fun onEntityCollidedWithBlock(worldIn: World, pos: BlockPos, state: IBlockState, entityIn: Entity) {
        entityIn.attackEntityFrom(DamageSource.MAGIC, 1.0f)
    }


    override fun onEntityWalk(worldIn: World, pos: BlockPos, entityIn: Entity) {
        if (!entityIn.isImmuneToFire && entityIn is EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment(entityIn)) {
            entityIn.attackEntityFrom(DamageSource.MAGIC, 1.0f) //DamageSource.MAGIC
        }

        super.onEntityWalk(worldIn, pos, entityIn)
    }


}