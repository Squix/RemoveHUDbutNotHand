package squixdev.removehudbutnothand.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import org.lwjgl.input.Keyboard;

import squixdev.removehudbutnothand.common.ProxyCommon;
import squixdev.removehudbutnothand.common.RemoveHUDbutNotHand;

public class ProxyClient extends ProxyCommon
{
	
	private static KeyBinding keyBindTest;
	
	public ProxyClient()
	{
		keyBindTest = new KeyBinding("rhbnh.toggle", Keyboard.KEY_F8, "key.categories.misc");
		ClientRegistry.registerKeyBinding(keyBindTest);
		
	}
	
	@SubscribeEvent
	public void onEvent(KeyInputEvent event)
	{
		if(keyBindTest.isPressed())
		{
			keyTestTyped();
		}
	}
	
	private void keyTestTyped()
	{
		/*
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideHealth: " + RemoveHUDbutNotHand.hideHealth);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideFood: " + RemoveHUDbutNotHand.hideFood);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideCrosshairs: " + RemoveHUDbutNotHand.hideCrosshairs);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideExp: " + RemoveHUDbutNotHand.hideExp);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideHotbar: " + RemoveHUDbutNotHand.hideHotbar);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideChat: " + RemoveHUDbutNotHand.hideChat);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideBossHealth: " + RemoveHUDbutNotHand.hideBossHealth);
		Minecraft.getMinecraft().thePlayer.sendChatMessage("hideItemHeldTooltip: " + RemoveHUDbutNotHand.hideItemHeldTooltip);
		*/
		
		if(RemoveHUDbutNotHand.hideItemHeldTooltip){
			
			Minecraft.getMinecraft().gameSettings.heldItemTooltips = !Minecraft.getMinecraft().gameSettings.heldItemTooltips;
		} 
		
		RemoveHUDbutNotHand.hideHud = !RemoveHUDbutNotHand.hideHud;
		
	}
	
	public static void versionCheck() {
		
		RemoveHUDbutNotHand.versionChecker = new VersionChecker();
		Thread versionCheckThread = new Thread(RemoveHUDbutNotHand.versionChecker, "Version Check");
		versionCheckThread.start();
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PlayerTickEvent event)
	{
	  
	    if (!RemoveHUDbutNotHand.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote 
	          && !RemoveHUDbutNotHand.versionChecker.isLatestVersion())
	    {
	        ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, 
	              "http://squixdev.appspot.com/ModsMinecraft/RemoveHUDbutNotHand/index.html");
	        ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
	        ChatComponentText versionWarningChatComponent = new ChatComponentText("[RemoveHUDbutNotHand]" + EnumChatFormatting.YELLOW + " A new version is available. Click here to update.");
	        versionWarningChatComponent.setChatStyle(clickableChatStyle);
	        event.player.addChatMessage(versionWarningChatComponent);
	        RemoveHUDbutNotHand.haveWarnedVersionOutOfDate = true;
	    }
	  
	}


}