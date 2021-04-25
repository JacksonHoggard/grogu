package com.github.jacksonhoggard.grogu.data.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class MetalBallBlock extends Block {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(7, 0, 7, 9, 2, 9);

    public MetalBallBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
            .hardnessAndResistance(0.2f, 6.0f)
            .sound(SoundType.METAL)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f;
    }

}
