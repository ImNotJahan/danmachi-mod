package imnotjahan.mod.danmachi.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHobgoblin extends ModelBase {
    public ModelRenderer rightArm;
    public ModelRenderer rightLeg;
    public ModelRenderer head;
    public ModelRenderer chest;
    public ModelRenderer leftArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightHorn;
    public ModelRenderer leftHorn;

    public ModelHobgoblin() {
        textureWidth = 64;
        textureHeight = 64;

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-5, 3, 0);
        rightArm.setTextureOffset(36, 38).addBox(-5, -3, -3, 4, 14, 4);
        rightArm.setTextureOffset(36, 38).addBox(-5, 11, -3, 1, 1, 4, 0);
        rightArm.setTextureOffset(36, 38).addBox(-4, 11, -4, 1, 1, 0, 0);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-1.9F, 18, 0);
        rightLeg.setTextureOffset(18, 38).addBox(-4, -6, -3, 5, 12, 4, 0);

        head = new ModelRenderer(this);
        head.setRotationPoint(0, 0, 0);
        head.setTextureOffset(0, 0).addBox(-6, -10, -6, 10, 10, 10, 0);

        chest = new ModelRenderer(this);
        chest.setRotationPoint(0, 0, 0);
        chest.setTextureOffset(0, 24).addBox(-6, 0, -3, 10, 12, 4, 0);
        chest.setTextureOffset(0, 24).addBox(-5, 7, -5, 8, 5, 2, 0);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(0, 3, 0);
        leftArm.setTextureOffset(28, 20).addBox(4, -3, -3, 4, 14, 4, 0);
        leftArm.setTextureOffset(28, 20).addBox(6, 11, -4, 1, 1, 0, 0);
        leftArm.setTextureOffset(28, 20).addBox(7, 11, -3, 1, 1, 4, 0);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(1.9F, 18, 0);
        leftLeg.setTextureOffset(0, 36).addBox(-3, -6, -3, 5, 12, 4, 0);

        rightHorn = new ModelRenderer(this);
        rightHorn.setRotationPoint(0, 0, 0);
        rightHorn.setTextureOffset(0, 0).addBox(-5, -11, -7, 3, 4, 3, 0);
        rightHorn.setTextureOffset(0, 0).addBox(-5, -15, -7, 2, 4, 1, 0);
        rightHorn.setTextureOffset(0, 0).addBox(-5, -12, -6, 2, 1, 1, 0);
        rightHorn.setTextureOffset(0, 0).addBox(-5, -17, -7, 1, 2, 1, 0);

        leftHorn = new ModelRenderer(this);
        leftHorn.setRotationPoint(0, 0, 0);
        leftHorn.setTextureOffset(0, 0).addBox(0, -11, -7, 3, 4, 3, 0);
        leftHorn.setTextureOffset(0, 0).addBox(2, -17, -7, 1, 2, 1, 0);
        leftHorn.setTextureOffset(0, 0).addBox(1, -15, -7, 2, 4, 1, 0);
        leftHorn.setTextureOffset(0, 0).addBox(1, -12, -6, 2, 1, 1, 0);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.chest.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftArm.offsetX, this.leftArm.offsetY, this.leftArm.offsetZ);
        GlStateManager.translate(this.leftArm.rotationPointX * f5, this.leftArm.rotationPointY * f5, this.leftArm.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1, 1.0D);
        GlStateManager.translate(-this.leftArm.offsetX, -this.leftArm.offsetY, -this.leftArm.offsetZ);
        GlStateManager.translate(-this.leftArm.rotationPointX * f5, -this.leftArm.rotationPointY * f5, -this.leftArm.rotationPointZ * f5);
        this.leftArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftLeg.offsetX, this.leftLeg.offsetY, this.leftLeg.offsetZ);
        GlStateManager.translate(this.leftLeg.rotationPointX * f5, this.leftLeg.rotationPointY * f5, this.leftLeg.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1, 1.0D);
        GlStateManager.translate(-this.leftLeg.offsetX, -this.leftLeg.offsetY, -this.leftLeg.offsetZ);
        GlStateManager.translate(-this.leftLeg.rotationPointX * f5, -this.leftLeg.rotationPointY * f5, -this.leftLeg.rotationPointZ * f5);
        this.leftLeg.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightLeg.offsetX, this.rightLeg.offsetY, this.rightLeg.offsetZ);
        GlStateManager.translate(this.rightLeg.rotationPointX * f5, this.rightLeg.rotationPointY * f5, this.rightLeg.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1, 1.0D);
        GlStateManager.translate(-this.rightLeg.offsetX, -this.rightLeg.offsetY, -this.rightLeg.offsetZ);
        GlStateManager.translate(-this.rightLeg.rotationPointX * f5, -this.rightLeg.rotationPointY * f5, -this.rightLeg.rotationPointZ * f5);
        this.rightLeg.render(f5);
        GlStateManager.popMatrix();
        this.rightHorn.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightArm.offsetX, this.rightArm.offsetY, this.rightArm.offsetZ);
        GlStateManager.translate(this.rightArm.rotationPointX * f5, this.rightArm.rotationPointY * f5, this.rightArm.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1, 1.0D);
        GlStateManager.translate(-this.rightArm.offsetX, -this.rightArm.offsetY, -this.rightArm.offsetZ);
        GlStateManager.translate(-this.rightArm.rotationPointX * f5, -this.rightArm.rotationPointY * f5, -this.rightArm.rotationPointZ * f5);
        this.rightArm.render(f5);
        GlStateManager.popMatrix();
        this.head.render(f5);
        this.leftHorn.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
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

        this.rightHorn.rotateAngleY = netHeadYaw * 0.017453292F;
        this.rightHorn.rotateAngleX = headPitch * 0.017453292F;
        this.leftHorn.rotateAngleY = netHeadYaw * 0.017453292F;
        this.leftHorn.rotateAngleX = headPitch * 0.017453292F;
    }
}
