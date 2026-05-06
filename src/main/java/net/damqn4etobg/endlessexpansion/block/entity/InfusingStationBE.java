package net.damqn4etobg.endlessexpansion.block.entity;

import net.damqn4etobg.endlessexpansion.block.EndlessBlocks;
import net.damqn4etobg.endlessexpansion.screen.menu.InfusingStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class InfusingStationBE extends BaseContainerBlockEntity implements IMultiblock {
    private final MultiblockShape shape;
    public static final int SIZE = 4;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(4);
    protected final ContainerData data;

    public InfusingStationBE(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.INFUSING_STATION.get(), pos, state);

        this.shape = createShape();
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
                return 0;
            }
        };
    }

    private MultiblockShape createShape() {
        return new MultiblockShape()
                .add(new BlockPos(-1, 0, 0), EndlessBlocks.ARBOR_LOG.get())
                .add(new BlockPos(1, 0, 0), EndlessBlocks.STRIPPED_ARBOR_LOG.get());
    }

    @Override
    public MultiblockShape getShape() {
        return shape;
    }

    @Override
    public BlockPos getOrigin() {
        return getBlockPos();
    }

    @Override
    public Direction getFacing() {
        return getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.endlessexpansion.infusing_station");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new InfusingStationMenu(id, inventory, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }
}
