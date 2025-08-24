package net.sen.noctivale.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.sen.noctivale.Noctivale;

public class DrawingKnifeItem extends SwordItem {
    public DrawingKnifeItem() {
        super(Tiers.IRON, new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.isCrouching()) {
            Noctivale.LOGGER.debug("Cut self");
            DamageSources damageSources = level.damageSources();
            player.hurt(damageSources.magic(), 1.0F);
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getPlayer().isCrouching()) {
            Noctivale.LOGGER.debug("Draw Blood");
            return InteractionResult.PASS;
        }

        return super.useOn(context);
    }
}
