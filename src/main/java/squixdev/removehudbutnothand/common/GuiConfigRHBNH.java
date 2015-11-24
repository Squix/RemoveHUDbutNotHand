package squixdev.removehudbutnothand.common;
import squixdev.removehudbutnothand.client.ProxyClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiMessageDialog;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

public class GuiConfigRHBNH extends GuiConfig 
{
	public GuiConfigRHBNH(GuiScreen parent) 
	{
		super(parent,
				new ConfigElement(
						RemoveHUDbutNotHand.config.getCategory("thingstohide"))
		.getChildElements(),
		RemoveHUDbutNotHand.MODID, 
		false, 
		false, 
		EnumChatFormatting.GREEN+"Choose which element to hide or not when using RemoveHUDButNotHand");
		titleLine2 = RemoveHUDbutNotHand.config.getConfigFile().getAbsolutePath();
	}

	@Override
	public void initGui()
	{
		// You can add buttons and initialize fields here
		super.initGui();
	}


	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		// You can do things like create animations, draw additional elements, etc. here
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 2000)
		{
			// DEBUG
			//System.out.println("Pressed DONE button");
			boolean flag = true;
			try
			{
				if ((configID != null || this.parentScreen == null || !(this.parentScreen instanceof GuiConfig)) 
						&& (this.entryList.hasChangedEntry(true)))
				{
					// DEBUG
					//System.out.println("Saving config elements");
					boolean requiresMcRestart = this.entryList.saveConfigElements();

					if (Loader.isModLoaded(modID))
					{
						//ConfigChangedEvent event = new ConfigChangedEvent(modID, configID, isWorldRunning, requiresMcRestart);
                        //FMLCommonHandler.instance().bus().post(event);
						
						// DEBUG
						
						
							//System.out.println("Syncing config for mod = Remove HUD but Not Hand");
							RemoveHUDbutNotHand.config.save();
							RemoveHUDbutNotHand.proxy.updateConfig();
						

						if (requiresMcRestart)
						{
							flag = false;
							mc.displayGuiScreen(new GuiMessageDialog(parentScreen, "fml.configgui.gameRestartTitle", 
									new ChatComponentText(I18n.format("fml.configgui.gameRestartRequired")), "fml.configgui.confirmRestartMessage"));
						}

						if (this.parentScreen instanceof GuiConfig)
							((GuiConfig) this.parentScreen).needsRefresh = true;
					}
				}
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}

			if (flag)
				this.mc.displayGuiScreen(this.parentScreen);
		}
	}
}