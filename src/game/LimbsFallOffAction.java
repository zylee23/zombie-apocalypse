package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An action to drop a zombie limb
 * 
 * @author yinghuang
 *
 */
public class LimbsFallOffAction extends Action {
	/**
	 * The limbs that is going to be dropped
	 */
	protected Item item;
	
	/**
	 * Constructor 
	 * @param newItem The limbs that is going to be dropped
	 */
	public LimbsFallOffAction(Item newItem) {
		if (newItem == null) {
			throw new NullPointerException("The item going to be dropped cannot be a null reference");
		}
		item = newItem;
	}
	
	/**
	 * Perform the action
	 * Drop the limbs at the actor's location
	 * @param actor The actor performing the action
	 * @param map The map the actor is standing on
	 * @return A descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).addItem(item);
		return menuDescription(actor);
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * @param actor The actor who is performing the action
	 * @return A descriptive message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " drops a " + item;
	}

}
