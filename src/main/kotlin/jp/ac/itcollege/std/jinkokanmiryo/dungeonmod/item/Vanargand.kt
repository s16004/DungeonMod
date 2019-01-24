package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block.BreakBlock
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer


//object Vanargand : ItemPickaxe(ToolMaterial.GOLD){
object Vanargand : ItemPickaxe(ToolMaterial.GOLD) {
    init {
        this.maxDamage = 1
        this.maxStackSize = 1
        this.unlocalizedName = "vanargand"
        this.registryName = ResourceLocation(DungeonMod.ID, "Vanargand")
    }

    //置き換えられる
    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {


        worldIn?.let { w ->
            if (worldIn.isRemote) {
                val target = worldIn.getBlockState(pos).block
                if (target != DungeonMod.breakBlock) {
                    return EnumActionResult.SUCCESS
                }
            }
                for (ix in -2..2) {
                    for (iz in -2..2) {
                        for (iy in -1..1) {
                            worldIn.setBlockToAir(pos.add(ix, iy, iz))
                        }
                    }
                }

        }


        return EnumActionResult.SUCCESS
    }

    //置き換えるブロックと爆発威力
    /*
    private fun freeze(player: EntityPlayerMP, w: WorldServer, pos: BlockPos) {


        w.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true,
                pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 20, 0.0, 0.0, 0.0, 0.1)
        w.setBlockState(pos, Blocks.AIR.defaultState, 3)

    }*/


}
