package cavebiomes.blocks.replacers;

import cavebiomes.blocks.CaveBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import wtfcore.api.Replacer;

public class FrozenRailReplacer extends Replacer{

	public FrozenRailReplacer(Block block) {
		super(block);
	}

	@Override
	public void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock) {
		if (y < 58 && BiomeDictionary.isBiomeOfType(chunk.worldObj.getBiomeGenForCoords(x, z), Type.SNOWY)){
			setBlockWithoutNotify(chunk.worldObj, x,y,z, CaveBlocks.frozenRail, chunk.worldObj.getBlockMetadata(x, y, z));
		}
		
	}

}
