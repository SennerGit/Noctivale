package net.sen.noctivale.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.sen.noctivale.common.recipe.ChemicalSynthesizerRecipe;
import net.sen.noctivale.common.recipe.ChemicalSynthesizerRecipeInput;
import net.sen.noctivale.common.registries.NoctivaleBlockEntities;
import net.sen.noctivale.common.registries.NoctivaleRecipes;
import net.sen.noctivale.common.screen.DistillationApparatusMenu;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DistillationApparatusBlockEntity extends BlockEntity implements MenuProvider, BlockEntityTicker {
    public final ItemStackHandler inputHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            DistillationApparatusBlockEntity.this.setChanged();
        }
    };

    public final ItemStackHandler outputHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            DistillationApparatusBlockEntity.this.setChanged();
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int TEST_TUBE_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    public CombinedInvWrapper combinedHandler = new CombinedInvWrapper(this.inputHandler, this.outputHandler);
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public DistillationApparatusBlockEntity(BlockPos pos, BlockState blockState) {
        super(NoctivaleBlockEntities.DISTILLATION_APPARATUS.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DistillationApparatusBlockEntity.this.progress;
                    case 1 -> DistillationApparatusBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DistillationApparatusBlockEntity.this.progress = value;
                    case 1 -> DistillationApparatusBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(inputHandler.getSlots());
        for (int i = 0; i < inputHandler.getSlots(); i++) {
            inventory.setItem(i, inputHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    /*
    MENU
     */
    @Override
    public Component getDisplayName() {
        return Component.literal("Distillation Apparatus");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new DistillationApparatusMenu(containerId, inventory, this, this.data);
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

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (level.isClientSide()) return;

        boolean onHotElement = checkBelowBlock(level, pos, Blocks.LAVA, Blocks.FIRE, Blocks.MAGMA_BLOCK);
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

        inputHandler.extractItem(INPUT_SLOT, 1, false);
        inputHandler.extractItem(TEST_TUBE_SLOT, 1, false);
        outputHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(), outputHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
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
        inputs.add(inputHandler.getStackInSlot(INPUT_SLOT));
        return this.level.getRecipeManager().getRecipeFor(NoctivaleRecipes.CHEMICAL_SYNTHESIZER_TYPES.get(), new ChemicalSynthesizerRecipeInput(inputs), this.level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return outputHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || outputHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = outputHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : outputHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = outputHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }

    /*
    SAVE & LOAD
     */
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inputHandler", this.inputHandler.serializeNBT(registries));
        tag.put("outputHandler", this.outputHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.inputHandler.deserializeNBT(registries, tag.getCompound("inputHandler"));
        this.outputHandler.deserializeNBT(registries, tag.getCompound("outputHandler"));
    }
}
