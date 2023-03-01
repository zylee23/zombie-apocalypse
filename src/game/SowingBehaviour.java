package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates a SowingAction if the current Actor is a farmer standing next to a patch of dirt.
 * 
 * @author zylee
 *
 */
public class SowingBehaviour implements Behaviour {
	
	/**
	 * The probability of farmer sowing a crop.
	 */
	private final double PROBABILITY_TO_SOW = 0.33;
	/**
	 * Random probability generator.
	 */
	private Random random = new Random();
		
	/**
	 * Returns a SowingAction that allows farmer to sow a crop on dirt.
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if not standing next to dirt.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e : exits) {
			if (Dirt.class.isInstance(e.getDestination().getGround())) {
				if (random.nextDouble() <= PROBABILITY_TO_SOW) {
					return new SowingAction(e.getDestination());
				}
				else {
					return null;
				}
			}
		}
		return null;
	}

}
