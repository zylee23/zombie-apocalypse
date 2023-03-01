package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	/**
	 * Implementing GroundInterface method. Method does nothing.
	 */
	@Override
	public void fertilise() {
	}
}
