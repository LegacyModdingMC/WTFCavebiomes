package cavebiomes.api;


import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import wtfcore.api.IStoneRegister;

public abstract class APICaveBiomes {

	public static APICaveBiomes CAVEBIOMESAPI;
	
	/**
	 * Use this to add new custom cave types to the generator.
	 * @param cavetype
	 * @param biome 
	 * @param depth : The depth at which the cave should spawn, must be 1 (shallow) 2 (mid) or 3 (deep)
	 */
	public abstract void addCaveType(CaveType cavetype, BiomeGenBase biome, int depth);

	public enum DungeonBiomeType {DEFAULT, FOREST, WET, SWAMP, DESERT, JUNGLE, COLD, VOLCANIC, MOUNTAIN};

	/**
	 * Adds a custom dungeon to the generator
	 * @param dungeon
	 * @param biometype: the type of biome you wish it to spawn in
	 * @return 
	 */
	public abstract void addDungeon(DungeonType dungeon, DungeonBiomeType biometype);
	
	/**
	 * This method returns a wrapper method of the information you give it, with a set of boolean variables
	 * To cancel the generation of a type of block, set it's boolean in the returned wrapper to false, all are true by default
	 * Then call the blocks register() method, to create and register all the child block types
	 * The wrapper then adds itself to a hashmap in WTFCore, which WTFOres can use to generate new stonetypes of ores for
	 * 
	 * @param Block stone
	 * @param Block cobblestone
	 * @param String unlocalisedName: this is the base string that will be used to create the unlocalised names of the block- it doesn't have to match the actual unlocalised name of either stone or cobblestone blocks
	 * @param stoneNames: A string array containing the texture names for the all metadata versions of the block
	 * @param domain: the mod domain that the block comes from
	 */
	public abstract IStoneRegister getStoneInfo(Block stone, Block cobblestone, String unlocalisedName, String[] stoneTextureNames, String[] cobbleTextureNames, String domain);


}
