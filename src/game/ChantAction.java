package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action for chanting and spawning zombies on the map
 * 
 * @author zylee
 *
 */
public class ChantAction extends Action {
	
	/**
	 * The name of the zombies
	 */
	private String zombieName = "Zombie";
	/**
	 * Random number generator
	 */
	private Random random = new Random();

	/**
	 * Perform the Action.
	 * Spawn 5 zombies on the map randomly around the actor  
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int x, y;
		Location spawnLocation = map.locationOf(actor);
		
		for (int i = 0; i < 5; i++) {
			do {
				x = spawnLocation.x() + random.nextInt(30);
				y = spawnLocation.y() + random.nextInt(15);
			} 
			while (!map.getXRange().contains(x) || !map.getYRange().contains(y) || 
					map.at(x, y).containsAnActor() || !map.at(x, y).getGround().canActorEnter(actor));
			map.at(x, y).addActor(new Zombie(zombieName));
		}
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string when chanting.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " chants and spawns 5 zombies";
	}

}