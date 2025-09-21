package net.sen.noctivale.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sen.noctivale.common.recipe.ChemicalSynthesizerRecipe;
import net.sen.noctivale.common.recipe.ChemicalSynthesizerRecipeInput;
import net.sen.noctivale.common.registries.NoctivaleBlockEntities;
import net.sen.noctivale.common.registries.NoctivaleRecipes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestTubeRackBlockEntity extends BlockEntity implements BlockEntityTicker {
    public final ItemStackHandler inventoryHandler = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            TestTubeRackBlockEntity.this.setChanged();
        }
    };

    private static final int INPUT_SLOT = 0;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public TestTubeRackBlockEntity(BlockPos pos, BlockState blockState) {
        super(NoctivaleBlockEntities.TEST_TUBE_RACK.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TestTubeRackBlockEntity.this.progress;
                    case 1 -> TestTubeRackBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TestTubeRackBlockEntity.this.progress = value;
                    case 1 -> TestTubeRackBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (level.isClientSide()) return;
        if (inventoryHandler.getStackInSlot(0).isEmpty()) return;

        boolean onHotElement = checkBelowBlock(level, pos, Blocks.LAVA, Blocks.FIRE, Blocks.MAGMA_BLOCK);
        boolean onColdElement = checkBelowBlock(level, pos, Blocks.ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE);
        boolean hotBiome = level.getBiome(pos).value().getBaseTemperature() >= 1.0f;
        boolean coldBiome = level.getBiome(pos).value().getBaseTemperature() <= 0.15f;
        float temperatureElement = onHotElement ? 1.0f : onColdElement ? -1.0f : 0.0f;
        float temperatureBiome = hotBiome ? 0.5f : coldBiome ? -0.5f : 0.0f;
        float temperature = temperatureElement + temperatureBiome;
        float time = level.getDayTimePerTick();
        float height = pos.getY();
        boolean rain = level.isRainingAt(pos.above());
    }

    private boolean checkBelowBlock(Level level, BlockPos blockPos, Block block) {
        return level.getBlockState(blockPos.below()).getBlock() == block;
    }

    private boolean checkBelowBlock(Level level, BlockPos blockPos, Block... blocks) {
        for (Block block : blocks) {
            if (checkBelowBlock(level, blockPos, block)) {
                return true;
            }
        }
        return false;
    }

    public void clearContents() {
        inventoryHandler.setStackInSlot(0, ItemStack.EMPTY);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventoryHandler.getSlots());
        for(int i = 0; i < inventoryHandler.getSlots(); i++) {
            inv.setItem(i, inventoryHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    /*
    CRAFTING
     */
    public void craftingTick(Level level,BlockPos blockPos, BlockState blockState) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<ChemicalSynthesizerRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        inventoryHandler.extractItem(INPUT_SLOT, 1, false);
        inventoryHandler.setStackInSlot(INPUT_SLOT, new ItemStack(output.getItem(), inventoryHandler.getStackInSlot(0).getCount() + output.getCount()));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<ChemicalSynthesizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<ChemicalSynthesizerRecipe>> getCurrentRecipe() {
        List<ItemStack> inputs = new ArrayList<>();
        inputs.add(inventoryHandler.getStackInSlot(INPUT_SLOT));
        return this.level.getRecipeManager().getRecipeFor(NoctivaleRecipes.CHEMICAL_SYNTHESIZER_TYPES.get(), new ChemicalSynthesizerRecipeInput(inputs), this.level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventoryHandler.getStackInSlot(INPUT_SLOT).isEmpty() || inventoryHandler.getStackInSlot(INPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return !inventoryHandler.getStackInSlot(INPUT_SLOT).isEmpty();
    }

    /*
    SAVE & LOAD
     */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", this.inventoryHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.inventoryHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
