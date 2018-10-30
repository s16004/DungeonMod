package jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.block

import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.DungeonMod
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.EntityMummy
import jp.ac.itcollege.std.jinkokanmiryo.dungeonmod.mob.Mobs
import net.minecraft.block.Block
import net.minecraft.block.BlockFurnace
import net.minecraft.block.material.Material
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.world.World
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.util.NonNullList
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.registries.IForgeRegistry
import javax.annotation.Nullable


object PyramidBlock: Block(Material.ROCK) {
    init {
        // クリエイティブタブ
        this.setCreativeTab(CreativeTabs.DECORATIONS)
        // 明るさ 0.0F～1.0F
        this.setLightLevel(1.0f)
        // 採掘したときの固さ。大きいほど採掘が遅い
        this.setHardness(3.0f)
        // 爆発耐性
        this.setResistance(30.0f)
        // デフォルトのStateを設定
        this.setDefaultState(this.blockState.getBaseState())

        this.setBlockUnbreakable()

        this.unlocalizedName = "Pyramid Block"
        this.registryName = ResourceLocation(DungeonMod.ID, "Pyramid Block")

    }

    /**
     * どのクリエイティブタブにこのアイテムを表示するか。<br></br>
     * 1.12では、ここで不必要なTabに出現しないように場合分けしないと、全Tabに出てくるようになってしまった(!!!)<br></br>
     * Itemクラスと違い、BlockにはisInCreativeTabのような判定メソッドが用意されていないので、==で条件分岐する
     *
     * @param tab クリエイティブタブの種類
     * @param subItems アイテムのリスト
     */
    @SideOnly(Side.CLIENT)
    override fun getSubBlocks(tab: CreativeTabs, list: NonNullList<ItemStack>) {
        if (tab === this.getCreativeTabToDisplayOn()) {
            list.add(ItemStack(this, 1, 0))
        }
    }

    /**
     * アイテムとして表示されたときののツールチップの内容を増やす。<br></br>
     * 頭に@SideOnly(Side.CLIENT)のついているものは、サーバーサイドでは読み込めないので要注意！<br></br>
     * これを付け忘れると、マルチプレイのときだけクラッシュしてしまう、よくあるバグModを生み出す
     *
     * @param stack 対象のアイテム
     * @param world 現在のワールド
     * @param tooltip ツールチップの文字列が入ったリスト
     * @param flag f3+Hで拡張ツールチップに切り替えているかどうか
     */
   // @SideOnly(Side.CLIENT)
    //fun addInformation(stack: ItemStack, @Nullable world: World, tooltip: List<String>, advanced: ITooltipFlag) {
    //}

    fun registerBlocks(registry: IForgeRegistry<Block>) {
        registry.register(PyramidBlock)
    }
}