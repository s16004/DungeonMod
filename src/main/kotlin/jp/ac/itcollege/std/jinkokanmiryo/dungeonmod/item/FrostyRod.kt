package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer

object FrostyRod : Item()
{
    init
    {
        this.maxDamage = 300
        this.maxStackSize = 1
        this.unlocalizedName = "rod"
        this.registryName = ResourceLocation(DungeonMod.ID, "FrostyRod")
    }

    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        worldIn?.let { w ->
            if(!w.isRemote && pos != null)
            {
                val b = w.getBlockState(pos).block
                if(b == Blocks.TALLGRASS || b == Blocks.DIRT || b == Blocks.STONE || b == Blocks.GRASS) player?.let { freeze(it as EntityPlayerMP, w as WorldServer, pos) }
            }
        }

        return EnumActionResult.SUCCESS
    }

    private const val CostExp = 1
    private fun freeze(player: EntityPlayerMP, w: WorldServer, pos: BlockPos)
    {
        if(player.capabilities.isCreativeMode || player.experienceLevel >= CostExp)
        {
            if(!player.capabilities.isCreativeMode) player.addExperienceLevel(-CostExp)
            w.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, true,
                    pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, 20, 0.0, 0.0, 0.0, 0.1)
            w.setBlockState(pos, Blocks.ICE.defaultState, 3)
        }
    }
}