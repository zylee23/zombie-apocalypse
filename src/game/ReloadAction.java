package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A reload action for all the range weapon in the game
 * @author yinghuang
 *
 */
public class ReloadAction extends Action {
	
	/**
	 * Reload amount from the ammunition
	 */
	private int reloadAmount;
	
	/**
	 * The ammunition 
	 */
	private Item ammunition;
	
	/**
	 * Constructor 
	 * @param newAmount The amount for reloading
	 * @param newItem The ammunition
	 */
	public ReloadAction(int newAmount, Item newItem) {
		reloadAmount = newAmount;
		ammunition = newItem;
	}
	
	/**
	 * Perform the action
	 * Reload all the range weapon that the actor is carrying
	 * 
	 * @param actor The actor that is performing the action
	 * @param map The map that the actor is currently at
	 * @return A descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventory = actor.getInventory();
		
		for (Item item : inventory) {
			if (item.hasCapability(RangeWeaponCapability.RANGED)) {
				item.reload(reloadAmount);
			}
		}
		
		actor.removeItemFromInventory(ammunition);
		
		return actor + " reloaded all his weapon.";
	}
	
	/**
	 * A descriptive message for the menu
	 * 
	 * @param actor The actor that is performing the action
	 * @return A descriptive message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " choose to reload.";
	}

}
