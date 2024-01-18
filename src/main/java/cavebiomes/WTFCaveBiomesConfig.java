package cavebiomes;

import java.io.File;

import cavebiomes.api.ConfigAPI;
import net.minecraftforge.common.config.Configuration;

public class WTFCaveBiomesConfig{


	public static boolean EnableMobSpawners;

	public static boolean enableDrippingBlocks;
	public static int ceilingAddonChance;
	public static int floorAddonChance;
	//public static boolean enableMossyStone;
	public static int dungeonChance;

	//public static int magmaCrustGenRate;

	public static boolean foxfireGlow;
	public static boolean foxfireAnimations;
	public static boolean cindershroomGlow;
	public static boolean cindershroomAnimations;
	public static boolean lavacrustGlow;
	public static boolean lavacrustAnimations;

	public static boolean generateCaveSubtypes;
	public static boolean logDungeons;
	public static boolean customMobs;
	public static boolean enableUBCSand;
	public static boolean ReplaceVanillaSand;
	
	public static void customConfig() {

		Configuration config = new Configuration(new File("config/WTFCaveBiomesConfig.cfg"));

		config.load();
		String section1 = "Cave Subtype and Dungeon Generation";
		EnableMobSpawners = config.get(section1, "EnableMobSpawners", true).getBoolean();
		dungeonChance = config.get(section1, "Subtype rarity- Set higher for fewer cave subtypes, lower for more", 3000).getInt();
		logDungeons=config.get(section1, "Log subtypes in chat", false).getBoolean();

		String section2 = "Cave Generation Options";
		ceilingAddonChance = config.get(section2, "base frequency of stalactites, and other ceiling addons", 5).getInt();
		floorAddonChance = config.get(section2, "base frequency of stalagmite, and other floor addons", 5).getInt();

		String section3 = "Block Options";
		//enableMossyStone = config.get(section3, "Enable Additional Mossy Stone Blocks", true).getBoolean();
		enableDrippingBlocks = config.get(section3, "Enable modded dripping blocks", true).getBoolean();
		foxfireGlow = config.get(section3, "Enable foxfire glowing", true).getBoolean();
		foxfireAnimations = config.get(section3, "Enable foxfire animations", true).getBoolean();
		cindershroomGlow = config.get(section3, "Enable cindershroom glowing", true).getBoolean();
		cindershroomAnimations = config.get(section3, "Enable cindershroom animations", true).getBoolean();
		lavacrustGlow = config.get(section3, "Enable Lava Crust Glows", true).getBoolean();
		lavacrustAnimations = config.get(section3, "Enable Lava Crust Animations", true).getBoolean();
		enableUBCSand = config.get(section3, "Enable sand for UBC Sedimentary Stones", true).getBoolean();
		ReplaceVanillaSand = config.get(section3, "Replace Sand with Sedimentary Sand based on UBC stone biomes", true).getBoolean();

		//magmaCrustGenRate =  config.get("Ore style generation", "Generation rate of magma crust as ore- to disable set to 0", 15).getInt();

		String section4 = "Cave Subtype and Dungeon Generation";
		generateCaveSubtypes = config.get(section4, "Generate Cave Subtypes and dungeons", true).getBoolean();
		
		String section5 = "Entity Settings";
		customMobs = config.get(section5, "Enable replacement of zombies and skeletons with biome specific versions", false).getBoolean();

		config.save();
		
		ConfigAPI.EnableMobSpawners = EnableMobSpawners;
		ConfigAPI.ceilingAddonChance = ceilingAddonChance;
		ConfigAPI.floorAddonChance = floorAddonChance;
		ConfigAPI.dungeonChance = dungeonChance;
		ConfigAPI.generateCaveSubtypes = generateCaveSubtypes;
		
	}
}
