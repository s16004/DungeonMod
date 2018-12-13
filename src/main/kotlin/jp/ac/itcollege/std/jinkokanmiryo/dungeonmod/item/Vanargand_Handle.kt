package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object Vanargand_Handle : Item()
{
    init
    {
        this.maxStackSize = 64
        this.unlocalizedName = "frostyrod_handle"
        this.registryName = ResourceLocation(DungeonMod.ID, "Vanargand_Handle")
    }
}