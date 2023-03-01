package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * An action for crafting a better weapon
 * 
 * @author yinghuang
 *
 */
public class CraftWeaponAction extends Action {
	/**
	 * The item that is going to be crafted
	 */
	protected WeaponItem item;
	
	/**
	 * Constructor
	 * @param newItem The item that is going to be crafted
	 */
	public CraftWeaponAction(WeaponItem newItem) {
		if (newItem == null) {
			throw new NullPointerException("The item going to be dropped cannot be a null reference");
		}
		this.item = newItem;
	}
	
	/**
	 * Perform the action
	 * Craft a zombieArm into a zombieClub and a zombieLeg into a zombieMace
	 * Create a newly crafted weapon and remove the old weapon from the map
	 * 
	 * @param actor The actor performing the action
	 * @param map The map the actor is standing on
	 * @return A descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		WeaponItem craftedItem = null;
		String results = menuDescription(actor);
		
		if (item.toString().toLowerCase().contains("arm")) {
			craftedItem = new WeaponTemplate("zombieClub", 'c', item.damage()*2, "heavy" + item.verb(), CraftingCapability.UNCRAFTABLE);
		}
		else if (item.toString().toLowerCase().contains("leg")) {
			craftedItem = new WeaponTemplate("zombieMace", 'm', item.damage()*2, "heavy" + item.verb(), CraftingCapability.UNCRAFTABLE);
		}
		
		actor.removeItemFromInventory(item);
		actor.addItemToInventory(craftedItem);
		
		return results + " into a " + craftedItem.toString();
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * @param actor The actor who is performing the action
	 * @return A message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " craft " + item.toString() ;
	}

}