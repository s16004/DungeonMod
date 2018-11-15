package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob


import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.EnumCreatureType
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.world.EnumDifficulty
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.EntityRegistry


class EntityMummy(worldIn: World) : EntityMob(worldIn) {

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/mummy")

    init {
        setSize(0.6f, 1.95f)
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
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 26.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.23
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 4.0
    }

    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 12
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 1.85f
    }

    public override fun getLootTable(): ResourceLocation? {
        return LOOT_TABLE
    }
}