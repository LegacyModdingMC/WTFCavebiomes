package cavebiomes.items;

import cavebiomes.CaveBiomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class CustomArmor extends ItemArmor{

	public String texture;
	
	public CustomArmor(ArmorMaterial material, int armorType, String textureName) {
		super(material, 0, armorType);
		this.texture = textureName;	
	}

    @SideOnly(Side.CLIENT)
    protected String getIconString()
    {
        return "CaveBiomes:"+this.getUnlocalizedName().substring(5);
    }
   	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return CaveBiomes.modid + ":textures/armor/" + texture + "_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {

    }

}