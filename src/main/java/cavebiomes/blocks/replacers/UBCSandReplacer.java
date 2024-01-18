package cavebiomes.blocks.replacers;

import cavebiomes.blocks.UBCSand;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import wtfcore.api.BlockInfo;
import wtfcore.api.Replacer;
import wtfcore.utilities.UBCblocks;

public class UBCSandReplacer extends Replacer{

	public UBCSandReplacer(Block block) {
		super(block);
	}

	@Override
	public void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock){
		BlockInfo ubcblock = getUBCStone(chunk.worldObj,x,y,z);
		if (ubcblock.block.hashCode() == UBCblocks.SedimentaryStone.hashCode()){
			setBlockWithoutNotify(chunk.worldObj, x, y, z, UBCSand.sedimentarySand, ubcblock.meta);
		}
	}
}
