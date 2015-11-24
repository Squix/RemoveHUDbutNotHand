package squixdev.removehudbutnothand.common;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ProxyCommon {
	
	@SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(RemoveHUDbutNotHand.showWelcomeMessage) {
        	event.player.addChatMessage(new ChatComponentText("[RemoveHUDbutNotHand] " + EnumChatFormatting.RED + "Don't forget to look for updates at " + EnumChatFormatting.GOLD + EnumChatFormatting.UNDERLINE + RemoveHUDbutNotHand.MODURL ));
        	event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " -|- If you downloaded this mod from another website, it could be a malware !" ));
        	event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " -|- The website cited above was the only "+ EnumChatFormatting.GREEN + "TRUSTED" + EnumChatFormatting.RED + " one."));
        	event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " -|- You can disable this message in the config." + EnumChatFormatting.RESET));

        	
        }
    }

	protected Configuration config;
	
	 protected void initConfig(FMLPreInitializationEvent event)
	    {
		 Minecraft.getMinecraft().gameSettings.heldItemTooltips = true;
   	  System.out.println(Minecraft.getMinecraft().gameSettings.heldItemTooltips);
	        // might need to use suggestedConfigFile (event.getSuggestedConfigFile) location to publish
		    RemoveHUDbutNotHand.configFile = event.getSuggestedConfigurationFile();
	        // DEBUG
	        System.out.println(RemoveHUDbutNotHand.MODNAME+" config path = "+RemoveHUDbutNotHand.configFile.getAbsolutePath());
	        System.out.println("Config file exists = "+RemoveHUDbutNotHand.configFile.canRead());
	        
	        config = new Configuration(RemoveHUDbutNotHand.configFile);
	        RemoveHUDbutNotHand.config = config;

	        updateConfig();
	    }


	public void updateConfig() {

		// loading the configuration from its file
		config.load();

		config.defaultEncoding = "UTF-8";

		RemoveHUDbutNotHand.hideHealth = config.getBoolean("hideHealth", "ThingsToHide", true, "Hide Player's Health Bar");
		RemoveHUDbutNotHand.hideAir = config.getBoolean("hideAir", "ThingsToHide", true, "Hide Player's SubWater Air Bar");
		RemoveHUDbutNotHand.hideHealthMount = config.getBoolean("hideHealthMount", "ThingsToHide", true, "Hide Player's Mount Health Bar");
		RemoveHUDbutNotHand.hideJumpBar = config.getBoolean("hideJumpBar", "ThingsToHide", true, "Hide Player's Mount Jump Bar");
		RemoveHUDbutNotHand.hideFood = config.getBoolean("hideFood", "ThingsToHide", true, "Hide Player's Food Bar");
		RemoveHUDbutNotHand.hideArmor = config.getBoolean("hideArmor", "ThingsToHide", true, "Hide Player's Armor");
		RemoveHUDbutNotHand.hideHelmet = config.getBoolean("hideHelmet", "ThingsToHide", true, "Hide 'Helmet' layer (Pumpkins,...)");
		RemoveHUDbutNotHand.hideCrosshairs = config.getBoolean("hideCrosshairs", "ThingsToHide", true, "Hide Player's Crosshairs");
		RemoveHUDbutNotHand.hideExp = config.getBoolean("hideExp", "ThingsToHide", true, "Hide Player's Experience Bar");
		RemoveHUDbutNotHand.hideHotbar = config.getBoolean("hideHotbar", "ThingsToHide", true, "Hide Player's Hotbar");
		RemoveHUDbutNotHand.hideBossHealth = config.getBoolean("hideBossHealth", "ThingsToHide", true, "Hide Bosses' Health Bar");
		RemoveHUDbutNotHand.hideChat = config.getBoolean("hideChat", "ThingsToHide", true, "Hide In-Game Chat");

		RemoveHUDbutNotHand.showWelcomeMessage = config.getBoolean("showWelcomeMessage", "ThingsToHide", true, "Show the mod's welcome message when the Player Log-inta a world");
		RemoveHUDbutNotHand.hideItemHeldTooltip = config.getBoolean("hideItemHeldTooltip", "ThingsToHide", true, "Hide text when holding an item");
		RemoveHUDbutNotHand.checkVersion = config.getBoolean("checkVersion", "ThingsToHide", true, "Check if there's an update [NEED RESTARTING]");

		
		// saving the configuration to its file
		config.save();

	}


}
