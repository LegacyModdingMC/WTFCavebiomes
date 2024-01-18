package cavebiomes.utilities;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeAndHeight {

	public BiomeGenBase biome;
	public int depth;

	public static final int SHALLOW = 0;
	public static final int MID = 1;
	public static final int DEEP = 2;

	public BiomeAndHeight (BiomeGenBase biometoset, int Heightoset){
		this.biome = biometoset;
		this.depth = Heightoset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
		result = prime * result + ((biome == null) ? 0 : biome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BiomeAndHeight other = (BiomeAndHeight) obj;
		if (depth != other.depth) {
			return false;
		}
		if (biome == null) {
			if (other.biome != null) {
				return false;
			}
		} else if (!biome.equals(other.biome)) {
			return false;
		}
		return true;
	}

}
