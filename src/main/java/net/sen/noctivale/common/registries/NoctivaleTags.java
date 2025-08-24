package net.sen.noctivale.common.registries;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class NoctivaleTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) { return TagKey.create(Registries.BLOCK, ModUtils.getModPath(name)); }
    }
    public static class Items {
        private static TagKey<Item> createTag(String name) { return TagKey.create(Registries.ITEM, ModUtils.getModPath(name)); }
    }
    public static class EntityTypes {
        private static TagKey<EntityType<?>> createTag(String name) { return TagKey.create(Registries.ENTITY_TYPE, ModUtils.getModPath(name)); }
    }
    public static class Biomes {

        private static TagKey<Biome> createTag(String name) { return TagKey.create(Registries.BIOME, ModUtils.getModPath(name)); }
    }

    /* Code By BluSunrize
     * https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/src/api/java/blusunrize/immersiveengineering/api/IETags.java#L36
     * */

    private static final Map<TagKey<Block>, TagKey<Item>> toItemTag = new HashMap<>();

    static {

    }

    public static TagKey<Item> getItemTag(TagKey<Block> blockTag)
    {
        Preconditions.checkArgument(toItemTag.containsKey(blockTag));
        return toItemTag.get(blockTag);
    }

    public static void forAllBlocktags(BiConsumer<TagKey<Block>, TagKey<Item>> out)
    {
        for(Map.Entry<TagKey<Block>, TagKey<Item>> entry : toItemTag.entrySet())
            out.accept(entry.getKey(), entry.getValue());
    }
}
