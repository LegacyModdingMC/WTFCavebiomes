package cavebiomes.blocks.customplants;

import cavebiomes.CaveBiomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockVine;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class LavaVine extends BlockVine{

    public LavaVine(){
    	super();
    	this.setHardness(0.2F).setStepSound(soundTypeGrass);
    	this.setCreativeTab(CaveBiomes.tabCaveDecorations);
    }
	
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    protected String getTextureName()
    {
        return "minecraft:vine";
    }
	
	
}
