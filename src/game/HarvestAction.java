package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An Action for harvesting ripe crop.
 * 
 * @author zylee
 *
 */
public class HarvestAction extends Action {
	
	/**
	 * The location of ripe crop.
	 */
	private Location location;
	
	/**
	 * Constructor.
	 * @param location The exact or adjacent location of an Actor.
	 */
	public HarvestAction(Location location) {
		if (location == null) {
			throw new NullPointerException();
		}
		this.location = location;
	}
	
	/**
	 * Perform the Action.
	 * Set ground at location to dirt.
	 * If Actor is the player, add food item into player's inventory.
	 * If Actor is farmer, drop food item on the location.  
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String description = menuDescription(actor);
		
		if (Player.class.isInstance(actor)) {
			actor.addItemToInventory(new Food());
			description += " and picks up the food";
		}
		else if (Farmer.class.isInstance(actor)) {
			location.addItem(new Food());
			description += " and drops food on the ground";
		}
		location.setGround(new Dirt());
		return description;
	}

	/**
	 * Returns a descriptive string when Actor harvests a ripe crop.
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests a crop";
	}

}