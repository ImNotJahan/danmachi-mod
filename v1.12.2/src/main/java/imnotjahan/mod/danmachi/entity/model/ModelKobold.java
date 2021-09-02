package imnotjahan.mod.danmachi.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelKobold extends ModelBase
{
	private final ModelRenderer rightArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer chest;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer nose;
	private final ModelRenderer head;
	private final ModelRenderer rightEar;
	private final ModelRenderer leftEar;

	public ModelKobold()
	{
		textureWidth = 64;
		textureHeight = 64;

		this.head = new ModelRenderer(this, 10, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);

		this.rightArm = new ModelRenderer(this, 41, 16);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0);
		this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 41, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0);
		this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0);
		this.leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0);
		this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.chest = new ModelRenderer(this, 16, 16);
		this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0);
		this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.rightEar = new ModelRenderer(this, 16, 14);
		this.rightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightEar.addBox(-3.0F, -8.0F, 0.0F, 2, 2, 1, 0.0F);

		this.leftEar = new ModelRenderer(this, 16, 14);
		this.leftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftEar.addBox(1.0F, -8.0F, 0.0F, 2, 2, 1, 0.0F);

		this.nose = new ModelRenderer(this, 0, 10);
		this.nose.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.nose.addBox(-1.5F, -3.0F, -6.0F, 3, 3, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
		rightArm.render(f5);
		rightLeg.render(f5);
		chest.render(f5);
		leftArm.render(f5);
		leftLeg.render(f5);
		nose.render(f5);
		head.render(f5);
		rightEar.render(f5);
		leftEar.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
    {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.rightEar.rotateAngleY = netHeadYaw * 0.017453292F;
        this.rightEar.rotateAngleX = headPitch * 0.017453292F;
        this.leftEar.rotateAngleY = netHeadYaw * 0.017453292F;
        this.leftEar.rotateAngleX = headPitch * 0.017453292F;

		this.nose.rotateAngleY = netHeadYaw * 0.017453292F;
		this.nose.rotateAngleX = headPitch * 0.017453292F;
    }
}