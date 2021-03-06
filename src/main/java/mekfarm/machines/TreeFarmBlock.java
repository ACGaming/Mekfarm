package mekfarm.machines;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.ndrei.teslacorelib.TeslaCoreLib;

/**
 * Created by CF on 2016-10-26.
 */
public class TreeFarmBlock extends BaseOrientedBlock<TreeFarmEntity> {
    public TreeFarmBlock() {
        super("tree_farm", TreeFarmEntity.class);
    }

    @Override
    protected IRecipe getRecipe() {
        return new ShapedOreRecipe(new ItemStack(this, 1),
                "sss", "wcw", "wgw",
                's', "treeSapling", // Blocks.SAPLING,
                'c', TeslaCoreLib.machineCase,
                'w', "plankWood", // Blocks.PLANKS,
                'g', "gearStone" // TeslaCoreLib.geaStone
        );
    }
}
