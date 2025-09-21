package net.sen.noctivale.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.sen.noctivale.common.block.entity.BloodMarkerBlockEntity;
import net.sen.noctivale.common.registries.NoctivaleBlocks;
import net.sen.noctivale.common.registries.NoctivaleDataComponents;

import java.util.List;
import java.util.Locale;

public class DrawingKnifeItem extends SwordItem {
    public static final int MAX_BLOOD = 10;

    public DrawingKnifeItem() {
        super(Tiers.IRON, new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.isCrouching()) {
            DamageSources damageSources = level.damageSources();
            player.hurt(damageSources.magic(), 1.0F);
            player.getItemInHand(usedHand).set(NoctivaleDataComponents.BLOOD_AMOUNT,  MAX_BLOOD);
            player.getItemInHand(usedHand).set(NoctivaleDataComponents.BLOOD_ENTITY,  player.getGameProfile().getName());
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof Player player1) {
            stack.set(NoctivaleDataComponents.BLOOD_AMOUNT, MAX_BLOOD);
            stack.set(NoctivaleDataComponents.BLOOD_ENTITY, player1.getGameProfile().getName());
        } else if (entity != null) {
            stack.set(NoctivaleDataComponents.BLOOD_AMOUNT, MAX_BLOOD);
            stack.set(NoctivaleDataComponents.BLOOD_ENTITY, BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString().toLowerCase(Locale.ROOT));
        }


        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getPlayer().isCrouching()) {
            if (hasBlood(context.getItemInHand())) {
                int bloodAmount = context.getItemInHand().get(NoctivaleDataComponents.BLOOD_AMOUNT);
                String bloodEntity = context.getItemInHand().get(NoctivaleDataComponents.BLOOD_ENTITY);

                if (bloodAmount > 0) {
                    if (!context.getPlayer().isCreative())
                        context.getItemInHand().set(NoctivaleDataComponents.BLOOD_AMOUNT, bloodAmount - 1);
                    placeBloodMark(context, bloodEntity);
                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (hasBlood(stack)) {
            int bloodAmount = stack.get(NoctivaleDataComponents.BLOOD_AMOUNT);
            String bloodEntity = stack.get(NoctivaleDataComponents.BLOOD_ENTITY);

            tooltipComponents.add(Component.literal(bloodEntity + " Blood: " + bloodAmount + "/" + MAX_BLOOD));
        } else {
            tooltipComponents.add(Component.literal("null Blood: 0/" + MAX_BLOOD));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    private void placeBloodMark(UseOnContext context, String bloodEntity) {
        BlockPos blockPos = context.getClickedPos();
        context.getLevel().setBlockAndUpdate(blockPos.above(), NoctivaleBlocks.BLOOD_MARKER_BLOCK.get().defaultBlockState());
        BlockEntity blockEntity = context.getLevel().getBlockEntity(blockPos.above());
        if (blockEntity instanceof BloodMarkerBlockEntity bloodMarkerBlockEntity) {
            bloodMarkerBlockEntity.setEntityID(bloodEntity);
        }
    }

    private boolean hasBlood(ItemStack stack) {
        return stack.get(NoctivaleDataComponents.BLOOD_AMOUNT) != null && stack.get(NoctivaleDataComponents.BLOOD_ENTITY) != null;
    }
}
