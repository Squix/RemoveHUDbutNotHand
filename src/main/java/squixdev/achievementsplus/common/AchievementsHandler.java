package squixdev.achievementsplus.common;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementsHandler {
	
	Hashtable<String, String> AchievementsTriggered = AchievementsPlus.AchievementsTriggered;
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onAchievement(AchievementEvent event)
	{
		//AchievementsTriggered = ExtendedPlayer.get(event.entityPlayer).getList();
		
		AchievementsTriggered = AchievementsPlusWorldData.get(event.entity.worldObj).getPlayerAchievements(event.entityPlayer);
		
		//System.out.print(AchievementsTriggered);
		
		event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + " -A|+- Achèvement reçuuu :" + event.achievement.statId + EnumChatFormatting.RESET));
	
		Integer xpToGive = 0;
		
		Boolean gotIt = false;
		
		String achievementGet = "";
				
		if(event.achievement == AchievementList.openInventory && AchievementsTriggered.get("openInventory") == "false") {
			
			xpToGive = 3;
			
			gotIt = true;
			
			achievementGet = "Ouvrir l'inventaire";
			
			event.entityPlayer.addExperience(3);
			
			AchievementsTriggered.put("openInventory", "true");
			
		}
		
		if(gotIt) {
			
			event.entity.worldObj.playSoundAtEntity(event.entityPlayer, "achievementsplus:achievementget", 1.0F, 1.0F);
			
			MinecraftServer.getServer().addChatMessage(new ChatComponentText("-A|+- " + event.entityPlayer.getName() + " a reçu le succès | " + achievementGet + " | !" ));
			
			event.entityPlayer.addChatMessage(new ChatComponentText(" -A|+- " + event.entityPlayer.getName() + " a reçu le succès | " + achievementGet + " | !" ));
		    
			event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + " -A|+- Vous avez reçu " + xpToGive + " XP" + EnumChatFormatting.RESET));
		 
			AchievementsPlusWorldData.get(event.entity.worldObj).setPlayerAchievements(event.entityPlayer, AchievementsTriggered);
		 
		}
		
		event.setCanceled(true);
				
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		/*
		Be sure to check if the entity being constructed is the correct type
		for the extended properties you're about to add!
		The null check may not be necessary - I only use it to make sure
		properties are only registered once per entity
		*/
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
			// This is how extended properties are registered using our convenient method from earlier
			ExtendedPlayer.register((EntityPlayer) event.entity, AchievementsPlus.AchievementsTriggered);
			// That will call the constructor as well as cause the init() method
			// to be called automatically
		
		// If you didn't make the two convenient methods from earlier, your code would be
		// much uglier:
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity, AchievementsPlus.AchievementsTriggered));
	}
	
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		NBTTagCompound compound = new NBTTagCompound();
		ExtendedPlayer.get(event.original).saveNBTData(compound);
		ExtendedPlayer.get(event.entityPlayer).loadNBTData(compound);
	}
	
	
			
    

}
