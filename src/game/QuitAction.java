package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action for quiting the game
 * 
 * @author zylee
 *
 */
public class QuitAction extends Action {

	/**
	 * Perform the Action.
	 * Remove the actor(player) from the map which will end the game.  
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (Player.class.isInstance(actor)) {
			map.removeActor(actor);
			return menuDescription(actor);
		}
		return null;
	}

	/**
	 * Returns a descriptive string when player quits the game.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " quits the game";
	}
	

}
