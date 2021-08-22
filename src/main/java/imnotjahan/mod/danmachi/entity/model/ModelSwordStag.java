package imnotjahan.mod.danmachi.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSwordStag extends ModelBase
{
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer blm;
	private final ModelRenderer blb;
	private final ModelRenderer flt;
	private final ModelRenderer frt;
	private final ModelRenderer flm;
	private final ModelRenderer frm;
	private final ModelRenderer head;
	private final ModelRenderer upperMouth;
	private final ModelRenderer lowerMouth;
	private final ModelRenderer body;
	private final ModelRenderer tm;
	private final ModelRenderer blt;
	private final ModelRenderer neck;
	private final ModelRenderer mane;
	private final ModelRenderer tt;
	private final ModelRenderer tt_r1;
	private final ModelRenderer tb;
	private final ModelRenderer brt;
	private final ModelRenderer brm;
	private final ModelRenderer brb;
	private final ModelRenderer frb;
	private final ModelRenderer horn;
	private final ModelRenderer horn_r1;
	private final ModelRenderer horn_r2;
	private final ModelRenderer horn_r3;
	private final ModelRenderer horn_r4;
	private final ModelRenderer flb;

	public ModelSwordStag() {
		textureWidth = 128;
		textureHeight = 128;

		leftEar = new ModelRenderer(this);
		leftEar.setRotationPoint(0, 4, -10);
		setRotationAngle(leftEar, 0.5236F, 0, 0.2618F);
		

		rightEar = new ModelRenderer(this);
		rightEar.setRotationPoint(0, 4, -10);
		setRotationAngle(rightEar, 0.5236F, 0, -0.2618F);
		

		blm = new ModelRenderer(this);
		blm.setRotationPoint(0, 0, 0);
		blm.setTextureOffset(38, 54).addBox(-2, 7, -1.5F, 3, 5, 3, 0);

		blb = new ModelRenderer(this);
		blb.setRotationPoint(0, 0, 0);
		

		flt = new ModelRenderer(this);
		flt.setRotationPoint(4, 9, -8);
		flt.setTextureOffset(42, 34).addBox(-1.9F, -1, -2.1F, 3, 16, 4, 0);

		frt = new ModelRenderer(this);
		frt.setRotationPoint(-4, 9, -8);
		frt.setTextureOffset(44, 0).addBox(-1.1F, -1, -2.1F, 3, 16, 4, 0);

		flm = new ModelRenderer(this);
		flm.setRotationPoint(0, 0, 0);
		flm.setTextureOffset(0, 56).addBox(-1.9F, 7, -1.6F, 3, 5, 3, 0);

		frm = new ModelRenderer(this);
		frm.setRotationPoint(0, 0, 0);
		frm.setTextureOffset(56, 34).addBox(-1.1F, 7, -1.6F, 3, 5, 3, 0);

		head = new ModelRenderer(this);
		head.setRotationPoint(0, 4, -10);
		setRotationAngle(head, 0.5236F, 0, 0);
		

		upperMouth = new ModelRenderer(this);
		upperMouth.setRotationPoint(0, 3.95F, -10);
		setRotationAngle(upperMouth, 0.5236F, 0, 0);
		upperMouth.setTextureOffset(52, 50).addBox(-2, -6, -5, 4, 3, 4, 0);

		lowerMouth = new ModelRenderer(this);
		lowerMouth.setRotationPoint(0, 4, -10);
		setRotationAngle(lowerMouth, 0.5236F, 0, 0);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0, 11, 9);
		body.setTextureOffset(0, 0).addBox(-5, -8, -19, 10, 10, 24, 0);

		tm = new ModelRenderer(this);
		tm.setRotationPoint(0, 3, 14);
		setRotationAngle(tm, -1.309F, 0, 0);
		

		blt = new ModelRenderer(this);
		blt.setRotationPoint(4, 9, 11);
		blt.setTextureOffset(0, 34).addBox(-2.5F, -2, -2.5F, 4, 17, 5, 0);

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0, 4, -10);
		setRotationAngle(neck, 0.5236F, 0, 0);
		neck.setTextureOffset(18, 34).addBox(-3.05F, -9.8F, -2, 6, 7, 6, 0);
		neck.setTextureOffset(18, 47).addBox(-3.05F, -2.8F, 0, 6, 7, 4, 0);

		mane = new ModelRenderer(this);
		mane.setRotationPoint(0, 4, -10);
		setRotationAngle(mane, 0.5236F, 0, 0);
		

		tt = new ModelRenderer(this);
		tt.setRotationPoint(0, 3, 14);
		setRotationAngle(tt, -1.309F, 0, 0);
		

		tt_r1 = new ModelRenderer(this);
		tt_r1.setRotationPoint(0, 21, -1);
		tt.addChild(tt_r1);
		setRotationAngle(tt_r1, -0.6109F, 0, 0);
		tt_r1.setTextureOffset(13, 34).addBox(-1, -19, -13, 2, 2, 3, 0);

		tb = new ModelRenderer(this);
		tb.setRotationPoint(0, 3, 14);
		setRotationAngle(tb, -1.5708F, 0, 0);
		

		brt = new ModelRenderer(this);
		brt.setRotationPoint(-4, 9, 11);
		brt.setTextureOffset(0, 0).addBox(-1.5F, -2, -2.5F, 4, 17, 5, 0);

		brm = new ModelRenderer(this);
		brm.setRotationPoint(0, 0, 0);
		

		brb = new ModelRenderer(this);
		brb.setRotationPoint(0, 0, 0);
		

		frb = new ModelRenderer(this);
		frb.setRotationPoint(-4, 9, -8);
		

		horn = new ModelRenderer(this);
		horn.setRotationPoint(0, 4, -10);
		setRotationAngle(horn, 0.5236F, 0, 0);
		

		horn_r1 = new ModelRenderer(this);
		horn_r1.setRotationPoint(0, -10, 4);
		horn.addChild(horn_r1);
		setRotationAngle(horn_r1, -0.4597F, 0.4176F, 0.6863F);
		horn_r1.setTextureOffset(38, 47).addBox(0.5F, -4, -0.5F, 1, 6, 1, 0);

		horn_r2 = new ModelRenderer(this);
		horn_r2.setRotationPoint(0, -10, 4);
		horn.addChild(horn_r2);
		setRotationAngle(horn_r2, -0.582F, 0.1975F, 0.2898F);
		horn_r2.setTextureOffset(18, 0).addBox(0.5F, -8, -0.5F, 1, 10, 1, 0);

		horn_r3 = new ModelRenderer(this);
		horn_r3.setRotationPoint(0, -10, 4);
		horn.addChild(horn_r3);
		setRotationAngle(horn_r3, -0.5947F, -0.149F, -0.2161F);
		horn_r3.setTextureOffset(18, 11).addBox(-2.5F, -8, -0.5F, 1, 10, 1, 0);

		horn_r4 = new ModelRenderer(this);
		horn_r4.setRotationPoint(0, -10, 4);
		horn.addChild(horn_r4);
		setRotationAngle(horn_r4, -0.4597F, -0.4176F, -0.6863F);
		horn_r4.setTextureOffset(12, 56).addBox(-2.5F, -4, -0.5F, 1, 6, 1, 0);

		flb = new ModelRenderer(this);
		flb.setRotationPoint(0, 0, 0);
		
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.blb.render(f5);
		this.leftEar.render(f5);
		this.horn.render(f5);
		this.body.render(f5);
		this.blt.render(f5);
		this.neck.render(f5);
		this.flb.render(f5);
		this.brb.render(f5);
		this.tt.render(f5);
		this.frm.render(f5);
		this.frb.render(f5);
		this.tm.render(f5);
		this.brm.render(f5);
		this.frt.render(f5);
		this.brt.render(f5);
		this.flt.render(f5);
		this.mane.render(f5);
		this.flm.render(f5);
		this.rightEar.render(f5);
		this.head.render(f5);
		this.blm.render(f5);
		this.tb.render(f5);
		this.upperMouth.render(f5);
		this.lowerMouth.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		this.flt.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.flm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.flb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.blt.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.blm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.blb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.frt.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.brt.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.brm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.brb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		float thing = 0.1f;

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F + thing;

		this.horn.rotateAngleY = netHeadYaw * 0.017453292F;
		this.horn.rotateAngleX = headPitch * 0.017453292F + thing;

		this.upperMouth.rotateAngleY = netHeadYaw * 0.017453292F;
		this.upperMouth.rotateAngleX = headPitch * 0.017453292F + thing;
		this.lowerMouth.rotateAngleY = netHeadYaw * 0.017453292F;
		this.lowerMouth.rotateAngleX = headPitch * 0.017453292F + thing;

		this.rightEar.rotateAngleY = netHeadYaw * 0.017453292F;
		this.rightEar.rotateAngleX = headPitch * 0.017453292F + thing;
		this.leftEar.rotateAngleY = netHeadYaw * 0.017453292F;
		this.leftEar.rotateAngleX = headPitch * 0.017453292F + thing;

		this.rightEar.rotateAngleY = netHeadYaw * 0.017453292F;
		this.rightEar.rotateAngleX = headPitch * 0.017453292F + thing;
		this.leftEar.rotateAngleY = netHeadYaw * 0.017453292F;
		this.leftEar.rotateAngleX = headPitch * 0.017453292F + thing;

		this.neck.rotateAngleY = netHeadYaw * 0.017453292F;
		this.neck.rotateAngleX = headPitch * 0.017453292F + thing;

		this.mane.rotateAngleY = netHeadYaw * 0.017453292F;
		this.mane.rotateAngleX = headPitch * 0.017453292F + thing;
	}
}