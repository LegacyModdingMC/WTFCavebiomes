package cavebiomes.blocks.replacers;

import cavebiomes.blocks.CaveBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import wtfcore.api.Replacer;
import wtftweaks.WTFBlocks;

public class FrozenTorchReplacer extends Replacer {

	public FrozenTorchReplacer(Block block) {
		super(block);
	}
	
	@Override
	public void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock) {
		if (y < 58){
			if ( BiomeDictionary.isBiomeOfType(chunk.worldObj.getBiomeGenForCoords(x, z), Type.SNOWY)){
				setBlockWithoutNotify(chunk.worldObj, x,y,z, CaveBlocks.frozenTorch, chunk.worldObj.getBlockMetadata(x, y, z));
			}
			else {
				setBlockWithoutNotify(chunk.worldObj, x,y,z, WTFBlocks.finitetorch_unlit, chunk.worldObj.getBlockMetadata(x, y, z));
			}
		}
	}
}
