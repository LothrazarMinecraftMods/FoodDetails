package com.lothrazar.samsfooddetails;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModFoodDetails.MODID, useMetadata = true, updateJSON = "https://raw.githubusercontent.com/LothrazarMinecraftMods/FoodDetails/master/update.json")
public class ModFoodDetails {
  public static final String MODID = "samsfooddetails";
  @Instance(value = ModFoodDetails.MODID)
  public static ModFoodDetails instance;
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(instance);
  }
  @SubscribeEvent
  public void onItemTooltipEvent(ItemTooltipEvent event) {
    // https://www.reddit.com/r/minecraftsuggestions/comments/3brh7v/when_hovering_over_a_food_it_shows_how_many_food/
    ItemStack itemStack = event.getItemStack();
    if (itemStack != null && itemStack.getItem() instanceof ItemFood) {
      ItemFood food = (ItemFood) itemStack.getItem();
      int hunger = food.getHealAmount(itemStack);
      float satur = food.getSaturationModifier(itemStack);
      event.getToolTip().add(hunger + " (" + satur + ")");
    }
  }
}
