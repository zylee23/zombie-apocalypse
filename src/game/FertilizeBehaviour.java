package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that generates FertilizeAction if farmer is standing on unripe crop.
 * 
 * @author zylee
 *
 */
public class FertilizeBehaviour implements Behaviour {
	
	/**
	 * Returns a FertilizeAction that allows farmer to fertilise crop is there is unripe crop on current ground
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no crop on Actor's location.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Ground ground = map.locationOf(actor).getGround();
		if (Crop.class.isInstance(ground) && ground.hasCapability(CropCapability.UNRIPE)) {
			return new FertilizeAction();
		}
		return null;
	}

}
