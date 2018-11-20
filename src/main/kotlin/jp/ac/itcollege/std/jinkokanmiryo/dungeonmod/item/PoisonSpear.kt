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
import net.minecraft.util.ResourceLocation
import java.util.UUID



object PoisonSpear : ItemSword(ToolMaterial.IRON) {
    init {
        this.maxStackSize = 1
        this.unlocalizedName = "poisonspear"
        this.registryName = ResourceLocation(DungeonMod.ID, "PoisonSpear")

    }

    val REACH_MODIFIER = UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827")

    //攻撃時毒付与
    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {

        if (super.hitEntity(stack, target, attacker)) {
            target.addPotionEffect(PotionEffect(MobEffects.POISON, 70, 0))
        }
        return super.hitEntity(stack, target, attacker)
    }

    //攻撃力とか速度とか
    override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
        val multimap = HashMultimap.create<String, AttributeModifier>()

        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.name,
                    AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6.0, 0))

            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.name,
                    AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.0, 0))

            multimap.put(EntityPlayer.REACH_DISTANCE.name, AttributeModifier(REACH_MODIFIER, "Weapon modifier", 3.0, 0))
        }

        return multimap
    }
}
