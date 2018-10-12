package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.SoulEater
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object ItemSword : Item()
{
    init
    {
        this.maxStackSize = 1
        this.unlocalizedName = "sword"
        this.registryName = ResourceLocation(SoulEater.ID, "SoulEater")
    }

    override fun onItemUse(player: EntityPlayer?, worldIn: World?, pos: BlockPos?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        player!!.heal(20.0F)

        return EnumActionResult.SUCCESS
    }

    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = HashMultimap.create<String, AttributeModifier>()

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.name,
                    AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",7.0,0))

            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.name,
                    AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.6, 0))

        }

        return multimap
    }
}