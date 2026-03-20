package net.damqn4etobg.endlessexpansion.screen.menu;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.block.entity.InfuserBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.SlotItemHandler;

public class InfuserMenu extends ModContainerMenu {
    public final InfuserBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private FluidStack fluidStack;

    public InfuserMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv ,inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    public InfuserMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.INFUSER_MENU.get(), id);
        checkContainerSize(inv, 4);
        blockEntity = (InfuserBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        this.BE_INVENTORY_SLOT_COUNT = 4;
        this.fluidStack = blockEntity.getFluidStack();
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler ->{
            this.addSlot(new SlotItemHandler(handler, 0, 26, 18));
            this.addSlot(new SlotItemHandler(handler, 1, 26, 42));
            this.addSlot(new SlotItemHandler(handler, 2, 80, 42));
            this.addSlot(new SlotItemHandler(handler, 3, 134, 42));
        });

        addDataSlots(data);
    }

    public InfuserBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public void setFluid(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }
    public FluidStack getFluidStack() {
        return fluidStack;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.INFUSER.get());
    }
}
