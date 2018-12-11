package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block.PyramidBlock
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer


object FrostyRod : ItemPickaxe(ToolMaterial.GOLD) {
    init {
        this.maxDamage = 10
        this.maxStackSize = 1
        this.unlocalizedName = "frostyrod"
        this.registryName = ResourceLocation(DungeonMod.ID, "FrostyRod")
    }

    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        worldIn?.let { w ->
            if (!w.isRemote && pos != null) {
                val b = w.getBlockState(pos).block
                if (b == PyramidBlock) player?.let { freeze(it as EntityPlayerMP, w as WorldServer, pos) }
            }
        }

        return EnumActionResult.SUCCESS
    }

    override fun getContainerItem(item: ItemStack?): ItemStack? {
        if (item != null && item.item === this) {
            item.itemDamage = item.itemDamage + 1
        }
        return item
    }

    private fun freeze(player: EntityPlayerMP, w: WorldServer, pos: BlockPos) {
        w.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true,
                pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 20, 0.0, 0.0, 0.0, 0.1)
        w.setBlockState(pos, Blocks.SNOW.defaultState, 3)
    }
}


