package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item
import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.ibm.icu.util.ULocale.getDisplayName
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.block.material.Material
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemSword
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BossInfo
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.BossInfoServer



object FrostyRod : ItemPickaxe(ToolMaterial.GOLD)
{
    init
    {
        this.maxStackSize = 1
        this.unlocalizedName = "frostyrod"
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

    //攻撃力とか速度とか
    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = HashMultimap.create<String, AttributeModifier>()

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.name,
                    AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",8.0,0))

            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.name,
                    AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.6, 0))
        }

        return multimap
    }
}