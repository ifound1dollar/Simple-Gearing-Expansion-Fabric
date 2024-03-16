package net.dollar.simplegear;

import net.dollar.simplegear.block.ModBlocks;
import net.dollar.simplegear.block.entity.ModBlockEntities;
import net.dollar.simplegear.item.ModItemGroups;
import net.dollar.simplegear.item.ModItems;
import net.dollar.simplegear.util.ModModelPredicateProvider;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMain implements ModInitializer {
	public static final String MOD_ID = "simplegear";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModTileEntities();
		ModModelPredicateProvider.registerModModels();

		//FUEL ITEMS
		FuelRegistry.INSTANCE.add(ModItems.MOLTEN_CORE, 30000);	//25 minutes
		//FuelRegistry.INSTANCE.add(ModItems.COAL_COKE, 1600);	//80 seconds (DEFAULT FOR COAL/CHARCOAL)
	}
}