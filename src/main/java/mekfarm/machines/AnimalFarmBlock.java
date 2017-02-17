package mekfarm.machines;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.ndrei.teslacorelib.TeslaCoreLib;

/**
 * Created by CF on 2016-10-26.
 */
public class AnimalFarmBlock extends BaseOrientedBlock<AnimalFarmEntity> {
    public AnimalFarmBlock() {
        super("animal_farm", AnimalFarmEntity.class);
    }

    @Override
    protected IRecipe getRecipe() {
        return new ShapedOreRecipe(new ItemStack(this, 1),
                "xyz", "wcw", "wgw",
                'x', Items.WHEAT,
                'y', Items.CARROT,
                'z', Items.WHEAT,
                'c', TeslaCoreLib.machineCase,
                'w', Blocks.PLANKS,
                'g', TeslaCoreLib.gearStone);
    }
}
