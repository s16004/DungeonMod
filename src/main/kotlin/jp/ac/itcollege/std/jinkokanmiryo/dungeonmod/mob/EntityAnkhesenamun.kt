package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item.Failnaught
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.ai.DMEntityAIAttackRangedBow
import net.minecraft.entity.*
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.entity.projectile.EntityTippedArrow
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.potion.PotionEffect
import net.minecraft.util.math.MathHelper
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.EnumDifficulty
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class EntityAnkhesenamun(worldIn: World) : EntityMob(worldIn), IRangedAttackMob{

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/ankhesenamun")

    init {
        setSize(0.6f, 1.95f)
    }

    override fun entityInit() {
        super.entityInit()
        this.dataManager.register(SWINGING_ARMS, java.lang.Boolean.valueOf(false))
    }

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        this.tasks.addTask(3, EntityAIWander(this, 1.0))
        this.tasks.addTask(4, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(4, EntityAILookIdle(this))
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, true))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))


    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 250.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.26
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 4.0
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).baseValue = 0.1
    }

    override fun setItemStackToSlot(slotIn: EntityEquipmentSlot, stack: ItemStack) {
        super.setItemStackToSlot(slotIn, stack)
        if (!world.isRemote && slotIn.index == 0) {
            setCombatTask()
        }
    }

    override fun setEquipmentBasedOnDifficulty(difficulty: DifficultyInstance) {
        super.setEquipmentBasedOnDifficulty(difficulty)
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack(Failnaught))
    }

    override fun onInitialSpawn(difficulty: DifficultyInstance, livingdata: IEntityLivingData?): IEntityLivingData? {
        val livingdata = super.onInitialSpawn(difficulty, livingdata)
        setEquipmentBasedOnDifficulty(difficulty)
        setEnchantmentBasedOnDifficulty(difficulty)
        setCombatTask()

        return livingdata
    }

    override fun onLivingUpdate() {
        if(this.health <= this.maxHealth / 2) {
            var i = 0

            if (this.world.difficulty == EnumDifficulty.NORMAL) {
                i = 7
            } else if (this.world.difficulty == EnumDifficulty.HARD) {
                i = 15
            }

            if (i > 0) {
                this.addPotionEffect(PotionEffect(MobEffects.STRENGTH, i * 60, 0))
            }
        }
        super.onLivingUpdate()
    }

    private fun setCombatTask() {
        tasks.removeTask(EntityAIAttackMelee(this, 1.0, true))
        tasks.removeTask(DMEntityAIAttackRangedBow(this, 2.0, 20, 15.0F))

        val itemstack = heldItemMainhand
        if (itemstack.item === Failnaught) {
            tasks.addTask(2, DMEntityAIAttackRangedBow(this, 2.0, 20, 15.0F))
        } else {
            tasks.addTask(2, EntityAIAttackMelee(this, 1.0, true))
        }
    }

    override fun attackEntityWithRangedAttack(target: EntityLivingBase, distanceFactor: Float) {
        val entityarrow = this.getArrow(distanceFactor)
        val d0 = target.posX - this.posX
        val d1 = target.entityBoundingBox.minY + (target.height / 3.0f).toDouble() - entityarrow.posY
        val d2 = target.posZ - this.posZ
        val d3 = MathHelper.sqrt(d0 * d0 + d2 * d2).toDouble()
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6f, (14 - this.world.difficulty.difficultyId * 4).toFloat())
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0f, 1.0f / (this.rng.nextFloat() * 0.4f + 0.8f))
        this.world.spawnEntity(entityarrow)
    }

    private fun getArrow(p_190726_1_: Float): EntityArrow {
        val entitytippedarrow = EntityTippedArrow(this.world, this)
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_)
        return entitytippedarrow
    }

    @SideOnly(Side.CLIENT)
    fun isSwingingArms(): Boolean {
        return (this.dataManager.get(SWINGING_ARMS) as Boolean)
    }

    override fun setSwingingArms(swingingArms: Boolean) {
        this.dataManager.set(SWINGING_ARMS, java.lang.Boolean.valueOf(swingingArms))
    }

    companion object {
        private val SWINGING_ARMS = EntityDataManager.createKey(EntityMummyArcher::class.java, DataSerializers.BOOLEAN)
    }

    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 7000
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 1.80f
    }

    public override fun getLootTable(): ResourceLocation? {
        return LOOT_TABLE
    }
}