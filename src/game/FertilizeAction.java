package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action for fertilising unripe crop.
 * 
 * @author zylee
 *
 */
public class FertilizeAction extends Action {
	
	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).getGround().fertilise();
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string when farmer fertilises crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu when edible item is eaten.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilises a crop";
	}

}
