package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action for sowing crop on dirt.
 * 
 * @author zylee
 *
 */
public class SowingAction extends Action {
	
	/**
	 * The location to sow the crop.
	 */
	private Location location;
	
	/**
	 * Constructor.
	 * @param location The patch of dirt next to farmer.
	 */
	public SowingAction(Location location) {
		if (location == null) {
			throw new NullPointerException();
		}
		this.location = location;
	}
	
	/**
	 * Perform the Action.
	 * Set ground at location to crop.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		location.setGround(new Crop());
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string when farmer sows crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows a crop";
	}

}
