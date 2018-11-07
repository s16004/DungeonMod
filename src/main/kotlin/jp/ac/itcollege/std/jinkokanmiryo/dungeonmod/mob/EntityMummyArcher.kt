package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob


import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.*
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.AbstractSkeleton
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.entity.projectile.EntityTippedArrow
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraft.init.Items
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.util.math.MathHelper
import net.minecraft.world.DifficultyInstance


class EntityMummyArcher(worldIn: World) : EntityMob(worldIn), IRangedAttackMob{

    override fun setSwingingArms(swingingArms: Boolean) {
    }

    //override fun attackEntityWithRangedAttack(target: EntityLivingBase, distanceFactor: Float) {
    //}

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/mummy_archer")

    init {
        setSize(0.6f, 1.95f)
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
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 20.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.26
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 3.0
    }

    override fun setItemStackToSlot(slotIn: EntityEquipmentSlot, stack: ItemStack) {
        super.setItemStackToSlot(slotIn, stack)
        if (!world.isRemote && slotIn.index == 0) {
            setCombatTask()
        }
    }

    override fun setEquipmentBasedOnDifficulty(difficulty: DifficultyInstance) {
        super.setEquipmentBasedOnDifficulty(difficulty)
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack(Items.BOW))
    }

    override fun onInitialSpawn(difficulty: DifficultyInstance, livingdata: IEntityLivingData?): IEntityLivingData? {
        val livingdata = super.onInitialSpawn(difficulty, livingdata)
        setEquipmentBasedOnDifficulty(difficulty)
        setEnchantmentBasedOnDifficulty(difficulty)
        setCombatTask()

        return livingdata
    }

    private fun setCombatTask() {
        tasks.removeTask(EntityAIAttackMelee(this, 1.0, true))
        tasks.removeTask(EntityAIAttackRangedBow(this, 2.0, 20, 15.0F))

        val itemstack = heldItemMainhand
        if (itemstack.item === Items.BOW) {
            tasks.addTask(2, EntityAIAttackRangedBow(this, 2.0, 20, 15.0F))
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

    protected fun getArrow(p_190726_1_: Float): EntityArrow {
        val entitytippedarrow = EntityTippedArrow(this.world, this)
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_)
        return entitytippedarrow
    }

    override fun getExperiencePoints(player: EntityPlayer): Int {
        experienceValue = 8
        return super.getExperiencePoints(player)
    }

    override fun getEyeHeight(): Float {
        return 1.85f
    }

    public override fun getLootTable(): ResourceLocation? {
        return LOOT_TABLE
    }
}