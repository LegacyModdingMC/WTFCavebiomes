package cavebiomes.entities;

import cavebiomes.CaveBiomes;
import cavebiomes.entities.Entities.CustomMobTypes;
import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpawnerBlock extends BlockAir implements ITileEntityProvider{

	public CustomMobTypes mobType;
	
	protected SpawnerBlock(Entities.CustomMobTypes mobType) {
		super();
		this.mobType = mobType;
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);

	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new SpawnerBlockEntity(); 	
	}

}
