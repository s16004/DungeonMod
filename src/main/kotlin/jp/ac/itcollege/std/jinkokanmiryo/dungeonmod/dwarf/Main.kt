package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.dwarf

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.dwarf.entity.mob.Mobs
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object Main {

    // エンティティ(モブ)のデータ追加
    @SubscribeEvent
    fun registerEntities(event: RegistryEvent.Register<EntityEntry>) {
        Mobs.registerMobs(event.registry)
    }

    // 各種描画関連の登録
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        Mobs.registerModels()
    }
}