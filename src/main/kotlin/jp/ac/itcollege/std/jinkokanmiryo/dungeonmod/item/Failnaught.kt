package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.init.Enchantments
import net.minecraft.init.Items
import net.minecraft.init.SoundEvents
import net.minecraft.item.*
import net.minecraft.stats.StatList
import net.minecraft.util.*
import net.minecraft.world.World


object Failnaught : ItemBow() {
    init {
        this.maxStackSize = 1
        this.unlocalizedName = "failnaught"
        this.maxDamage = 500
        this.registryName = ResourceLocation(DungeonMod.ID, "Failnaught")
        this.addPropertyOverride(ResourceLocation("pull")) { stack, worldIn, entityIn ->
            if (entityIn == null) {
                0.0f
            } else {
                if (entityIn.activeItemStack.item !== Failnaught)
                    0.0f else (stack.maxItemUseDuration - entityIn.itemInUseCount).toFloat() / 20.0f
            }
        }
        this.addPropertyOverride(ResourceLocation("pulling"))
        { stack, worldIn, entityIn -> if (entityIn != null && entityIn.isHandActive && entityIn.activeItemStack == stack) 1.0f else 0.0f }

    }

    override fun onPlayerStoppedUsing(stack: ItemStack, worldIn: World, entityLiving: EntityLivingBase, timeLeft: Int) {
        if (entityLiving is EntityPlayer) {
            val flag = entityLiving.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0
            var itemstack = this.findAmmo(entityLiving)

            var i = this.getMaxItemUseDuration(stack) - timeLeft
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityLiving, i, !itemstack.isEmpty || flag)
            if (i < 0) return

            if (!itemstack.isEmpty || flag) {
                if (itemstack.isEmpty) {
                    itemstack = ItemStack(Items.ARROW)
                }

                val f = getArrowVelocity(i)

                if (f.toDouble() >= 0.1) {
                    val flag1 = entityLiving.capabilities.isCreativeMode || itemstack.item is ItemArrow && (itemstack.item as ItemArrow).isInfinite(itemstack, stack, entityLiving)

                    if (!worldIn.isRemote) {
                        val itemarrow = (if (itemstack.item is ItemArrow) itemstack.item else Items.ARROW) as ItemArrow
                        val entityarrow = itemarrow.createArrow(worldIn, itemstack, entityLiving)
                        entityarrow.shoot(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0f, f * 5.0f, 1.0f)

                        if (f == 1.0f) {
                            entityarrow.isCritical = true
                        }

                        val j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack)

                        if (j > 0) {
                            entityarrow.damage = entityarrow.damage + j.toDouble() * 0.5 + 0.5
                        }

                        val k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack)

                        if (k > 0) {
                            entityarrow.setKnockbackStrength(k)
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            entityarrow.setFire(100)
                        }

                        stack.damageItem(1, entityLiving)

                        if (flag1 || entityLiving.capabilities.isCreativeMode && (itemstack.item === Items.SPECTRAL_ARROW || itemstack.item === Items.TIPPED_ARROW)) {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY
                        }

                        worldIn.spawnEntity(entityarrow)
                    }

                    worldIn.playSound(null as EntityPlayer?, entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (Item.itemRand.nextFloat() * 0.4f + 1.2f) + f * 0.5f)

                    if (!flag1 && !entityLiving.capabilities.isCreativeMode) {
                        itemstack.shrink(1)

                        if (itemstack.isEmpty) {
                            entityLiving.inventory.deleteStack(itemstack)
                        }
                    }

                    entityLiving.addStat(StatList.getObjectUseStats(this)!!)
                }
            }
        }
    }

    private fun findAmmo(player: EntityPlayer): ItemStack {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND)
        } else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND)
        } else {
            for (i in 0 until player.inventory.sizeInventory) {
                val itemstack = player.inventory.getStackInSlot(i)

                if (this.isArrow(itemstack)) {
                    return itemstack
                }
            }

            return ItemStack.EMPTY
        }
    }

    override fun getItemUseAction(stack: ItemStack): EnumAction {
        return EnumAction.BOW
    }

    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        val itemstack = playerIn.getHeldItem(handIn)
        val flag = !this.findAmmo(playerIn).isEmpty

        val ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag)
        if (ret != null) return ret

        if (!playerIn.capabilities.isCreativeMode && !flag) {
            return if (flag) ActionResult(EnumActionResult.PASS, itemstack) else ActionResult(EnumActionResult.FAIL, itemstack)
        } else {
            playerIn.activeHand = handIn
            return ActionResult(EnumActionResult.SUCCESS, itemstack)
        }
    }

}
