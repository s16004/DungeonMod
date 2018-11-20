package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation


object Scorpion_Tail : Item()
{
    init
    {
        this.maxStackSize = 64
        this.unlocalizedName = "scorpion_tail"
        this.registryName = ResourceLocation(DungeonMod.ID, "Scorpion_Tail")
    }
}