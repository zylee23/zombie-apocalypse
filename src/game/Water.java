package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represent water in the map
 * 
 * @author yinghuang
 *
 */
public class Water extends Ground {
	
	public Water() {
		super('~');
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
