package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity.RenderImhotep
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity.RenderMummy
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity.RenderMummyArcher
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.client.entity.RenderScorpion
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EnumCreatureType
import net.minecraft.init.Biomes
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraft.world.biome.BiomeDesert
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.common.registry.EntityEntryBuilder
import net.minecraftforge.fml.common.registry.ForgeRegistries.BIOMES
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
                .id(ResourceLocation("${DungeonMod.ID}:$name"), id++)
                .name(name)
                .factory(factory)
                .egg(eggBackground, eggSpot)
                .spawn(EnumCreatureType.MONSTER, 200, 1, 25, Biomes.DESERT,Biomes.DESERT_HILLS)
                .tracker(80, 3, true)
                .build()
    }

    private fun createEntry2(clazz: Class<out EntityLiving>, name: String, factory: (World) -> EntityLiving,
                            eggBackground: Int, eggSpot: Int): EntityEntry {
        return EntityEntryBuilder.create<EntityLiving>()
                .entity(clazz)
                .id(ResourceLocation("${DungeonMod.ID}:$name"), id++)
                .name(name)
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
        registry.register(createEntry(EntityMummy::class.java, "mummy", ::EntityMummy, 0xffFFff, 0xffFFff))
        registry.register((createEntry(EntityMummyArcher::class.java, "mummy_archer",::EntityMummyArcher,0xffFFff, 0xffFFff)))
        registry.register(createEntry(EntityScorpion::class.java, "scorpion",::EntityScorpion,0xffFFff, 0xffFFff))
        registry.register(createEntry2(EntityImhotep::class.java, "imhotep",::EntityImhotep,0xffff00, 0xffd700	))
    }

    /**
     * 描画情報の登録
     * SubscribeEventのModelRegistryEventで呼び出す
     */
    fun registerModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy::class.java) {
            RenderMummy(it)
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityMummyArcher::class.java){
            RenderMummyArcher(it)
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityScorpion::class.java) {
            RenderScorpion(it)
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityImhotep::class.java) {
            RenderImhotep(it)
        }
    }
}