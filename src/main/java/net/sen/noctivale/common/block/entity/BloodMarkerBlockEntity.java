package net.sen.noctivale.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sen.noctivale.common.registries.NoctivaleBlockEntities;

public class BloodMarkerBlockEntity extends BlockEntity {
    private static final String ID_TAG = "customId";
    private String entityID = "";

    public BloodMarkerBlockEntity(BlockPos pos, BlockState blockState) {
        super(NoctivaleBlockEntities.BLOOD_MARKER_BLOCK.get(), pos, blockState);
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (this.entityID != null) {
            tag.putString(ID_TAG, this.entityID);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (this.entityID != null) {
            tag.putString(ID_TAG, this.entityID);
        }
    }
}
