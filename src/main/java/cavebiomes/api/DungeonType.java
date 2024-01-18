package cavebiomes.api;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import wtfcore.api.GenCoreBase;

public class DungeonType{

	protected Random random = new Random();

	public Block modifier;
	public int spawncounter;
	public int wallStripe;
	
	//These are the required clear radius for generation- to make a larger area required, override these
	public int xClearance = 6;
	public int yClearance = 4;
	public int zClearance = 6;
	
	public int dungeonMaxSize = 6;

	public String name;

	public static GenCoreBase gen;

	public DungeonType(String name){
		this.name = name;
	}
	
	
	/**
	 * Checks for special conditions- if you're using blocks from other mods, you should add a check here for the mod being installed to prevent null crashes
	 */
	public boolean canSpawnHere(World world, int x, int y, int z, int ceiling, int floor) {
		return true;
	}

	/**
	 * Generates blocks for the ceiling
	 */
	public void generateCeiling(World world, Random rand, int x, int y, int z){	}

	/**
	 * generates blocks on the floor
	 */
	public void generateFloor(World world, Random rand, int x, int y, int z){}
/**
 *  generates blocks on the wall
 */
	public void generateWalls(World world, Random rand, int x, int y, int z){}

	/**
	 * called once, at the center of the dungeon, used to generate mob spawners and the like
	 */
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor){}

	/**
	 * generates fill blocks- used by cave in and frozen dungeons, to replace all air blocks within the dungeon with whatever is supplied.
	 **/
	public void generateFill(World world, Random rand, int x, int y, int z){

	}
	/**
	 * generates a stripe along the wall, 1 block below the ceiling
	 **/
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		return false;
	}



}
