package cavebiomes.items;


import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import cavebiomes.CaveBiomes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMoss extends Item{

	public static Item moss;

	public ItemMoss()
	{
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}

	public static void register(){
		moss = new ItemMoss().setUnlocalizedName("moss");
		GameRegistry.registerItem(moss, "moss");
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone, 1, 0), new Object[] {new ItemStack(moss, 1), new ItemStack(Blocks.cobblestone, 1)});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(CaveBiomes.modid + ":" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block blockToSet = BlockSets.blockTransformer.get(new BlockInfo(world.getBlock(x,y,z), world.getBlockMetadata(x,y,z), BlockSets.Modifier.MossyStone));
		if (blockToSet != null){
			world.setBlock(x, y, z, blockToSet, world.getBlockMetadata(x,y,z), 3);
			if(!player.capabilities.isCreativeMode) {
				player.inventory.consumeInventoryItem(moss);
			}
			return true;
		}
		blockToSet = BlockSets.blockTransformer.get(new BlockInfo(world.getBlock(x,y,z), world.getBlockMetadata(x,y,z), BlockSets.Modifier.mossy_cobblestone));
		if (blockToSet != null){
			world.setBlock(x, y, z, blockToSet, world.getBlockMetadata(x,y,z), 3);
			return true;
		}
		return false;
    }




}
