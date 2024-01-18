package cavebiomes.utilities.gencores;

import net.minecraft.block.Block;
import wtfcore.api.Replacer;

public class BlockAndConditions {

	public Block newblock;
	public Replacer conditions;
	
	public BlockAndConditions(Block block, Replacer cond){
		newblock = block;
		conditions = cond;
	}
	
}
