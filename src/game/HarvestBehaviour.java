package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that generates a HarvestAction if the current Actor is standing on or next to ripe crop.
 * 
 * @author zylee
 *
 */
public class HarvestBehaviour implements Behaviour {
	
	/**
	 * Returns a HarvesttAction that allows farmer or player to harvest a ripe crop.
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no ripe crop on or nearby Actor's location.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		
		if (cropIsRipe(map.locationOf(actor).getGround())) {
			return new HarvestAction(map.locationOf(actor));
		}		
		else {
			for (Exit e : exits) {
				if (cropIsRipe(e.getDestination().getGround())) {
					return new HarvestAction(e.getDestination());
				}
			}
		}		
		return null;
	}
	
	/**
	 * Checks if there is a ripe crop on current or adjacent ground.
	 * @param ground The ground to be checked.
	 * @return true if there is ripe crop, false otherwise.
	 */
	public boolean cropIsRipe(Ground ground) {
		if (Crop.class.isInstance(ground)) {
			if (ground.hasCapability(CropCapability.RIPE)) {
				return true;
			}
		}
		return false;
	}

}
