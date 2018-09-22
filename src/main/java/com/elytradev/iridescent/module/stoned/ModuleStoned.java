package com.elytradev.iridescent.module.stoned;

import java.util.Set;

import com.elytradev.iridescent.Goal;
import com.elytradev.iridescent.module.Module;
import com.google.common.collect.ImmutableSet;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class ModuleStoned extends Module {

	@Override
	public String getName() {
		return "Stoned";
	}

	@Override
	public String getDescription() {
		return "Makes granite, diorite, and andesite actually useful.";
	}

	@Override
	public Set<Goal> getGoals() {
		return ImmutableSet.of(Goal.IMPROVE_VANILLA);
	}
	
	public static ItemSword COBBLESTONE_SWORD;
	public static ItemHoe COBBLESTONE_HOE;
	public static ItemPickaxe COBBLESTONE_PICKAXE;
	public static ItemAxe COBBLESTONE_AXE;
	public static ItemSpade COBBLESTONE_SHOVEL;
	
	public static ItemSword GRANITE_SWORD;
	public static ItemHoe GRANITE_HOE;
	public static ItemPickaxe GRANITE_PICKAXE;
	public static ItemAxe GRANITE_AXE;
	public static ItemSpade GRANITE_SHOVEL;
	
	public static ItemSword ANDESITE_SWORD;
	public static ItemHoe ANDESITE_HOE;
	public static ItemPickaxe ANDESITE_PICKAXE;
	public static ItemAxe ANDESITE_AXE;
	public static ItemSpade ANDESITE_SHOVEL;
	
	public static ItemSword DIORITE_SWORD;
	public static ItemHoe DIORITE_HOE;
	public static ItemPickaxe DIORITE_PICKAXE;
	public static ItemAxe DIORITE_AXE;
	public static ItemSpade DIORITE_SHOVEL;
	
	public static ItemSword FLINT_SWORD;
	public static ItemHoe FLINT_HOE;
	public static ItemPickaxe FLINT_PICKAXE;
	public static ItemAxe FLINT_AXE;
	public static ItemSpade FLINT_SHOVEL;
	
	@Override
	public void onPreInit(FMLPreInitializationEvent e) {
		OreDictionary.registerOre("cobblestone", new ItemStack(Blocks.STONE, 1, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(Blocks.STONE, 1, 3));
		OreDictionary.registerOre("cobblestone", new ItemStack(Blocks.STONE, 1, 5));

		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onPostInit(FMLPostInitializationEvent e) {
		for (IRecipe recipe : ForgeRegistries.RECIPES.getValues()) {
			if (recipe.getRecipeOutput().getItem() == Items.STONE_SWORD ||
					recipe.getRecipeOutput().getItem() == Items.STONE_HOE ||
					recipe.getRecipeOutput().getItem() == Items.STONE_PICKAXE ||
					recipe.getRecipeOutput().getItem() == Items.STONE_AXE ||
					recipe.getRecipeOutput().getItem() == Items.STONE_SHOVEL) {
				((IForgeRegistryModifiable<IRecipe>)ForgeRegistries.RECIPES).remove(ForgeRegistries.RECIPES.getKey(recipe));
			}
		}
	}
	
	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(COBBLESTONE_SWORD = ((ItemSword)new ItemSword(ToolMaterial.STONE)
				.setUnlocalizedName("swordStone")
				.setRegistryName("cobblestone_sword")));
		e.getRegistry().register(COBBLESTONE_HOE = ((ItemHoe)new ItemHoe(ToolMaterial.STONE)
				.setUnlocalizedName("hoeStone")
				.setRegistryName("cobblestone_hoe")));
		e.getRegistry().register(COBBLESTONE_PICKAXE = ((ItemPickaxe)new ItemPickaxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("pickaxeStone")
				.setRegistryName("cobblestone_pickaxe")));
		e.getRegistry().register(COBBLESTONE_AXE = ((ItemAxe)new ItemAxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("hatchetStone")
				.setRegistryName("cobblestone_axe")));
		e.getRegistry().register(COBBLESTONE_SHOVEL = ((ItemSpade)new ItemSpade(ToolMaterial.STONE)
				.setUnlocalizedName("shovelStone")
				.setRegistryName("cobblestone_shovel")));
		
		e.getRegistry().register(GRANITE_SWORD = ((ItemSword)new ItemSword(ToolMaterial.STONE)
				.setUnlocalizedName("swordStone")
				.setRegistryName("granite_sword")));
		e.getRegistry().register(GRANITE_HOE = ((ItemHoe)new ItemHoe(ToolMaterial.STONE)
				.setUnlocalizedName("hoeStone")
				.setRegistryName("granite_hoe")));
		e.getRegistry().register(GRANITE_PICKAXE = ((ItemPickaxe)new ItemPickaxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("pickaxeStone")
				.setRegistryName("granite_pickaxe")));
		e.getRegistry().register(GRANITE_AXE = ((ItemAxe)new ItemAxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("hatchetStone")
				.setRegistryName("granite_axe")));
		e.getRegistry().register(GRANITE_SHOVEL = ((ItemSpade)new ItemSpade(ToolMaterial.STONE)
				.setUnlocalizedName("shovelStone")
				.setRegistryName("granite_shovel")));
		
		e.getRegistry().register(ANDESITE_SWORD = ((ItemSword)new ItemSword(ToolMaterial.STONE)
				.setUnlocalizedName("swordStone")
				.setRegistryName("andesite_sword")));
		e.getRegistry().register(ANDESITE_HOE = ((ItemHoe)new ItemHoe(ToolMaterial.STONE)
				.setUnlocalizedName("hoeStone")
				.setRegistryName("andesite_hoe")));
		e.getRegistry().register(ANDESITE_PICKAXE = ((ItemPickaxe)new ItemPickaxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("pickaxeStone")
				.setRegistryName("andesite_pickaxe")));
		e.getRegistry().register(ANDESITE_AXE = ((ItemAxe)new ItemAxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("hatchetStone")
				.setRegistryName("andesite_axe")));
		e.getRegistry().register(ANDESITE_SHOVEL = ((ItemSpade)new ItemSpade(ToolMaterial.STONE)
				.setUnlocalizedName("shovelStone")
				.setRegistryName("andesite_shovel")));
		
		e.getRegistry().register(DIORITE_SWORD = ((ItemSword)new ItemSword(ToolMaterial.STONE)
				.setUnlocalizedName("swordStone")
				.setRegistryName("diorite_sword")));
		e.getRegistry().register(DIORITE_HOE = ((ItemHoe)new ItemHoe(ToolMaterial.STONE)
				.setUnlocalizedName("hoeStone")
				.setRegistryName("diorite_hoe")));
		e.getRegistry().register(DIORITE_PICKAXE = ((ItemPickaxe)new ItemPickaxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("pickaxeStone")
				.setRegistryName("diorite_pickaxe")));
		e.getRegistry().register(DIORITE_AXE = ((ItemAxe)new ItemAxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("hatchetStone")
				.setRegistryName("diorite_axe")));
		e.getRegistry().register(DIORITE_SHOVEL = ((ItemSpade)new ItemSpade(ToolMaterial.STONE)
				.setUnlocalizedName("shovelStone")
				.setRegistryName("diorite_shovel")));
		
		e.getRegistry().register(FLINT_SWORD = ((ItemSword)new ItemSword(ToolMaterial.STONE)
				.setUnlocalizedName("swordStone")
				.setRegistryName("flint_sword")));
		e.getRegistry().register(FLINT_HOE = ((ItemHoe)new ItemHoe(ToolMaterial.STONE)
				.setUnlocalizedName("hoeStone")
				.setRegistryName("flint_hoe")));
		e.getRegistry().register(FLINT_PICKAXE = ((ItemPickaxe)new ItemPickaxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("pickaxeStone")
				.setRegistryName("flint_pickaxe")));
		e.getRegistry().register(FLINT_AXE = ((ItemAxe)new ItemAxe(ToolMaterial.STONE) {}
				.setUnlocalizedName("hatchetStone")
				.setRegistryName("flint_axe")));
		e.getRegistry().register(FLINT_SHOVEL = ((ItemSpade)new ItemSpade(ToolMaterial.STONE)
				.setUnlocalizedName("shovelStone")
				.setRegistryName("flint_shovel")));
	}
	
	@SubscribeEvent
	public void onRegisterRecipes(RegistryEvent.Register<IRecipe> e) {
		ItemStack cobble = new ItemStack(Blocks.COBBLESTONE, 1, 0);
		ItemStack granite = new ItemStack(Blocks.STONE, 1, 1);
		ItemStack diorite = new ItemStack(Blocks.STONE, 1, 3);
		ItemStack andesite = new ItemStack(Blocks.STONE, 1, 5);
		
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_SWORD,
				" # ",
				" # ",
				" / ",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_sword"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_HOE,
				"##",
				" /",
				" /",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_hoe"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_HOE,
				"##",
				"/ ",
				"/ ",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_hoe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_PICKAXE,
				"###",
				" / ",
				" / ",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_pickaxe"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_AXE,
				"##",
				"#/",
				" /",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_axe"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_AXE,
				"##",
				"/#",
				"/ ",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_axe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, COBBLESTONE_SHOVEL,
				"#",
				"/",
				"/",
				
				'#', cobble,
				'/', "stickWood"
				).setRegistryName("cobblestone_shovel"));
		
		
		
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_SWORD,
				" # ",
				" # ",
				" / ",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_sword"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_HOE,
				"##",
				" /",
				" /",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_hoe"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_HOE,
				"##",
				"/ ",
				"/ ",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_hoe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_PICKAXE,
				"###",
				" / ",
				" / ",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_pickaxe"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_AXE,
				"##",
				"#/",
				" /",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_axe"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_AXE,
				"##",
				"/#",
				"/ ",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_axe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, GRANITE_SHOVEL,
				"#",
				"/",
				"/",
				
				'#', granite,
				'/', "stickWood"
				).setRegistryName("granite_shovel"));
		
		
		
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_SWORD,
				" # ",
				" # ",
				" / ",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_sword"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_HOE,
				"##",
				" /",
				" /",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_hoe"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_HOE,
				"##",
				"/ ",
				"/ ",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_hoe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_PICKAXE,
				"###",
				" / ",
				" / ",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_pickaxe"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_AXE,
				"##",
				"#/",
				" /",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_axe"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_AXE,
				"##",
				"/#",
				"/ ",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_axe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, ANDESITE_SHOVEL,
				"#",
				"/",
				"/",
				
				'#', andesite,
				'/', "stickWood"
				).setRegistryName("andesite_shovel"));
		
		
		
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_SWORD,
				" # ",
				" # ",
				" / ",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_sword"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_HOE,
				"##",
				" /",
				" /",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_hoe"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_HOE,
				"##",
				"/ ",
				"/ ",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_hoe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_PICKAXE,
				"###",
				" / ",
				" / ",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_pickaxe"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_AXE,
				"##",
				"#/",
				" /",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_axe"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_AXE,
				"##",
				"/#",
				"/ ",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_axe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, DIORITE_SHOVEL,
				"#",
				"/",
				"/",
				
				'#', diorite,
				'/', "stickWood"
				).setRegistryName("diorite_shovel"));
		
		
		
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_SWORD,
				" # ",
				" # ",
				" / ",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_sword"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_HOE,
				"##",
				" /",
				" /",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_hoe"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_HOE,
				"##",
				"/ ",
				"/ ",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_hoe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_PICKAXE,
				"###",
				" / ",
				" / ",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_pickaxe"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_AXE,
				"##",
				"#/",
				" /",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_axe"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_AXE,
				"##",
				"/#",
				"/ ",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_axe_flipped"));
		e.getRegistry().register(new ShapedOreRecipe(null, FLINT_SHOVEL,
				"#",
				"/",
				"/",
				
				'#', Items.FLINT,
				'/', "stickWood"
				).setRegistryName("flint_shovel"));
	}

}
