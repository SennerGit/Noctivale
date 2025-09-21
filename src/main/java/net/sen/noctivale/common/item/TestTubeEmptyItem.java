package net.sen.noctivale.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.sen.noctivale.Noctivale;
import net.sen.noctivale.common.registries.NoctivaleDataComponents;
import net.sen.noctivale.common.registries.NoctivaleTestTubeItems;

public class TestTubeEmptyItem extends Item {
    private final String name;

    public TestTubeEmptyItem(String name) {
        super(new Properties().stacksTo(1));
        this.name = name;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, isEmpty(player) ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);

        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(blockpos1, direction, itemstack)) {
                return InteractionResultHolder.fail(itemstack);
            } else if (isEmpty(player)) {
                BlockState blockState = level.getBlockState(blockpos);
                if (blockState.getBlock() == Blocks.WATER) {
                    ItemStack filledTestTube = NoctivaleTestTubeItems.TEST_TUBE_WATER.get().getDefaultInstance();
                    filledTestTube.set(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT, TestTubeWaterItem.MAX_FLUID);
                    player.setItemInHand(usedHand, filledTestTube);
                    level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BUCKET_FILL, player.getSoundSource(), 1.0F, 1.0F);
                    return InteractionResultHolder.sidedSuccess(filledTestTube, level.isClientSide());
                }
            }

            return InteractionResultHolder.fail(itemstack);
        }
    }

    private boolean isEmpty(Player player) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).get(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT) == null || player.getItemInHand(InteractionHand.MAIN_HAND).get(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT) == 0;
    }

    public String getName() {
        return name;
    }
}
