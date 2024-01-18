package cavebiomes.blocks.customplants;

import java.util.ArrayList;
import java.util.Random;

import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.renderers.RenderRegisterer;
import cavebiomes.utilities.ILightDarkBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Foxfire extends BlockBush implements ILightDarkBlock
{
    public static Block blockFoxfire;
    public static Block hangingFoxfire;
    public static Random staticRandom = new Random();

    IIcon brownIcon;
    IIcon greenIcon;

    protected Foxfire()
    {
        float f = 0.2F;
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
        this.setTickRandomly(true);
        if (WTFCaveBiomesConfig.foxfireGlow){
        	this.setLightLevel(0.2F);
        }
    }

    //Foxfire- it's a fungus that grows in the dark, on top of wood.

    public static void register(){
    	String name = "foxfire";
		blockFoxfire = new Foxfire().setBlockName(name);
		GameRegistry.registerBlock(blockFoxfire, name);

		name = "hanging_foxfire";
		hangingFoxfire = new HangingFoxfire().setBlockName(name);
		GameRegistry.registerBlock(hangingFoxfire, name);

    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return brownIcon;
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.brownIcon = iconRegister.registerIcon(CaveBiomes.modid + ":foxfire");
		this.greenIcon = iconRegister.registerIcon(CaveBiomes.modid + ":lit_Foxfire");
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	world.scheduleBlockUpdate(x, y, z, this, staticRandom.nextInt(80)+20);
    	return false;
    }


    @Override
	public void onEntityWalking(World world, int x, int y, int z, Entity p_149724_5_) {
    	world.scheduleBlockUpdate(x, y, z, this, staticRandom.nextInt(80)+20);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	if(!world.isRemote){
    		if (world.getBlockMetadata(x, y, z) < 8 && world.getBlockLightValue(x, y, z) > 4){
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
    		if (rand.nextInt(250) == 0)
    		{
    			ArrayList arraylist = new ArrayList();
    			for (int xloop = -4; xloop < 5; xloop++){
    				for (int zloop = -4; zloop < 5; zloop++){
    					for (int yloop = 3; yloop > -3 || world.isAirBlock(xloop, y+yloop, z); yloop--){
    						if (canBlockStay(world, x+xloop, y+yloop+1, z+zloop)){
    							arraylist.add(new ChunkPosition(x+xloop, y+yloop+1, z+zloop));
    						}
    					}
    				}
    			}

    			ChunkPosition chunkposition = (ChunkPosition) arraylist.get(rand.nextInt(arraylist.size()));
    			world.setBlock(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, this, 0, 2);
    		}
    	}
    }

    @Override
	public boolean canBlockStay(World world, int x, int y, int z)
    {
    	return world.getBlock(x,y-1,z).getMaterial() == Material.wood;
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

    @Override
    public int getRenderType()
    {
         return RenderRegisterer.LightDarkRenderType;
    }

	@Override
	public IIcon getLitIcon() {
		return greenIcon;
	}

	@Override
	public IIcon getUnlitIcon() {
		return brownIcon;
	}


	@Override
	public boolean isPlant() {
		return true;
	}

	@Override
	public int daylightThreshold() {
		return 2;
	}

	@Override
	public int torchlightThreshold() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public boolean shouldBeLit(IBlockAccess world, int x, int y, int z) {

		//metadata less than 8 disables the block lighting

		//this was using skyblocks, I've changed it to get block light value in an attempt to stop servers from crashing.
			int brightness = world.getBlock(x, y, z).getLightValue(world, x, y, z);
			//if (brightness > 15<<4){//it's outdoors
			//	return brightness>>20 > this.daylightThreshold();
			//}
			//else { //it's indoors- no daylight detected
				return brightness>>4 < torchlightThreshold();
			//}



	}


}
