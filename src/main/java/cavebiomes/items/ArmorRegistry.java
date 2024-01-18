package cavebiomes.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorRegistry {

	public static ArmorMaterial CLOTHARMOR = EnumHelper.addArmorMaterial("CLOTH", 1, new int[] {0, 1, 1, 0}, 5);
	
	public static Item mummyHelmet;
	public static Item mummyChestplate;
	public static Item mummyLeggings;
	public static Item mummyBoots;
	
	public static Item pharaohHelmet;
	public static Item pharaohChestplate;
	public static Item pharaohLeggings;
	public static Item pharaohBoots;	


	
	public static void registerArmorTypes(){
		
		mummyHelmet = new CustomArmor(CLOTHARMOR, 0, "mummy").setUnlocalizedName("mummy_helmet");
		GameRegistry.registerItem(mummyHelmet, "mummy_helmet"); //0 for helmet
		
		mummyChestplate = new CustomArmor(CLOTHARMOR, 1, "mummy").setUnlocalizedName("mummy_chestplate");
		GameRegistry.registerItem(mummyChestplate, "mummy_chestplate"); // 1 for chestplate
		
		mummyLeggings = new CustomArmor(CLOTHARMOR, 2, "mummy").setUnlocalizedName("mummy_leggings");
		GameRegistry.registerItem(mummyLeggings, "mummy_leggings"); // 2 for leggings
		
		mummyBoots = new CustomArmor(CLOTHARMOR, 3, "mummy").setUnlocalizedName("mummy_boots");
		GameRegistry.registerItem(mummyBoots, "mummy_boots"); // 3 for boots
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 1, 15), new Object[]{mummyHelmet});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 3, 15), new Object[]{mummyChestplate});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 2, 15), new Object[]{mummyLeggings});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 1, 15), new Object[]{mummyBoots});
		
		pharaohHelmet = new CustomArmor(ArmorMaterial.GOLD, 0, "pharaoh").setUnlocalizedName("pharaoh_helmet");
		GameRegistry.registerItem(pharaohHelmet, "pharaoh_helmet"); //0 for helmet
		
		pharaohChestplate = new CustomArmor(ArmorMaterial.GOLD, 1, "pharaoh").setUnlocalizedName("pharaoh_chestplate");
		GameRegistry.registerItem(pharaohChestplate, "pharaoh_chestplate"); // 1 for chestplate
		
		pharaohLeggings = new CustomArmor(ArmorMaterial.GOLD, 2, "pharaoh").setUnlocalizedName("pharaoh_leggings");
		GameRegistry.registerItem(pharaohLeggings, "pharaoh_leggings"); // 2 for leggings
		
		pharaohBoots = new CustomArmor(ArmorMaterial.GOLD, 3, "pharaoh").setUnlocalizedName("pharaoh_boots");
		GameRegistry.registerItem(pharaohBoots, "pharaoh_boots"); // 3 for boots

	}
	
}
