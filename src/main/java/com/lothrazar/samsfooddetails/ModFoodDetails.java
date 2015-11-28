package com.lothrazar.samsfooddetails;

import net.minecraft.item.ItemFood;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModFoodDetails.MODID, useMetadata=true)
public class ModFoodDetails
{
    public static final String MODID = "samsfooddetails";
	@Instance(value = ModFoodDetails.MODID)
	public static ModFoodDetails instance;
    
    @EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{  
		MinecraftForge.EVENT_BUS.register(instance); 
	}
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event)
    {
    	//https://www.reddit.com/r/minecraftsuggestions/comments/3brh7v/when_hovering_over_a_food_it_shows_how_many_food/
    	//right now shows regardless of flag event.showAdvancedItemTooltips,... could change with config
    	if(event.itemStack != null && event.itemStack.getItem() instanceof ItemFood)
    	{
    		ItemFood food = (ItemFood)event.itemStack.getItem();
    		
    		int hunger = food.getHealAmount(event.itemStack);
    		float satur = food.getSaturationModifier(event.itemStack);
    		
    		event.toolTip.add(hunger+" ("+satur+")");
    	} 
    }
}
