package squixdev.achievementsplus.common;

import java.util.Hashtable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	
	public final static String EXT_PROP_NAME = "AchievementsPlusPlayerList";
	
	private final EntityPlayer player;
	
	Hashtable PlayerTriggeredAchievements;
	
	public ExtendedPlayer(EntityPlayer player, Hashtable defaultAchievements)
	{
		this.player = player;
		
		this.PlayerTriggeredAchievements = AchievementsPlus.AchievementsTriggered;
	}
	
	public static final void register(EntityPlayer player, Hashtable defaultAchievements)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player, defaultAchievements));
	}
	
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	// Save any custom data that needs saving here
		@Override
		public void saveNBTData(NBTTagCompound compound)
		{
			// We need to create a new tag compound that will save everything for our Extended Properties
			NBTTagCompound properties = new NBTTagCompound();
			
			// We only have 2 variables currently; save them both to the new tag
			properties.setString("PlayerTriggeredAchievements", this.PlayerTriggeredAchievements.toString());
			
			// Now add our custom tag to the player's tag with a unique name (our property's name)
			// This will allow you to save multiple types of properties and distinguish between them
			// If you only have one type, it isn't as important, but it will still avoid conflicts between
			// your tag names and vanilla tag names. For instance, if you add some "Items" tag,
			// that will conflict with vanilla. Not good. So just use a unique tag name.
			compound.setTag(EXT_PROP_NAME, properties);
			
		}

		// Load whatever data you saved
		@Override
		public void loadNBTData(NBTTagCompound compound)
		{
			// Here we fetch the unique tag compound we set for this class of Extended Properties
			NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
			// Get our data from the custom tag compound
			
			for(int i = 0; i < properties.getString("PlayerTriggeredAchievements").split(",").length; i++){
				
				String key = properties.getString("PlayerTriggeredAchievements").split(",")[i].split("=")[0];
				
				String value = properties.getString("PlayerTriggeredAchievements").split(",")[i].split("=")[1];

				//System.out.println(key);
				
				//System.out.println(value);

				this.PlayerTriggeredAchievements.put(key,value);
				
			}
	
		}
		
		/*
		I personally have yet to find a use for this method. If you know of any,
		please let me know and I'll add it in! 
		*/
		@Override
		public void init(Entity entity, World world)
		{
		}
		
		public Hashtable getList() {
			
			return this.PlayerTriggeredAchievements;
		}
	
	
}