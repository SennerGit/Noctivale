package net.sen.noctivale.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.sen.noctivale.common.utils.ModUtils;
import net.sen.noctivale.data.world.*;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ModRegistriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, NoctivaleConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NoctivalePlacedFeatures::bootstrap)
//            .add(Registries.DENSITY_FUNCTION_TYPE, YggdrasilDensityFunction::bootstrap)
;

    public ModRegistriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(ModUtils.getModId()));
    }
}
