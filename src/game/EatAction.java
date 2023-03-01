package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An Action for eating edible items.
 * 
 * @author zylee
 *
 */
public class EatAction extends Action {
	
	/**
	 * The amount of health points recovered.
	 */
	private final int HEAL_POINTS = 10;
	/**
	 * The edible item to be eaten.
	 */
	private Item food;
	
	/**
	 * Constructor
	 * 
	 * @param food The food at the location of Actor.
	 */
	public EatAction(Item food) {
		if (food == null) {
			throw new NullPointerException();
		}
		this.food = food;
	}
	
	/**
	 * Perform the Action.
	 * Remove edible item from ground or player's inventory and heal Actor.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.hasCapability(ZombieCapability.ALIVE)) {
			if (Player.class.isInstance(actor)) {
				actor.removeItemFromInventory(food);
			}
			map.locationOf(actor).removeItem(food);
			actor.heal(HEAL_POINTS);
		}
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string when Actor that is alive eats food.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats food to heal for " + HEAL_POINTS + " health points";
	}

}
