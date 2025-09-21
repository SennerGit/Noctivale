package net.sen.noctivale.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.sen.noctivale.common.registries.NoctivaleDataComponents;
import net.sen.noctivale.common.utils.alchemy.AlchemyTypes;

import java.util.List;

public class TestTubeWaterItem extends TestTubeEmptyItem {
    public static final int MAX_FLUID = 100;

    public TestTubeWaterItem() {
        super("Water");
        this.asItem().getDefaultInstance().set(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT,  MAX_FLUID);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (hasFluid(stack)) {
            int waterAmount = stack.get(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT);

            tooltipComponents.add(Component.literal("Water: " + waterAmount + "/" + MAX_FLUID));
        } else {
            tooltipComponents.add(Component.literal("null: 0/" + MAX_FLUID));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    private boolean hasFluid(ItemStack stack) {
        return stack.get(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT) != null && stack.get(NoctivaleDataComponents.TEST_TUBE_FLUID_AMOUNT) > 0;
    }
}
