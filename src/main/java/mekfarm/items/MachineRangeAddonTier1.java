package mekfarm.items;

import mekfarm.MekfarmMod;
import mekfarm.machines.ElectricMekfarmMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.ndrei.teslacorelib.TeslaCoreLib;
import net.ndrei.teslacorelib.items.BaseTieredAddon;
import net.ndrei.teslacorelib.tileentities.SidedTileEntity;

/**
 * Created by CF on 2017-04-01.
 */
public class MachineRangeAddonTier1 extends BaseTieredAddon {
    public MachineRangeAddonTier1() {
        super(MekfarmMod.MODID, MekfarmMod.creativeTab, "addon_range_tier1");
    }

    @Override
    protected int getTier() {
        return 1;
    }

    @Override
    protected String getAddonFunction() {
        return "mekfarm.range";
    }

    @Override
    public boolean canBeAddedTo(SidedTileEntity machine) {
        if (!(machine instanceof ElectricMekfarmMachine) || !((ElectricMekfarmMachine)machine).supportsRangeAddons()) {
            return false;
        }

        return super.canBeAddedTo(machine);
    }

    @Override
    protected IRecipe getRecipe() {
        return new ShapedOreRecipe(new ItemStack(this, 1),
                " g ", "rcr", " r ",
                'c', TeslaCoreLib.baseAddon,
                'r', "dustRedstone", // Items.REDSTONE,
                'g', "gearGold" // TeslaCoreLib.gearGold
        );
    }
}