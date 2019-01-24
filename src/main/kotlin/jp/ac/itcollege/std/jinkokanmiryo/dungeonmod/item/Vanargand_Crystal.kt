package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object Vanargand_Crystal : Item()
{
    init
    {
        this.maxStackSize = 64
        this.unlocalizedName = "frostyrod_crystal"
        this.registryName = ResourceLocation(DungeonMod.ID, "Vanargand_Crystal")
    }
}