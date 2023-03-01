package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class that represent crop that is sown by a farmer. Crop starts as unripe and will grow to ripe.
 * 
 * @author zylee
 *
 */
public class Crop extends Ground {
	
	/**
	 * The number of turns for crop to ripe.
	 */
	private int numOfTurnsToRipe = 20;
	/**
	 * The number of turns since the crop is created.
	 */
	private int numOfTurns = 0;
	/**
	 * The actions that can be done to crop.
	 */
	private Actions allowableActions;
	
	/**
	 * Constructor.
	 * 
	 * Initialises allowableActions.
	 */
	public Crop() {
		super('*');
		allowableActions = new Actions();
		addCapability(CropCapability.UNRIPE);
	}
	
	/**
	 * The crop will ripe after a number of turns.
	 * The crop can be harvested after it ripens.
	 * @param location The location of the Ground.
	 */
	@Override
	public void tick(Location location) {
		numOfTurns++;
		if (numOfTurns >= numOfTurnsToRipe && this.hasCapability(CropCapability.UNRIPE)) {
			removeCapability(CropCapability.UNRIPE);
			addCapability(CropCapability.RIPE);
			allowableActions.add(new HarvestAction(location));
		}
	}
	
	/**
	 * Fertilises the crop by reducing the number of turns to ripe.
	 */
	public void fertilise() {
		numOfTurns += 10;
	}
	
	/**
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return Actions that can be done to crop.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions ret = new Actions();
		for (Action a : allowableActions) {
			ret.add(a);
		}
		return ret;
	}

}
