package ShipsDamage;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import MoseShipsBukkit.Ships.VesselTypes.DataTypes.LiveData;
import MoseShipsBukkit.Ships.VesselTypes.Loading.ShipsLocalDatabase;

public class SDDatabase {
	

	public static void writeToVesselDB(LiveData vessel, String location, Object write) {
		ShipsLocalDatabase database = vessel.getLocalDatabase();
		database.set(write, location);
		database.save();
	}

	public static String getStringFromVesselDB(LiveData vessel, String location) {
		return vessel.getLocalDatabase().get(String.class, location);
	}

	public static void writeToConfig(String location, Object write) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(ShipsDamage.CONFIG);
		config.set(location, write);
		try {
			config.save(ShipsDamage.CONFIG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getString(String location) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(ShipsDamage.CONFIG);
		String ret = config.getString(location);
		return ret;
	}

}
