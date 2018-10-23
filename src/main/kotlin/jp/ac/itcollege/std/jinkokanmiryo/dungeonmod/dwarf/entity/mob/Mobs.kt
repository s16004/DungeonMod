package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.dwarf.entity.mob

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.dwarf.client.renderer.entity.RenderMummy
import net.minecraft.entity.EntityLiving
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.common.registry.EntityEntryBuilder
import net.minecraftforge.registries.IForgeRegistry

object Mobs {
    private var id: Int = 1     // mod内部でインクリメントしていればOK

    /**
     * Mob(Entity)を登録するのに必要なEntityEntryを生成する
     */
    private fun createEntry(clazz: Class<out EntityLiving>, name: String, factory: (World) -> EntityLiving,
                            eggBackground: Int, eggSpot: Int): EntityEntry {
        return EntityEntryBuilder.create<EntityLiving>()
                .entity(clazz)
                .id(ResourceLocation(DungeonMod.ID), id++)
                .factory(factory)
                .egg(eggBackground, eggSpot)
                .tracker(80, 3, true)
                .build()
    }

    /**
     * Mobの情報登録
     * SubscribeEventのRegistryEvent.Register<EntityEntry>で呼び出す
     */
    fun registerMobs(registry: IForgeRegistry<EntityEntry>) {
        registry.register(createEntry(EntityMummy::class.java, "mummy", ::EntityMummy, 0xffFFff, 0xffA500))
    }

    /**
     * 描画情報の登録
     * SubscribeEventのModelRegistryEventで呼び出す
     */
    fun registerModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy::class.java) {
            RenderMummy(it)
        }
    }
}