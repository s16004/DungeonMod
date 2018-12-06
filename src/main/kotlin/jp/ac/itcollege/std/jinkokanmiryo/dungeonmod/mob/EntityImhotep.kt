package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.FrostyRod
import net.minecraft.entity.IEntityLivingData
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Items
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.BossInfo
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.World
import net.minecraft.world.BossInfoServer

class EntityImhotep(worldIn: World) : EntityMob(worldIn) {

    private val bossInfo = BossInfoServer(displayName, BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/imhotep")

    init {
        setSize(0.6f, 1.95f)
        this.health = this.maxHealth
    }

    override fun onInitialSpawn(difficulty: DifficultyInstance, livingdata: IEntityLivingData?): IEntityLivingData? {
        val livingData = super.onInitialSpawn(difficulty, livingdata)
        setEquipmentBasedOnDifficulty(difficulty)

        return livingData
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
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 250.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.3
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 8.0
    }

    override fun setEquipmentBasedOnDifficulty(difficulty: DifficultyInstance) {
        super.setEquipmentBasedOnDifficulty(difficulty)
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack(FrostyRod))
    }

    override fun updateAITasks() {
        this.bossInfo.percent = this.health / this.maxHealth

    }

    override fun setCustomNameTag(name: String) {
        super.setCustomNameTag(name)
        this.bossInfo.name = this.displayName
    }

    override fun addTrackingPlayer(player: EntityPlayerMP) {
        super.addTrackingPlayer(player)
        this.bossInfo.addPlayer(player)
    }

    override fun removeTrackingPlayer(player: EntityPlayerMP) {
        super.removeTrackingPlayer(player)
        this.bossInfo.removePlayer(player)
    }

    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 10000
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 1.85f
    }

    public override fun getLootTable(): ResourceLocation? {
        return LOOT_TABLE
    }
}