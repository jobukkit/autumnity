package com.teamabnormals.autumnity.core.data.client;

import com.teamabnormals.autumnity.core.Autumnity;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.autumnity.core.registry.AutumnityItems.*;

public class AutumnityItemModelProvider extends ItemModelProvider {

	public AutumnityItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Autumnity.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(MAPLE_BOAT.get(), MAPLE_CHEST_BOAT.get(), MAPLE_FURNACE_BOAT.get(), LARGE_MAPLE_BOAT.get());
		this.generatedItem(SAP_BOTTLE.get(), SYRUP_BOTTLE.get(), FOUL_BERRIES.get(), FOUL_BERRY_PIPS.get(), FOUL_SOUP.get(), PUMPKIN_BREAD.get());
		this.generatedItem(SNAIL_SHELL_PIECE.get(), SNAIL_SHELL_CHESTPLATE.get(), TURKEY_EGG.get());
		this.handheldItem(TURKEY_PIECE.get(), COOKED_TURKEY_PIECE.get());
		this.generatedItem(MAPLE_LEAF_BANNER_PATTERN.get(), SWIRL_BANNER_PATTERN.get());
		this.spawnEggItem(SNAIL_SPAWN_EGG.get(), TURKEY_SPAWN_EGG.get());
	}

	private void generatedItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "generated");
	}

	private void handheldItem(ItemLike... items) {
		for (ItemLike item : items)
			item(item, "handheld");
	}

	private void item(ItemLike item, String type) {
		ResourceLocation itemName = item.asItem().getRegistryName();
		withExistingParent(itemName.getPath(), "item/" + type).texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
	}

	private void spawnEggItem(ItemLike... items) {
		for (ItemLike item : items) {
			ResourceLocation itemName = item.asItem().getRegistryName();
			withExistingParent(itemName.getPath(), "item/template_spawn_egg");
		}
	}

	private void blockItem(Block block) {
		ResourceLocation name = block.getRegistryName();
		this.getBuilder(name.getPath()).parent(new UncheckedModelFile(new ResourceLocation(this.modid, "block/" + name.getPath())));
	}
}