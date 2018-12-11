package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemSword
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import sun.audio.AudioPlayer.player

object SoulEater : ItemSword(ToolMaterial.IRON)
{
    init
    {
        this.maxStackSize = 1
        this.unlocalizedName = "souleater"
        this.registryName = ResourceLocation(DungeonMod.ID, "SoulEater")

    }

    /*経験値消費でHP回復
    private const val CostExp = 1
    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {

        val stack = player.getHeldItem(hand)
        if(player.capabilities.isCreativeMode || player.experienceLevel >= CostExp) {

            if(!player.capabilities.isCreativeMode) player.addExperienceLevel(-CostExp)
            player!!.heal(5.0F)
        }

        return ActionResult(EnumActionResult.SUCCESS,stack)
    }
    */

    //hitで回復
    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {

        if (super.hitEntity(stack, target, attacker)) {
            attacker.heal(3.0F)
        }
        return super.hitEntity(stack, target, attacker)
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