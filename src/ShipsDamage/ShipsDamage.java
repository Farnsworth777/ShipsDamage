package ShipsDamage;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ShipsDamage extends JavaPlugin {

	JavaPlugin PLUGIN;

	public static final File CONFIG = new File("plugins/ShipsDamage/Config.yml");

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new SDListeners(), this);
		if (!CONFIG.exists()) {
			SDDatabase.writeToConfig("DamageThreshold", 60);
		}
	}


}
