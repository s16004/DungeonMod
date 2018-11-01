package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob


import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.*
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.world.DifficultyInstance


class EntityMummyArcher(worldIn: World) : EntityMob(worldIn), IRangedAttackMob {
    override fun setSwingingArms(swingingArms: Boolean) {
    }

    override fun attackEntityWithRangedAttack(target: EntityLivingBase, distanceFactor: Float) {
        if (!target.isDead) {
            //Ranged.rangedAttack(target, this, distanceFactor)
        }
    }

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/mummy")

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