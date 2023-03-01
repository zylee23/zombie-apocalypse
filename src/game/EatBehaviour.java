package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A class that generates an EatAction if the current Actor is alive and standing on food.
 * 
 * @author zylee
 *
 */
public class EatBehaviour implements Behaviour {
	
	/**
	 * Returns an EatAction that allows player or damaged Human to eat food to recover health points.
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no food on Actor's location.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
		for (Item i : items) {
			if (Food.class.isInstance(i)) {
				if (actor.hasCapability(ZombieCapability.ALIVE)) {
					if (actor.isInjured()) {
						return new EatAction(i);
					}
				}
			}
		}
		return null;
	}

}
