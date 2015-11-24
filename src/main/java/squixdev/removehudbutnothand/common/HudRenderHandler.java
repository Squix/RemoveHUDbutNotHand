package squixdev.removehudbutnothand.common;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.GuiIngameModOptions;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.client.GuiModOptionList;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HudRenderHandler
{
	private Minecraft mc;

	private String s;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onHudRender(RenderGameOverlayEvent event)
	{

		if(RemoveHUDbutNotHand.hideHud && Minecraft.getMinecraft().currentScreen == null) {

			if (event.type == ElementType.HEALTH && RemoveHUDbutNotHand.hideHealth)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.AIR && RemoveHUDbutNotHand.hideAir)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.HEALTHMOUNT && RemoveHUDbutNotHand.hideHealthMount)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.FOOD && RemoveHUDbutNotHand.hideFood)
			{
				event.setCanceled(true);
			}	
			else if (event.type == ElementType.ARMOR && RemoveHUDbutNotHand.hideArmor)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.HELMET && RemoveHUDbutNotHand.hideHelmet)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.JUMPBAR && RemoveHUDbutNotHand.hideJumpBar)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.CROSSHAIRS && RemoveHUDbutNotHand.hideCrosshairs)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.EXPERIENCE && RemoveHUDbutNotHand.hideExp)
			{
				event.setCanceled(true);
			}	
			else if (event.type == ElementType.HOTBAR && RemoveHUDbutNotHand.hideHotbar)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.BOSSHEALTH && RemoveHUDbutNotHand.hideBossHealth)
			{
				event.setCanceled(true);
			}
			else if (event.type == ElementType.CHAT && RemoveHUDbutNotHand.hideChat)
			{
				event.setCanceled(true);
			}
			
			
			

		}

	}
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiIngameModOptions)
        {
        	event.gui = new GuiModList(null);        
        }
    }

}