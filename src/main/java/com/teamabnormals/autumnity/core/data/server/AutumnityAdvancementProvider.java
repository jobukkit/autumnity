package com.teamabnormals.autumnity.core.data.server;

import com.teamabnormals.autumnity.common.advancements.criterion.FeedSnailTrigger;
import com.teamabnormals.autumnity.core.Autumnity;
import com.teamabnormals.autumnity.core.other.AutumnityCriteriaTriggers;
import com.teamabnormals.autumnity.core.registry.AutumnityItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class AutumnityAdvancementProvider extends AdvancementProvider {

	public AutumnityAdvancementProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, fileHelper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
		createAdvancement("sneaky_protection", "husbandry", new ResourceLocation("husbandry/breed_an_animal"), AutumnityItems.SNAIL_SHELL_CHESTPLATE.get(), FrameType.TASK, true, true, false)
				.addCriterion("snail_shell_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(AutumnityItems.SNAIL_SHELL_CHESTPLATE.get()))
				.save(consumer, Autumnity.MOD_ID + ":husbandry/sneaky_protection");

		createAdvancement("cure_foul_taste", "husbandry", new ResourceLocation("husbandry/plant_seed"), AutumnityItems.FOUL_BERRIES.get(), FrameType.TASK, true, true, false)
				.addCriterion("cure_foul_taste", AutumnityCriteriaTriggers.CURE_FOUL_TASTE.createInstance())
				.save(consumer, Autumnity.MOD_ID + ":husbandry/cure_foul_taste");

		createAdvancement("fungivore_diet", "husbandry", new ResourceLocation("husbandry/breed_an_animal"), AutumnityItems.SNAIL_SHELL_CHESTPLATE.get(), FrameType.CHALLENGE, true, true, false)
				.addCriterion("red_mushroom", FeedSnailTrigger.TriggerInstance.forItem(Items.RED_MUSHROOM))
				.addCriterion("brown_mushroom", FeedSnailTrigger.TriggerInstance.forItem(Items.BROWN_MUSHROOM))
				.addCriterion("crimson_fungus", FeedSnailTrigger.TriggerInstance.forItem(Items.CRIMSON_FUNGUS))
				.addCriterion("warped_fungus", FeedSnailTrigger.TriggerInstance.forItem(Items.WARPED_FUNGUS))
				.save(consumer, Autumnity.MOD_ID + ":husbandry/fungivore_diet");
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
				new TranslatableComponent("advancements." + Autumnity.MOD_ID + "." + category + "." + name + ".title"),
				new TranslatableComponent("advancements." + Autumnity.MOD_ID + "." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}
}