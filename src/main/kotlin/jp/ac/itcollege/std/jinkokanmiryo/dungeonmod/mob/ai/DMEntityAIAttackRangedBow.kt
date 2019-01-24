package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.ai

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Failnaught
import net.minecraft.entity.IRangedAttackMob
import net.minecraft.entity.ai.EntityAIAttackRangedBow
import net.minecraft.entity.monster.EntityMob

class DMEntityAIAttackRangedBow<T>(private val entity: T, moveSpeedAmp: Double, attackCooldown: Int, maxAttackDistance: Float)
    : EntityAIAttackRangedBow<T>(entity, moveSpeedAmp, attackCooldown, maxAttackDistance) where T : EntityMob, T : IRangedAttackMob {

    override fun isBowInMainhand(): Boolean {
        return !entity.heldItemMainhand.isEmpty && entity.heldItemMainhand.item == Failnaught
    }
}
