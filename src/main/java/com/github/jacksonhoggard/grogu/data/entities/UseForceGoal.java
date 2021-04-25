package com.github.jacksonhoggard.grogu.data.entities;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class UseForceGoal extends Goal {

    protected final GroguEntity entity;
    protected BlockPos closestBlockPos;
    protected final Block watchedBlock;
    private int lookTime;
    private final float chance;

    public UseForceGoal(GroguEntity entityIn, Block watchedBlock, float chanceIn) {
        this.entity = entityIn;
        this.watchedBlock = watchedBlock;
        lookTime = 0;
        chance = chanceIn;
        this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        if (entity.getRNG().nextFloat() >= chance)
            return false;

        BlockPos positiveRadiusPosition = entity.getPosition().add(8, 2, 8);
        BlockPos negativeRadiusPosition = entity.getPosition().add(-8, 0, -8);

        Iterable<BlockPos> cube = BlockPos.getAllInBoxMutable(positiveRadiusPosition, negativeRadiusPosition);

        for(BlockPos pos : cube) {
            Block block = entity.world.getBlockState(pos).getBlock();
            Block blockAbove = entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1.0f, pos.getZ())).getBlock();
            if(block.matchesBlock(watchedBlock) && blockAbove.matchesBlock(Blocks.AIR)) {
                closestBlockPos = pos;
                return true;
            }
        }
        return false;
    }

    @Override
    public void resetTask() {
        lookTime = 0;
        entity.setUsingForce(false);
        entity.setIdle(true);

        Block block = entity.world.getBlockState(closestBlockPos).getBlock();
        Block blockAbove = entity.world.getBlockState(new BlockPos(closestBlockPos.getX(), closestBlockPos.getY() + 1.0f, closestBlockPos.getZ())).getBlock();

        if(!block.matchesBlock(Blocks.AIR) || !blockAbove.matchesBlock(watchedBlock))
            return;

        entity.world.setBlockState(new BlockPos(closestBlockPos.getX(), closestBlockPos.getY() + 1.0f, closestBlockPos.getZ()), Blocks.AIR.getDefaultState());
        entity.world.setBlockState(closestBlockPos, watchedBlock.getDefaultState());
    }

    @Override
    public void startExecuting() {
        lookTime = 100;
        entity.setUsingForce(true);
        entity.setIdle(false);

        entity.getNavigator().tryMoveToXYZ(closestBlockPos.getX(), closestBlockPos.getY(), closestBlockPos.getZ(), 1.25f);

        BlockPos newBlockPos = new BlockPos(new BlockPos(closestBlockPos.getX(), closestBlockPos.getY() + 1.0f, closestBlockPos.getZ()));
        entity.world.setBlockState(closestBlockPos, Blocks.AIR.getDefaultState());
        entity.world.setBlockState(newBlockPos, watchedBlock.getDefaultState());
    }

    @Override
    public boolean shouldContinueExecuting() {
        Block blockAbove = entity.world.getBlockState(new BlockPos(closestBlockPos.getX(), closestBlockPos.getY() + 1.0f, closestBlockPos.getZ())).getBlock();

        return lookTime > 0 && !blockAbove.matchesBlock(Blocks.AIR);
    }

    @Override
    public void tick() {
        entity.getLookController().setLookPosition(this.closestBlockPos.getX(), this.closestBlockPos.getY() + 2.0f, this.closestBlockPos.getZ());
        --this.lookTime;
    }
}
