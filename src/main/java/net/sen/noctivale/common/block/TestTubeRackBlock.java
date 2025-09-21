package net.sen.noctivale.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sen.noctivale.common.block.entity.TestTubeRackBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

import static net.sen.noctivale.common.registries.NoctivaleTestTubeItems.TEST_TUBE_ITEMS;

public class TestTubeRackBlock  extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final MapCodec<TestTubeRackBlock> CODEC = simpleCodec(TestTubeRackBlock::new);

    public TestTubeRackBlock() {
        super(BlockBehaviour.Properties.of());
    }

    public TestTubeRackBlock(Properties properties) {
        super(BlockBehaviour.Properties.of().noCollission().noLootTable());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /*
    BlockEntity
     */

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TestTubeRackBlockEntity(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof TestTubeRackBlockEntity blockEntity) {
                blockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof TestTubeRackBlockEntity blockEntity) {
            if (blockEntity.inventoryHandler.getStackInSlot(0).isEmpty() && !stack.isEmpty()) {
                Collection<? extends Item> test_tube_items = TEST_TUBE_ITEMS.getEntries().stream().map(DeferredHolder::value).toList();
                for(Item testTube : test_tube_items) {
                    if (stack.getItem() == testTube) {
                        blockEntity.inventoryHandler.insertItem(0, stack.copy(), false);
                        stack.shrink(1);
                        level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                        return ItemInteractionResult.SUCCESS;
                    }
                }
            } else if (stack.isEmpty()) {
                ItemStack stackInRack = blockEntity.inventoryHandler.extractItem(0, 1, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackInRack);
                blockEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
}
