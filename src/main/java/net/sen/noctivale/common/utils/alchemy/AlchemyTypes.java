package net.sen.noctivale.common.utils.alchemy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;

import java.util.Locale;

public enum AlchemyTypes {
    // Base alchemy types (Tier 0).
    VITALIS(0, "Vitalis"),      // Life and vitality.
    CHALYBS(0, "Chalybs"),      // Metals and machines.
    NECRUS(0, "Necrus"),       // death and decay.
    MUTAGEN(0, "Mutagen"),      // Transformation and mutation.
    FULGUR(0, "Fulgur"),       // Volatile and kinetic energy.
    PURITAS(0, "Puritas"),      // Purification and refinement.
    FLORA(0, "Flora"),        // Plant life and growth.
    FAUNA(0, "Fauna"),        // Animal life and instincts.
    AQUA(0, "Aqua"),         // Water and fluidity.
    TERRA(0, "Terra"),        // Earth and stability.

    // Mixed alchemy types (Tier 1), created by combining base chemicals.
    AETHERIUS(1, "Aetherius"),   // Aetherius is a mixed type of Vitalis and Puritas.
    NOX(1, "Nox"),         // Nox is a mixed type of Necrus and Mutagen.
    STELLARIS(1, "Stellaris"),    // Stellaris is a mixed type of Flora and Aqua.
    GEONIS(1, "Geonis"),      // Geonis is a mixed type of Terra and Fauna.
    AUTOMATON(1, "Automaton");   // Automaton is a mixed type of Chalybs and Fulgur.

    private final int tier;
    private final String id;

    AlchemyTypes(int tier, String id) {
        this.tier = tier;
        this.id = id;
    }

    /**
     * Gets the tier of this alchemy type.
     * Tier 0 represents a base, extracted chemical.
     * Tier 1 and higher represent mixed, more complex compounds.
     * @return the tier of the alchemy type.
     */
    public int getTier() {
        return this.tier;
    }

    /**
     * Gets the unique ID of this alchemy type, used for simple generation.
     * @return the unique ID string.
     */
    public String getId() {
        return this.id.toLowerCase(Locale.ROOT);
    }
    public String getName() {
        return this.id;
    }

    /**
     * Generates the path or identifier for the test tube model based on the ID.
     * @return the test tube identifier.
     */
    public String getTestTube() {
        return this.id + "_test_tube";
    }

    /**
     * Generates the path or identifier for the tooltip icon based on the ID.
     * @return the tooltip icon identifier.
     */
    public String getTooltipIcon() {
        return "icon/" + this.id;
    }
}
