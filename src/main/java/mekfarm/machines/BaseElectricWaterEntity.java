package mekfarm.machines;

import com.google.common.collect.Lists;
import mekfarm.capabilities.ColoredTextLine;
import mekfarm.inventories.SingleFluidTank;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.awt.*;
import java.util.List;

/**
 * Created by CF on 2016-11-26.
 */
public abstract class BaseElectricWaterEntity<CT extends Container, CGT extends GuiContainer> extends BaseElectricEntity<CT, CGT> {
    protected SingleFluidTank fluidTank;

    protected BaseElectricWaterEntity(int typeId, int energyMaxStorage, int inputSlots, int outputSlots, int filterSlots, int tankCapacity, Class<CT> containerClass, Class<CGT> guiContainerClass) {
        super(typeId, energyMaxStorage, inputSlots, outputSlots, filterSlots, containerClass, guiContainerClass);

        this.fluidTank = new SingleFluidTank(tankCapacity);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        EnumFacing machineFacing = this.getWorld().getBlockState(this.getPos())
                .getValue(BaseOrientedBlock.FACING);
        Boolean isFront = (machineFacing == facing);

        if (!isFront && (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        EnumFacing machineFacing = this.getWorld().getBlockState(this.getPos())
                .getValue(BaseOrientedBlock.FACING);
        Boolean isFront = (machineFacing == facing);

        if (!isFront && (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)) {
            return (T) this.fluidTank;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);

        NBTTagCompound fluid = new NBTTagCompound();
        fluid = this.fluidTank.writeToNBT(fluid);
        compound.setTag("fluid", fluid);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("fluid")) {
            this.fluidTank.readFromNBT(compound.getCompoundTag("fluid"));
        }
    }

    protected abstract int getFluidRequiredToWork();

    protected boolean hasEnoughFluid() {
        return (this.fluidTank.getFluidAmount() >= this.getFluidRequiredToWork());
    }

    protected boolean extractWorkFluid() {
        if (!this.hasEnoughFluid()) {
            return false;
        }

        int required = this.getFluidRequiredToWork();
        FluidStack fluid = this.fluidTank.drain(required, true, true);
        return ((fluid != null) && (fluid.amount == required));
    }

    @Override
    public List<ColoredTextLine> getHUDLines() {
        List<ColoredTextLine> list = super.getHUDLines();
        if (list == null) {
            list = Lists.newArrayList();
        }

        if (!this.hasEnoughFluid()) {
            list.add(new ColoredTextLine(new Color(51, 159, 255),
                    new Color(51, 159, 255, 42),
                    "no water")
                    .setTextAlignment(ColoredTextLine.TextAlignment.CENTER));
        }

        return list;
    }

}