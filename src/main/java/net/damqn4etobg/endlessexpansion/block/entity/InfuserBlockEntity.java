package net.damqn4etobg.endlessexpansion.block.entity;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.*;
import net.damqn4etobg.endlessexpansion.recipe.InfuserRecipe;
import net.damqn4etobg.endlessexpansion.recipe.RadioactiveGeneratorRecipe;
import net.damqn4etobg.endlessexpansion.screen.InfuserMenu;
import net.damqn4etobg.endlessexpansion.screen.RadioactiveGeneratorMenu;
import net.damqn4etobg.endlessexpansion.util.*;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InfuserBlockEntity extends BlockEntity implements MenuProvider {
    private static final int INPUT_SLOT_1 = 1;
    private static final int INPUT_SLOT_2 = 3;
    private static final int OUTPUT_SLOT = 2;
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.LUMINITE.get();
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private final ModLuminiteEssence LUMINITE_ESSENCE = new ModLuminiteEssence(20, 20) {
        @Override
        public void onEssenceChanged() {
            setChanged();
            ModMessages.sendToClients(new EssenceSyncS2CPacket(this.getEssence(), getBlockPos()));
        }
    };

    public ILuminiteEssence getEssenceStorage() {
        return LUMINITE_ESSENCE;
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<ILuminiteEssence> lazyEssenceHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    public InfuserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFUSER.get(), pos, state);
        this.data = new ContainerData() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InfuserBlockEntity.this.progress;
                    case 1 -> InfuserBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InfuserBlockEntity.this.progress = value;
                    case 1 -> InfuserBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public ItemStack getRenderStack() {
        if(itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            return itemHandler.getStackInSlot(INPUT_SLOT_1);
        } else {
            return itemHandler.getStackInSlot(OUTPUT_SLOT);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.endlessexpansion.infuser");
    }

    public void setEssenceLevel(int essence) {
        this.LUMINITE_ESSENCE.setEssence(essence);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new InfuserMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        if(cap == ModCapabilities.LUMINITE_ESSENCE) {
            return lazyEssenceHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEssenceHandler = LazyOptional.of(() -> LUMINITE_ESSENCE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEssenceHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("infuser.progress", this.progress);
        nbt.putInt("infuser.essence", this.LUMINITE_ESSENCE.getEssence());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("infuser.progress");
        LUMINITE_ESSENCE.setEssence(nbt.getInt("infuser.essence"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        makeLuminiteEssence();
        if(hasRecipe() && LUMINITE_ESSENCE.getEssence() >= 2) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
                LUMINITE_ESSENCE.extractEssence(2, false);
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void makeLuminiteEssence() {
        if(LUMINITE_ESSENCE.getEssence() < 20 && hasLuminiteInSlot()) {
            this.LUMINITE_ESSENCE.receiveEssence(2, false);
            this.itemHandler.extractItem(0, 1, false);
        }
    }

    private void craftItem() {
        Optional<InfuserRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_2, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Optional<InfuserRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<InfuserRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(InfuserRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasLuminiteInSlot() {
        return this.itemHandler.getStackInSlot(0).getItem() == ModItems.LUMINITE.get();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
