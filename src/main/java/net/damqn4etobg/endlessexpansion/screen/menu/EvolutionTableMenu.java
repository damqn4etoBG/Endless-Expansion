package net.damqn4etobg.endlessexpansion.screen.menu;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.block.entity.EvolutionTableBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class EvolutionTableMenu extends ModContainerMenu {
  public final EvolutionTableBlockEntity blockEntity;
  private final Level level;
  private final ContainerData data;

  public EvolutionTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
    this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(1));
  }

  public EvolutionTableMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
    super(ModMenuTypes.EVOLUTION_TABLE_MENU.get(), id);
    checkContainerSize(inv, 1);
    blockEntity = (EvolutionTableBlockEntity) entity;
    this.level = inv.player.level();
    this.data = data;
    this.BE_INVENTORY_SLOT_COUNT = 1;
    addPlayerInventory(inv);
    addPlayerHotbar(inv);

    this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER)
        .ifPresent(handler -> this.addSlot(new SlotItemHandler(handler, 0, 80, 73)));

    addDataSlots(data);
  }

  public EvolutionTableBlockEntity getBlockEntity() {
    return this.blockEntity;
  }

  @Override
  public boolean stillValid(Player player) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
        ModBlocks.EVOLUTION_TABLE.get());
  }

  @Override
  protected void addPlayerHotbar(Inventory playerInventory) {
    for (int i = 0; i < 9; ++i) {
      this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144 + 30));
    }
  }

  @Override
  protected void addPlayerInventory(Inventory playerInventory) {
    for (int i = 0; i < 3; ++i) {
      for (int l = 0; l < 9; ++l) {
        this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + 30 + i * 18));
      }
    }
  }
}
