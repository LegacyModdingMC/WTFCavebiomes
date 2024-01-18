package cavebiomes.worldgeneration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import cavebiomes.EventListener;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonType;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.WTFCore;
import wtfcore.utilities.DungeonBlockPosition;

public class CaveGen {
	public static void generateCaveType(CaveType cavetype, World world, Random random, int x, int floor, int ceiling, int z){
		//int center = floor + (ceiling - floor) / 2;

		cavetype.generateFloor(world, random, x, floor, z);
		cavetype.generateCeiling(world, random, x, ceiling, z);
		if (random.nextInt(100) < cavetype.ceilingaddonchance)
		{
			cavetype.generateCeilingAddons(world, random, x, ceiling - 1, z);
		}
	}

	public static void generateDungeon(CaveType cavetype, World world, Random random, int x, int z, int ceiling, int floor){

		DungeonType dungeon = cavetype.dungeons.getRandomDungeon(random);

		//Step1: loop outwards, and find a rough center of the open space
		//Get X
		int y = floor + (ceiling-floor)/2;
		int xpos = x+1;
		//WTFCore.log.info("x scan");
		boolean stop = false; //I have a canblockseesky for the dungeon generator- it was an attempt to solve the issue of dungeons generating above ground, I've since found a different bug, and have disabled the can see sky, as it shouldn't be necessary, and was very laggy
		for (int loop = 0;!stop && world.isAirBlock(xpos, y, z) && loop < dungeon.dungeonMaxSize ; loop++){
			//if (world.canBlockSeeTheSky(xpos, y, z)){stop = true;}
			xpos++;
		}
		int xneg = x-1;
		for (int loop = 0;!stop && world.isAirBlock(xneg, y, z) && loop < dungeon.dungeonMaxSize; loop++){
			//if (world.canBlockSeeTheSky(xneg, y, z)){stop = true;}
			xneg--;
		}
		int xrange = (xpos-xneg);
		if (xrange < dungeon.xClearance){return;}
		x=xneg+xrange/2;
		//WTFCore.log.info("z scan");
		//Get z
		int zpos = z+1;
		for (int loop = 0;!stop && world.isAirBlock(x, y, zpos) && loop < dungeon.dungeonMaxSize; loop++){
			//if (world.canBlockSeeTheSky(x, y, zpos)){stop = true;}
			zpos++;
		}
		int zneg = z-1;
		for (int loop = 0;!stop && world.isAirBlock(x, y, zneg) && loop < dungeon.dungeonMaxSize; loop++){
			//if (world.canBlockSeeTheSky(x, y, zneg)){stop = true;}
			zneg--;
		}
		int zrange = (zpos-zneg);
		if (zrange < dungeon.zClearance){return;}
		z=zneg+zrange/2;
		//get Y
		if (stop){return;} // cancels the dungeon generator if any of the blocks found could see the sky- should cut down on surface dungeons
		//WTFCore.log.info("y scan");
		int ypos = y+1;
		for (int loop = 0; world.isAirBlock(x, ypos, z) && loop < dungeon.dungeonMaxSize; loop++){
			ypos++;
		}
		int yneg = y-1;
		while (world.isAirBlock(x, yneg, z) && y > 0){
			//WTFCore.log.info(yneg);
			yneg--;
		}
		int yrange = (ypos-yneg);
		if (yrange < dungeon.yClearance || yrange > 2*dungeon.yClearance){return;}
		y=yneg+yrange/2;
		floor = yneg;
		ceiling = ypos;
		dungeon.wallStripe =y+1;
		//WTFCore.log.info("y scan finished");
		if (!dungeon.canSpawnHere(world, x, y, z, ceiling, floor)){return;}



		//WTFCore.log.info("Dungeon Space scan finished, and continuing to spawn");
		
		if(WTFCaveBiomesConfig.logDungeons){
			if (EventListener.thePlayer != null){
				EventListener.thePlayer.addChatMessage(new ChatComponentText("Generating " + dungeon.name + " @ " + x + " " + y + " " + z));
			}
			WTFCore.log.info("Generating Dungeon " + dungeon.name + " @ " + x + " " + floor + " " + z);
		}

		//so, it finds all the walls, then draws a box that big to make it's explosion
		//can I instead add all the blocks to a hashset, and then draw a ray to all the solid ones?
		HashSet<DungeonBlockPosition> hashset = new HashSet<DungeonBlockPosition>();
		HashSet<DungeonBlockPosition> air = new HashSet<DungeonBlockPosition>();

		int xmin = (0-xrange)/2;
		int xmax = xrange/2;
		int ymin = (0-yrange)/2;
		int ymax = yrange/2;
		int zmin = (0-zrange)/2;
		int zmax = zrange/2;

		double oriX = x + 0.5;
		double oriY = floor + (ceiling - floor)/2;
		double oriZ = z + 0.5;

		for (int xloop = xmin; xloop < xmax+1; ++xloop) {
			for (int yloop = ymin; yloop < ymax+1; ++yloop) {
				for (int zloop = zmin; zloop < zmax+1; ++zloop) {
					if (xloop==xmin || xloop==xmax || yloop==ymin || yloop==ymax || zloop==zmin || zloop == zmax){


						//the values to increment along the ray each loop
						double incX = (0 + xloop);
						double incY = (0 + yloop);
						double incZ = (0 + zloop);

						//length of the vector
						double vectorLength = Math.sqrt(incX * incX + incY * incY + incZ * incZ);


						//setting the values
						incX /= (vectorLength);
						incY /= (vectorLength);
						incZ /= (vectorLength);

						//origin


						int i = MathHelper.floor_double(oriX);
						int j = MathHelper.floor_double(oriY);
						int k = MathHelper.floor_double(oriZ);

						//problem: this is potentially running after stalactite generation
						for (int loop = 1; world.isAirBlock(i, j, k) && loop < vectorLength+1; loop++){
							air.add(new DungeonBlockPosition(i, j, k, false));
							i = MathHelper.floor_double(oriX+(incX*loop));
							j = MathHelper.floor_double(oriY+(incY*loop));
							k = MathHelper.floor_double(oriZ+(incZ*loop));

						}

						if (!world.isAirBlock(i, j, k)){
							hashset.add(new DungeonBlockPosition(i, j, k, false));
						}

					}
				}
			}
		}

		Iterator<DungeonBlockPosition> iterator= hashset.iterator();

		DungeonBlockPosition chunkposition;
		while (iterator.hasNext()) {
			chunkposition = iterator.next();
			int i = chunkposition.chunkPosX;
			int j = chunkposition.chunkPosY;
			int k = chunkposition.chunkPosZ;

			//gen.setBlockWithoutNotify(world, i, j, k, Blocks.gold_block, 0);


			if (j < oriY && !world.getBlock(i, j+1, k).renderAsNormalBlock())
			{
				//gen.setBlockWithoutNotify(world,i, j, k, Blocks.redstone_block, 0);
				dungeon.generateFloor(world, random, i, j, k);
			}
			else if ( j > oriY && !world.getBlock(i,  j-1, k).renderAsNormalBlock()){
				dungeon.generateCeiling(world, random, i, j, k);
				//gen.setBlockWithoutNotify(world, i, j, k, Blocks.gold_block, 0);
			}
			else {

				if (j ==dungeon.wallStripe && dungeon.generateWallStripe(world, random, i, j, k)){
					//the generator is called as a boolean- to make sure that if a dungeontype doesn't have a stripe, it doesn't skip that part of the wall
				}
				else {
					//gen.setBlockWithoutNotify(world, i, j, k, Blocks.iron_block, 0);
					dungeon.generateWalls(world, random, i, j, k);
				}
			}

		}

		dungeon.generateCenter(world, random, x, y, z, ceiling, floor);

		iterator = air.iterator();
		while (iterator.hasNext()){
			chunkposition = iterator.next();
			int i = chunkposition.chunkPosX;
			int j = chunkposition.chunkPosY;
			int k = chunkposition.chunkPosZ;

			dungeon.generateFill(world, random, i,j,k);
		}

	}



}
