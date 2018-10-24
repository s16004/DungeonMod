package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object SoulEater : Item()
{
    init
    {
        this.maxStackSize = 1
        this.unlocalizedName = "souleater"
        this.registryName = ResourceLocation(DungeonMod.ID, "SoulEater")
    }

    //経験値消費でHP回復
    private const val CostExp = 1
    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {

        val stack = player.getHeldItem(hand)
        if(player.capabilities.isCreativeMode || player.experienceLevel >= CostExp) {

            if(!player.capabilities.isCreativeMode) player.addExperienceLevel(-CostExp)
            player!!.heal(5.0F)
        }

        return ActionResult(EnumActionResult.SUCCESS,stack)
    }

    //攻撃力とか速度とか
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