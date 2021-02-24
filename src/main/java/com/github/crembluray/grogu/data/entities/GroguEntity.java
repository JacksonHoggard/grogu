package com.github.crembluray.grogu.data.entities;

import com.github.crembluray.grogu.setup.ModBlocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GroguEntity extends CreatureEntity {

    private boolean idle;
    private boolean usingForce;

    public GroguEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        idle = true;
        usingForce = false;
    }

    // func_233666_p_() --> registerAttributes()
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new UseForceGoal(this, ModBlocks.METAL_BALL_BLOCK.get(), 0.02f));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 1 + this.world.rand.nextInt(4);
    }

    public boolean isIdle() {
        return this.idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public boolean isUsingForce() {
        return this.usingForce;
    }

    public void setUsingForce(boolean usingForce) {
        this.usingForce = usingForce;
    }
}
