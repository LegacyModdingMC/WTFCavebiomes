package cavebiomes.worldgeneration;

import java.util.HashMap;

import cavebiomes.api.CaveType;
import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeDeepIce;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeVolcanic;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeWetDeep;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeDesertMid;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeMidIce;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeNormalMid;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeWetMid;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeConifer;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeForest;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeFungal;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeIceMountain;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeJungleVolcano;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeMountain;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeNormalShallow;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypePlains;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeSandy;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeShallowIce;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeSwamp;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeWetShallow;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CaveTypeRegister {

	public static HashMap<BiomeAndHeight, CaveType> cavebiomemap = new HashMap <BiomeAndHeight, CaveType>();


	//jacko lantern
	//mineshaft, nether portal, mushroom, cave in,
	//carved stone, carved sandstone

	//shallow caves
	public static CaveType normalShallow = new CaveTypeNormalShallow(1, DungeonTypeRegister.defaultSet);
	public static CaveType wetShallow =new CaveTypeWetShallow (1, DungeonTypeRegister.wetSet);
	public static CaveType swamp = new CaveTypeSwamp (1, DungeonTypeRegister.swampSet);
	public static CaveType sandyShallow = new CaveTypeSandy (1, DungeonTypeRegister.desertSet);
	public static CaveType jungleVolcano = new CaveTypeJungleVolcano (1, DungeonTypeRegister.jungleSet);
	public static CaveType mountains = new CaveTypeMountain (1, DungeonTypeRegister.mountainSet);
	public static CaveType iceMountain = new CaveTypeIceMountain (1, DungeonTypeRegister.icemountainSet);
	public static CaveType iceShallow = new CaveTypeShallowIce (1, DungeonTypeRegister.coldSet);
	public static CaveType fungal = new CaveTypeFungal (1, DungeonTypeRegister.swampSet);
	public static CaveType plains = new CaveTypePlains (1, DungeonTypeRegister.defaultSet);
	public static CaveType coniferous = new CaveTypeConifer (1, DungeonTypeRegister.forestSet);
	public static CaveType forest = new CaveTypeForest(1, DungeonTypeRegister.forestSet);

	// mid caves
	public static CaveType normalMid = new CaveTypeNormalMid(2, DungeonTypeRegister.defaultSet);
	public static CaveType wetMid = new CaveTypeWetMid (2, DungeonTypeRegister.wetSet);
	public static CaveType iceMid = new CaveTypeMidIce (2, DungeonTypeRegister.coldSet);
	public static CaveType desertMid = new CaveTypeDesertMid (2, DungeonTypeRegister.desertSet);

	//Deep caves
	public static CaveType wetDeep = new CaveTypeWetDeep (3, DungeonTypeRegister.wetSet);
	public static CaveType iceDeep = new CaveTypeDeepIce (3, DungeonTypeRegister.coldSet);
	//public static CaveType desertDeep = new CaveTypeDesertDeep ("DesertDeep", 3, volcaniclist);
	public static CaveType volcanic = new CaveTypeVolcanic (3,DungeonTypeRegister. volcanicSet);



	public static CaveType getCaveType(BiomeAndHeight biomeandheight){
		CaveType cavetype = cavebiomemap.get(biomeandheight);
		if (cavetype == null){
			putBiomeAndHeightInMap(biomeandheight);
			cavetype = cavebiomemap.get(biomeandheight);
			
		}
		return cavetype;
		//return  iceShallow;
	}



	public static CaveType GetShallowCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.SHALLOW));
	}
	public static CaveType GetMidCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.MID));
	}
	public static CaveType GetDeepCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.DEEP));
	}

	public static void putBiomeAndHeightInMap(BiomeAndHeight biomeandheight){

		CaveType cavetypetoset = null;

		//ShallowCaveTypes
		if (biomeandheight.depth == BiomeAndHeight.SHALLOW){

			cavetypetoset = normalShallow;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = wetShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SWAMP)){cavetypetoset = swamp;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MESA)){cavetypetoset = plains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = sandyShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.JUNGLE)){cavetypetoset = jungleVolcano;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MOUNTAIN)){cavetypetoset = mountains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MOUNTAIN)&&BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceMountain;}
			
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MUSHROOM)){cavetypetoset = fungal;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.PLAINS)){cavetypetoset = plains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.FOREST)){
				if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.CONIFEROUS)){cavetypetoset =coniferous;}
				else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.LUSH)){cavetypetoset =swamp;}
				else if (!BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.JUNGLE) && !BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SAVANNA)){
					cavetypetoset = forest;
				}
			}

		}
		//Mid-depth cave types
		else if (biomeandheight.depth == BiomeAndHeight.MID){
			//int depth = 2;
			cavetypetoset = normalMid;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = wetMid;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceMid;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = desertMid;}
			//
		}

		//Deep cave types
		else if (biomeandheight.depth == BiomeAndHeight.DEEP){
			//int depth = 3;
			cavetypetoset = volcanic;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceDeep;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = wetDeep;}
			//else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = desertDeep;}
			//add deep sandy
		}


		if (cavetypetoset != null){
			cavebiomemap.put(biomeandheight, cavetypetoset);

		}
		else {
			System.out.println("Cave Type height outside parameters");
		}
	}

	
}
