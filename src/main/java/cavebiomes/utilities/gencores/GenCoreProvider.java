package cavebiomes.utilities.gencores;

import cpw.mods.fml.common.Loader;

public class GenCoreProvider {

	public static VanillaGen getGenCore(){
		if (Loader.isModLoaded("UndergroundBiomes")){
			return new UBCGen();
		}
		else{
			return new VanillaGen();
		}
	}
	
}
