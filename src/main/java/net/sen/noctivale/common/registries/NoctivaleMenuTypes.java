package net.sen.noctivale.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.common.screen.*;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.function.Supplier;

public class NoctivaleMenuTypes {
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, ModUtils.getModId());

    public static final Supplier<MenuType<SolventExtractorMenu>> SOLVENT_EXTRACTOR_MENU = createMenu("solvent_extractor_menu", SolventExtractorMenu::new);
    public static final Supplier<MenuType<DistillationApparatusMenu>> DISTILLATION_APPARATUS_MENU = createMenu("distillation_apparatus_menu", DistillationApparatusMenu::new);
    public static final Supplier<MenuType<ChemicalSynthesizerMenu>> CHEMICAL_SYNTHESIZER_MENU = createMenu("chemical_synthesizer", ChemicalSynthesizerMenu::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> createMenu(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
