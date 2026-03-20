package net.damqn4etobg.endlessexpansion.block.entity;

import net.damqn4etobg.endlessexpansion.item.wand.WandItem;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.PlayerWandData;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.screen.menu.EvolutionTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EvolutionTableBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;
    public boolean contentsChanged = false;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                contentsChanged = true;
            }
        }
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(slot == 0 && stack.getItem() instanceof WandItem) {
                return super.isItemValid(slot, stack);
            } else {
                return false;
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public EvolutionTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.EVOLUTION_TABLE.get(), pPos, pBlockState);
        this.data = new ContainerData() {

            @Override
            public int get(int i) {
                return 0;
            }

            @Override
            public void set(int i, int i1) {

            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.endlessexpansion.evolution_table");
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new EvolutionTableMenu(id, inventory, this, this.data);
    }

    public void evolveWand(WandEvolution evolution, UUID playerUUID, int xpCost) {
        if (evolution == null || level == null) return;
        Player player = level.getPlayerByUUID(playerUUID);
        if (player == null) return;
        if(player.experienceLevel >= xpCost || player.isCreative()) {
            PlayerXpEvent.LevelChange event = new PlayerXpEvent.LevelChange(player, xpCost);
            if(!MinecraftForge.EVENT_BUS.post(event)) {
                if(!player.isCreative()) player.giveExperienceLevels(-xpCost);

                ItemStack stack = itemHandler.getStackInSlot(0);
                if(stack.getItem() instanceof WandItem) {
                    CompoundTag tag = stack.getOrCreateTag();
                    tag.putString("evolution", evolution.getBaseId().toString());
                    stack.setTag(tag);
                    itemHandler.setStackInSlot(0, stack);
                    ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
                    if (id != null) PlayerWandData.updateHighestEvolution(player, id.toString(), evolution.getBaseId().toString());
                }
            }
        }
    }
}
