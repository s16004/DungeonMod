package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob

import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

class EntityMummy(worldIn: World) : EntityMob(worldIn) {
    init {
        setSize(0.6f, 1.95f)
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 26.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.23
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 4.0
    }

    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 8
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 1.85f
    }
}