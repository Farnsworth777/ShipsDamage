package ShipsDamage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import MoseShipsBukkit.Events.Vessel.Create.ShipCreateEvent;
import MoseShipsBukkit.Events.Vessel.Transform.ShipAboutToMoveEvent;
import MoseShipsBukkit.Ships.ShipsData;
import MoseShipsBukkit.Ships.VesselTypes.DataTypes.LiveData;

public class SDListeners implements Listener {
	
	@EventHandler
	public void onShipCreation(ShipCreateEvent event) {
		ShipsData ship = event.getShip();
		if (ship instanceof LiveData) {
			SDDatabase.writeToVesselDB((LiveData) ship, "FullSize", event.getShip().getBasicStructure().size());
		}
	}

	@EventHandler
	public void onShipMove(ShipAboutToMoveEvent event) {
		LiveData ship = event.getShip();
		int currentsize = ship.getBasicStructure().size();
		int fullsize = Integer.parseInt(SDDatabase.getStringFromVesselDB(ship, "FullSize"));
		if ((currentsize / fullsize) * 100 < Integer.parseInt(SDDatabase.getString("DamageThreshold"))) {
			event.setCancelled(true);
		}
		if ((currentsize / fullsize) * 100 > 100) {
			event.setCancelled(true);
		}
	}

}
