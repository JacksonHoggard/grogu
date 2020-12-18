package com.github.crembluray.grogu.data.client.model;

import com.github.crembluray.grogu.data.entities.GroguEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GroguModel<T extends GroguEntity> extends EntityModel<T> {

	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer head;
	private final ModelRenderer left_side;
	private final ModelRenderer right_side;

	public GroguModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 4.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(22, 28).addBox(-4.5F, -6.0F, -3.0F, 9.0F, 2.0F, 6.0F, 0.25F, false);
		body.setTextureOffset(0, 12).addBox(-5.0F, -9.0F, -4.0F, 10.0F, 3.0F, 8.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(5.0F, 18.0F, 0.0F);
		setRotationAngle(left_arm, 0.0F, 0.0F, 0.6545F);
		left_arm.setTextureOffset(28, 12).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		left_arm.setTextureOffset(22, 36).addBox(2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-5.0F, 18.0F, 0.0F);
		setRotationAngle(right_arm, 0.0F, 0.0F, -0.6545F);
		right_arm.setTextureOffset(28, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		right_arm.setTextureOffset(14, 34).addBox(-5.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 15.0F, 0.0F);
		head.setTextureOffset(0, 23).addBox(-4.0F, -5.0F, -3.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);

		left_side = new ModelRenderer(this);
		left_side.setRotationPoint(4.0F, -2.5F, 0.0F);
		head.addChild(left_side);
		setRotationAngle(left_side, 0.0F, -0.0873F, 0.0873F);
		left_side.setTextureOffset(0, 34).addBox(-0.5F, -2.0F, 0.0F, 7.0F, 4.0F, 0.0F, 0.0F, false);

		right_side = new ModelRenderer(this);
		right_side.setRotationPoint(-4.0F, -2.5F, 0.0F);
		head.addChild(right_side);
		setRotationAngle(right_side, 0.0F, 0.0873F, -0.0873F);
		right_side.setTextureOffset(22, 23).addBox(-6.5F, -2.0F, 0.0F, 7.0F, 4.0F, 0.0F, 0.0F, false);
	}

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean idle = entityIn.isIdle();
		boolean usingForce = entityIn.isUsingForce();
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		if(idle) {
			this.body.rotateAngleX = 0.0f;
			this.body.rotateAngleY = 0.0f;
			this.body.rotateAngleZ = 0.0f;
			this.right_arm.setRotationPoint(-5.0F, 18.0F, 0.0F);
			this.right_arm.rotateAngleX = 0.0f;
			this.right_arm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
			this.right_arm.rotateAngleZ = -0.6545f;
			this.left_arm.setRotationPoint(5.0F, 18.0F, 0.0F);
			this.left_arm.rotateAngleX = 0.0f;
			this.left_arm.rotateAngleY = -MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
			this.left_arm.rotateAngleZ = 0.6545f;
		}
		if(usingForce) {
			this.body.rotateAngleX = 0.0f;
			this.body.rotateAngleY = 0.6108f;
			this.body.rotateAngleZ = 0.0f;
			this.right_arm.setRotationPoint(-5.0F, 18.0F, 2.0F);
			this.right_arm.rotateAngleX = 0.0f;
			this.right_arm.rotateAngleY = 0.5673f;
			this.right_arm.rotateAngleZ = -0.6545f;
			this.left_arm.setRotationPoint(5.0F, 18.0F, -3.0F);
			this.left_arm.rotateAngleX = 2.3998f;
			this.left_arm.rotateAngleY = 0.6981F;
			this.left_arm.rotateAngleZ = -1.309f;
		}
    }

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}