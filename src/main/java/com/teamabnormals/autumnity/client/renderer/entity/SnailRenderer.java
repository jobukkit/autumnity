package com.teamabnormals.autumnity.client.renderer.entity;

import com.teamabnormals.autumnity.client.renderer.entity.model.SnailModel;
import com.teamabnormals.autumnity.common.entity.animal.SnailEntity;
import com.teamabnormals.autumnity.core.Autumnity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnailRenderer extends MobRenderer<SnailEntity, SnailModel<SnailEntity>> {
	private static final ResourceLocation SNAIL_TEXTURES = new ResourceLocation(Autumnity.MOD_ID, "textures/entity/snail/snail.png");
	private static final ResourceLocation SNAKE_SNAIL_TEXTURES = new ResourceLocation(Autumnity.MOD_ID, "textures/entity/snail/snake_snail.png");
	private static final ResourceLocation NAUTILUS_SNAIL_TEXTURES = new ResourceLocation(Autumnity.MOD_ID, "textures/entity/snail/nautilus_snail.png");

	public SnailRenderer(EntityRendererProvider.Context context) {
		super(context, new SnailModel<>(context.bakeLayer(SnailModel.SNAIL_MODEL)), 0.5F);
	}

	public ResourceLocation getTextureLocation(SnailEntity entity) {
		String s = ChatFormatting.stripFormatting(entity.getName().getString().toLowerCase());
		if (s != null) {
			if ("snake".equals(s) || "snakeblock".equals(s) || "snake block".equals(s) || "snailblock".equals(s) || "snail block".equals(s)) {
				return SNAKE_SNAIL_TEXTURES;
			} else if ("nautilus".equals(s) || "nautilus snail".equals(s)) {
				return NAUTILUS_SNAIL_TEXTURES;
			}
		}

		return SNAIL_TEXTURES;
	}

	protected void setupRotations(SnailEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		double d0 = entityLiving.getShakingAnim(partialTicks);
		double d1 = entityLiving.getShakingAnimTicks() > 0 ? 1.0D : -1.0D;
		double d2 = Math.sin(6.3D * d0) * d1 * d0;
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.0F * (float) d2));
	}
}