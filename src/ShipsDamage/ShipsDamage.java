package ShipsDamage;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import MoseShipsBukkit.Events.ShipMovingEvent;
import MoseShipsBukkit.StillShip.Vessel.MovableVessel;
import MoseShipsBukkit.StillShip.Vessel.Vessel;

public class ShipsDamage extends JavaPlugin implements Listener{
	
	JavaPlugin PLUGIN;
	
	public static final File CONFIG = new File("plugins/ShipsDamage/Config.yml");
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){
		
	}
	
	@EventHandler
	public void onShipMove(ShipMovingEvent event){
		MovableVessel vessel = event.getVessel();
		int currentsize = vessel.getStructure().getAllBlocks().size();
		int fullsize = Integer.parseInt(getStringFromVesselDB(vessel, "FullSize"));
		if ((currentsize/fullsize)*100 < Integer.parseInt(getString("totaled"))) {
			event.setCancelled(true);
		}
	}
	public static void writeToVesselDB(Vessel vessel, String location, Object write){
		File file = vessel.getFile();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set(location, write);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getStringFromVesselDB(MovableVessel vessel, String location){
		File file = vessel.getFile();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		String ret = config.getString(location);
		return ret;
	}
	
	public static void writeToConfig(String location, Object write){
		YamlConfiguration config = YamlConfiguration.loadConfiguration(CONFIG);
		config.set(location, write);
		try {
			config.save(CONFIG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getString(String location){
		YamlConfiguration config = YamlConfiguration.loadConfiguration(CONFIG);
		String ret = config.getString(location);
		return ret;
	}

}
