package squixdev.achievementsplus.common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class AchievementsPlusWorldData extends WorldSavedData {

	private static final String IDENTIFIER = "AchievementsPlusPlayerList";
	
	private NBTTagCompound prop = new NBTTagCompound();
	
	Hashtable AllPlayersTriggeredAchievements;
	
	Hashtable PlayerTriggeredAchievements;
	
	public AchievementsPlusWorldData() {
		super(IDENTIFIER);
	}
	
	public AchievementsPlusWorldData(String identifier) {
		super(identifier);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		 prop = compound.getCompoundTag(IDENTIFIER);
		
	if(prop.getString("AllPlayersTriggeredAchievements") != null) {
       for(int i = 0; i < prop.getString("AllPlayersTriggeredAchievements").split(",").length; i++){
			
			String key = prop.getString("AllPlayersTriggeredAchievements").split(",")[i].split("=")[0];
			
			String value = prop.getString("AllPlayersTriggeredAchievements").split(",")[i].split("=")[1];

			//System.out.println(key);
			
			//System.out.println(value);

			this.AllPlayersTriggeredAchievements.put(key,value);
			
		}
	} else {
		

		List Players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		
		for(int i = 0; i < Players.size(); i++) {
			
			EntityPlayer playerUUID = (EntityPlayer)Players.get(i);
			
			this.AllPlayersTriggeredAchievements.put(playerUUID.getUniqueID(),AchievementsPlus.AchievementsTriggered);
			
			prop.setString("AllPlayersTriggeredAchievements", this.AllPlayersTriggeredAchievements.toString());
			
			markDirty();
		}
		
		System.out.println(this.AllPlayersTriggeredAchievements.toString());
	
	}
	
	
	
    }

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		
		 prop.setString("AllPlayersTriggeredAchievements", this.AllPlayersTriggeredAchievements.toString());
		 compound.setTag(IDENTIFIER, prop);
		 markDirty();
	}
	
	public Hashtable getPlayerAchievements(EntityPlayer player) {
		
		 return (Hashtable) this.AllPlayersTriggeredAchievements.get(player.getUniqueID());
	}
	
	public void setPlayerAchievements(EntityPlayer player, Hashtable givenPlayerAchievements) {
		
		this.AllPlayersTriggeredAchievements.put(player, givenPlayerAchievements);
		
		markDirty();
	}
	
	
	public static AchievementsPlusWorldData get(World world) {
		AchievementsPlusWorldData data = (AchievementsPlusWorldData)world.loadItemData(AchievementsPlusWorldData.class, IDENTIFIER);
		if (data == null) {
			data = new AchievementsPlusWorldData();
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}
}