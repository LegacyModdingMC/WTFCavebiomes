package cavebiomes.blocks.customplants;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.ILightDarkBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HangingFoxfire extends Foxfire implements ILightDarkBlock{
	 protected HangingFoxfire()
	    {
	        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
	        this.setTickRandomly(true);
	        if (WTFCaveBiomesConfig.foxfireGlow){
	        	this.setLightLevel(0.2F);
	        }
	    }



	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.brownIcon = iconRegister.registerIcon(CaveBiomes.modid + ":foxfire_hanging");
		this.greenIcon = iconRegister.registerIcon(CaveBiomes.modid + ":lit_foxfire_hanging");
	}
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{

		if (world.getBlockMetadata(x, y, z) < 8 && shouldBeLit(world, x, y, z)){
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)+8, 3);

		}
		else if (world.getBlockMetadata(x, y, z) > 7){
			//randomly turn off
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)-8, 3);
		}


		if (world.getBlockLightValue(x, y, z) > 10 || !canBlockStay(world,x,y,z)){
			world.setBlockToAir(x, y, z);
			this.dropBlockAsItem(world,x,y,z, world.getBlockMetadata(x, y, z),0);
			return;
		}

		//Try to spread
		if (WTFCaveBiomesConfig.foxFireSpread && rand.nextInt(25) == 0)
		{
//			System.out.println("try to grow");
			ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>();
			for (int xloop = -4; xloop <= 4; xloop++){
				for (int zloop = -4; zloop <= 4; zloop++){
					for (int yloop = -3; yloop <= 2; yloop++){// || world.isAirBlock(xloop, y+yloop, z); yloop++){
						if (canBlockStay(world, x+xloop, y+yloop-1, z+zloop)){
							arraylist.add(new ChunkPosition(x+xloop, y+yloop-1, z+zloop));
						}
					}
				}
			}
			ChunkPosition chunkposition = arraylist.get(rand.nextInt(arraylist.size()));
			world.setBlock(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, this, 0, 2);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x,y+1,z).getMaterial() == Material.wood;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{


		if (WTFCaveBiomesConfig.foxfireAnimations && random.nextInt(10) == 0 && world.getBlockMetadata(x,y,z) > 7 && shouldBeLit(world, x, y, z))
		{
			world.spawnParticle("happyVillager", x + random.nextFloat(), y + 0.5F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
		}
	}

}
