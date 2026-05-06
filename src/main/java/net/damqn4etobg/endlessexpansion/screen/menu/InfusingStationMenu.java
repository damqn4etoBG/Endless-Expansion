package net.damqn4etobg.endlessexpansion.screen.menu;

import net.damqn4etobg.endlessexpansion.block.EndlessBlocks;
import net.damqn4etobg.endlessexpansion.block.entity.InfusingStationBE;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class InfusingStationMenu extends EndlessContainerMenu {
    public final InfusingStationBE be;
    public final Level level;
    public final ContainerData data;

    public InfusingStationMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    public InfusingStationMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(EndlessMenus.INFUSING_STATION_MENU.get(), id);
        checkContainerSize(inv, 4);
        this.be = (InfusingStationBE) entity;
        this.level = inv.player.level();
        this.data = data;
        this.addStandardInventorySlots(inv, 8, 86);

        this.addSlot(new ResourceHandlerSlot(this.be.inventory, this.be.inventory::set, 0, 26, 18));
        this.addSlot(new ResourceHandlerSlot(this.be.inventory, this.be.inventory::set, 1, 26, 42));
        this.addSlot(new ResourceHandlerSlot(this.be.inventory, this.be.inventory::set, 2, 80, 42));
        this.addSlot(new ResourceHandlerSlot(this.be.inventory, this.be.inventory::set, 3, 134, 42));

        addDataSlots(data);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, be.getBlockPos()), player, EndlessBlocks.INFUSING_STATION.get());
    }
}
