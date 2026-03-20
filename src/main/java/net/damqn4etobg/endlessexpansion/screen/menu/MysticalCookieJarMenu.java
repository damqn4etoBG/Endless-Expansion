package net.damqn4etobg.endlessexpansion.screen.menu;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.block.entity.MysticalCookieJarBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class MysticalCookieJarMenu extends ModContainerMenu {

    public final MysticalCookieJarBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public MysticalCookieJarMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv ,inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(1));
    }

    public MysticalCookieJarMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.MYSTICAL_COOKIE_JAR_MENU.get(), id);
        checkContainerSize(inv, 1);
        blockEntity = (MysticalCookieJarBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        this.BE_INVENTORY_SLOT_COUNT = 1;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler ->{
            this.addSlot(new SlotItemHandler(handler, 0, 80, 42));
        });

        addDataSlots(data);
    }

    public MysticalCookieJarBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.MYSTICAL_COOKIE_JAR.get());
    }
}
