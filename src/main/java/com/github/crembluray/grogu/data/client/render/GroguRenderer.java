package com.github.crembluray.grogu.data.client.render;

import com.github.crembluray.grogu.Grogu;
import com.github.crembluray.grogu.data.client.model.GroguModel;
import com.github.crembluray.grogu.data.entities.GroguEntity;
import com.github.crembluray.grogu.setup.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.system.NonnullDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GroguRenderer extends MobRenderer<GroguEntity, GroguModel<GroguEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Grogu.MOD_ID, "textures/entity/grogu.png");

    public GroguRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GroguModel<>(), 0.5f);
    }

    @Override
    public void render(GroguEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        boolean useForce = shouldRenderForce(entityIn, ModBlocks.METAL_BALL_BLOCK.get());
        entityIn.setUsingForce(useForce);
        entityIn.setIdle(!useForce);
    }

    private boolean shouldRenderForce(GroguEntity entity, Block watchedBlock) {
        BlockPos positiveRadiusPosition = entity.getPosition().add(8, 2, 8);
        BlockPos negativeRadiusPosition = entity.getPosition().add(-8, 0, -8);

        Iterable<BlockPos> cube = BlockPos.getAllInBoxMutable(positiveRadiusPosition, negativeRadiusPosition);

        for(BlockPos pos : cube) {
            Block block = entity.world.getBlockState(pos).getBlock();
            Block blockBelow = entity.world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1.0f, pos.getZ())).getBlock();
            if(block.matchesBlock(watchedBlock) && blockBelow.matchesBlock(Blocks.AIR)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResourceLocation getEntityTexture(GroguEntity entity) {
        return TEXTURE;
    }
}
