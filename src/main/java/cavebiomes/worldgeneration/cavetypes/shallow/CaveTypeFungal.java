package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeFungal extends CaveType{

	
	public CaveTypeFungal(int cavedepth, DungeonSet swampSet) {
		super("Fungal", cavedepth, swampSet);
		
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		//int rootheight = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z) % 10) -5) + (random.nextInt(2)-6);
		int height = MathHelper.abs_int((MathHelper.abs_int(x) % 10) -5)+MathHelper.abs_int((MathHelper.abs_int(z/2) % 10) -5)+ (random.nextInt(3)+3);

		//create mycellium based root structures, possibly using surface to find giant mushrooms

		if (height > 10){
			gen.replaceBlock(world, x, y, z, Blocks.mycelium, 0);
			if (MathHelper.abs_int(x+y)%100 < 4){
				gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.brown_mushroom, 0);
			}
			else {
				gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.red_mushroom, 0);
			}
		}
		else {
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}




}
