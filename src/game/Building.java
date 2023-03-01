package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents building in the map.
 * 
 * @author zylee
 *
 */
public class Building extends Ground {

	public Building() {
		super('?');
	}
	
	/**
	 * An impassable terrain
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Implementing GroundInterface method. Method does nothing
	 */
	@Override
	public void fertilise() {
	}

}
