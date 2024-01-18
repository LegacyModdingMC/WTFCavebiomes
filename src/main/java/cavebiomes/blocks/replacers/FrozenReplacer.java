package cavebiomes.blocks.replacers;

import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import wtfcore.api.Replacer;

public class FrozenReplacer extends Replacer {

    private Block replacement;

    public FrozenReplacer(Block target, Block replacement) {
        super(target);
        this.replacement = replacement;
    }

    @Override
    public void doReplace(Chunk chunk, int x, int y, int z, Block oldBlock) {
        if (y < 58 && BiomeDictionary.isBiomeOfType(chunk.worldObj.getBiomeGenForCoords(x, z), BiomeDictionary.Type.SNOWY)) {
            setBlockWithoutNotify(chunk.worldObj, x,y,z, replacement, chunk.worldObj.getBlockMetadata(x, y, z));
        }
    }

}
