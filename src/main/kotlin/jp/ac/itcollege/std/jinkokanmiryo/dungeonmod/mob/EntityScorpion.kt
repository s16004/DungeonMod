package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.world.EnumDifficulty
import net.minecraft.world.World


class EntityScorpion(worldIn: World) : EntityMob(worldIn) {

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/scorpion")

    init {
        setSize(0.6f, 0.6f)
    }

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        this.tasks.addTask(3, EntityAIAttackMelee(this, 1.0, false))
        this.tasks.addTask(6, EntityAIWander(this, 1.0))
        this.tasks.addTask(7, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(7, EntityAILookIdle(this))
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, true))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))
    }


    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 15.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.3
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 3.0
    }

    override fun attackEntityAsMob(entityIn: Entity): Boolean {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn is EntityLivingBase) {
                var i = 0

                if (this.world.difficulty == EnumDifficulty.NORMAL) {
                    i = 7
                } else if (this.world.difficulty == EnumDifficulty.HARD) {
                    i = 15
                }

                if (i > 0) {
                    entityIn.addPotionEffect(PotionEffect(MobEffects.POISON, i * 20, 0))
                }
            }

            return true
        } else {
            return false
        }
    }

    override fun isPotionApplicable(potioneffectIn: PotionEffect): Boolean {
        return if (potioneffectIn.potion === MobEffects.POISON) false else super.isPotionApplicable(potioneffectIn)
    }


    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 10
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 0.6f
    }

    public override fun getLootTable(): ResourceLocation? {
        return LOOT_TABLE
    }
}