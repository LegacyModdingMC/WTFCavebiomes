package cavebiomes.blocks.customplants;

import java.util.ArrayList;
import java.util.Random;

import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.blocks.BlockMagmaCrust;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import wtfcore.api.BlockSets.Modifier;

public class CinderShroom extends BlockBush{

	 protected CinderShroom()
	    {
	        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.7F, 0.7F);
	        this.setTickRandomly(true);
	        	if (WTFCaveBiomesConfig.cindershroomGlow){
	        		setLightLevel(0.5F);

	        }
	    }



	public static Block cinderShroom;

	public static void register(){
		String name = "CinderShroom";
		cinderShroom = new CinderShroom().setBlockName(name).setHardness(2F).setTickRandomly(true);
		GameRegistry.registerBlock(cinderShroom, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(CaveBiomes.modid + ":cindershroom");

	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x,y,z)-1, 3);
		world.scheduleBlockUpdate(x, y, z, this, 300);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity p_149724_5_) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x,y,z)-1, 3);
		world.scheduleBlockUpdate(x, y, z, this, 300);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.getBlockMetadata(x, y, z) >0 ){
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x,y,z)-1, 3);
			if (world.getBlockMetadata(x, y, z) >1 ){
			world.scheduleBlockUpdate(x, y, z, this, rand.nextInt(200)+100);
			}
		}


		else{

			if (world.getBlockLightValue(x, y, z) > 10 || !canBlockStay(world,x,y,z)){
				world.setBlockToAir(x, y, z);
				this.dropBlockAsItem(world,x,y,z, world.getBlockMetadata(x, y, z),0);
				return;
			}

			//Try to spread
			if (WTFCaveBiomesConfig.cindershroomSpread && rand.nextInt(100) == 0)
			{
//				System.out.println("try to grow");
				ArrayList arraylist = new ArrayList();
				for (int xloop = -4; xloop <= 4; xloop++){
					for (int zloop = -4; zloop <= 4; zloop++){
						for (int yloop = 3; yloop >= -2; yloop--){// || world.isAirBlock(xloop, y+yloop, z); yloop--){
							
							if (y+yloop > 255 || y+yloop <= 0 ) {continue;}

							if (world.getBlock(x+xloop, y+yloop-1, z+zloop) == Blocks.lava && world.isAirBlock(x+xloop, y+yloop, z+zloop)){
								arraylist.add(new ChunkPosition(x+xloop, y+yloop, z+zloop));
							}
						}
					}
				}

				if (arraylist.size() >0){
					ChunkPosition chunkposition = (ChunkPosition) arraylist.get(rand.nextInt(arraylist.size()));
					world.setBlock(chunkposition.chunkPosX, chunkposition.chunkPosY-1, chunkposition.chunkPosZ, BlockSets.blockTransformer.get(new BlockInfo(Blocks.stone, 0, Modifier.stoneMagmaCrust)), 0, 2);
				//	maybe set a detector for UBC magma crust setting here
					world.setBlock(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, this, 0, 2);
				}
			}
		}


	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if(canBlockStay(world, x, y, z)){
		world.setBlockMetadataWithNotify(x, y, z, 15, 3);
		world.scheduleBlockUpdate(x, y, z, this, 30000);
		}
		else{
		this.checkAndDropBlock(world, x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x,y-1,z) instanceof BlockMagmaCrust;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (!WTFCaveBiomesConfig.cindershroomAnimations){return;}
		int str =16-world.getBlockMetadata(x,y,z);

		if (random.nextInt(str)==0){
			world.spawnParticle("smoke", x+0.5F, y + 0.5F, z + 0.5F, 0.0D, 0.0D, 0.0D);
		}
		if (random.nextInt(2*str)-1==0){
			world.spawnParticle("largesmoke", x+0.5F, y + 0.5F, z + 0.5F, 0.0D, 0.0D, 0.0D);
		}
		if (random.nextInt(3*str)-2==0){
			world.spawnParticle("lava", x+0.5F, y + 0.5F, z + 0.5F, 0.0D, 0.0D, 0.0D);
		}

	}



}
