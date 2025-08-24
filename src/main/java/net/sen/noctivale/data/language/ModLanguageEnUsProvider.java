package net.sen.noctivale.data.language;

import net.minecraft.data.PackOutput;
import net.sen.noctivale.common.registries.NoctivaleCreativeModeTabs;

import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;
import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleEntityTypes.*;

public class ModLanguageEnUsProvider extends LanguageProviderHelper {
    public ModLanguageEnUsProvider(PackOutput output, String locale) {
        super(output, locale);
    }

    @Override
    void spawnEggs() {

    }

    @Override
    void blocks() {

    }

    @Override
    void items() {
        this.addItem(DRAWING_KNIFE, "Drawing Knife");
    }

    @Override
    void paintings() {

    }

    @Override
    void effects() {

    }

    @Override
    void potions() {

    }

    @Override
    void sounds() {

    }

    @Override
    void custom() {

    }

    @Override
    void config() {

    }

    @Override
    void creativeTab() {
        addCreativeTab(NoctivaleCreativeModeTabs.NOCTIVALE_TAB, "Noctivale Tab");
    }

    @Override
    void baseAdvancements() {

    }
}
