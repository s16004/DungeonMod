package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob


import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.EnumCreatureType
import net.minecraft.entity.IRangedAttackMob
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.inventory.EntityEquipmentSlot




class EntityMummyArcher(worldIn: World) : EntityMob(worldIn), IRangedAttackMob {
    override fun setSwingingArms(swingingArms: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attackEntityWithRangedAttack(target: EntityLivingBase, distanceFactor: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val LOOT_TABLE = ResourceLocation(DungeonMod.ID, "entities/mummy")

    init {
        setSize(0.6f, 1.95f)
        EntityRegistry.addSpawn(EntityMummy::class.java, 20, 1, 4, EnumCreatureType.CREATURE)
    }

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        //this.tasks.addTask(3, EntityAIAttackMelee(this, 1.0, false))
        //this.tasks.addTask(2,EntityAIAttackRangedBow(this, 2.0, 20, 15.0F))
        this.tasks.addTask(6, EntityAIWander(this, 1.0))
        this.tasks.addTask(7, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(7, EntityAILookIdle(this))
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, true))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))
    }


    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 20.0
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.3
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 3.0
    }

    override fun setItemStackToSlot(slotIn: EntityEquipmentSlot, stack: ItemStack) {
        super.setItemStackToSlot(slotIn, stack)
        if (!world.isRemote && slotIn.index == 0) {
            setCombatTask()
        }
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