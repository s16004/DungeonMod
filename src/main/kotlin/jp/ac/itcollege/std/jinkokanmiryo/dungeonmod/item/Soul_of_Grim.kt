package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.item

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation


object Soul_of_Grim : Item()
{
    init
    {
        this.maxStackSize = 64
        this.unlocalizedName = "soul_of_grim"
        this.registryName = ResourceLocation(DungeonMod.ID, "Soul_of_Grim")
    }
}