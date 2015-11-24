package squixdev.achievementsplus.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProxyCommon {
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			
			//AchievementsPlus.AchievementsTriggered = ExtendedPlayer.get((EntityPlayer)event.entity).getList();
			
		}
	}
	
	public void initAchievements() {
		
       for (int i = 0; i < net.minecraft.stats.AchievementList.achievementList.size(); i++ ) { 
			
			Object achievement = net.minecraft.stats.AchievementList.achievementList.get(i);
			
			AchievementsPlus.AchievementsTriggered.put(achievement.toString().split(",")[0].replace("Stat{id=", "").replace("achievement.", "").trim() ,"false"); 
		
		  }
	}

}
