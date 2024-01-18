package cavebiomes;

import cavebiomes.api.APICaveBiomes;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonType;
import cavebiomes.blocks.CaveBlocks;
import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.utilities.StoneRegister;
import cavebiomes.worldgeneration.CaveTypeRegister;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRegister;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import wtfcore.WTFCore;

public class CaveBiomesAPI extends APICaveBiomes {
	
	/**
	 * Use this to add new custom cave types to the generator.
	 * @param cavetype
	 * @param biome 
	 * @param depth : The depth at which the cave should spawn, must be 1 (shallow) 2 (mid) or 3 (deep)
	 */
	@Override
	public void addCaveType(CaveType cavetype, BiomeGenBase biome, int depth){
		if (depth >0 && depth < 4){
			CaveTypeRegister.cavebiomemap.put(new BiomeAndHeight(biome, depth), cavetype);
		}
		else {
			WTFCore.log.info("CaveBiomesAPI: CaveType had depth outside acceptable range " + cavetype.name);
		}
	}
		
	/**
	 * Adds a custom dungeon to the generator
	 * @param dungeon
	 * @param biometype: the type of biome you wish it to spawn in
	 */
	@Override
	public void addDungeon(DungeonType dungeon, DungeonBiomeType biometype){
		if (biometype == DungeonBiomeType.DEFAULT){
			DungeonTypeRegister.defaultSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.FOREST){
			DungeonTypeRegister.forestSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.WET){
			DungeonTypeRegister.wetSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.SWAMP){
			DungeonTypeRegister.swampSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.DESERT){
			DungeonTypeRegister.desertSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.JUNGLE){
			DungeonTypeRegister.jungleSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.COLD){
			DungeonTypeRegister.coldSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.VOLCANIC){
			DungeonTypeRegister.volcanicSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.MOUNTAIN){
			DungeonTypeRegister.mountainSet.addDungeon(dungeon);
		}
	}
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
	@Override
	public  StoneRegister getStoneInfo(Block stone, Block cobblestone, String unlocalisedName, String[] stoneTextureNames, String[] cobbleTextureNames, String domain){
		return CaveBlocks.getStoneRegister(stone, cobblestone, unlocalisedName, stoneTextureNames, cobbleTextureNames, domain);
	}




	
	
}
