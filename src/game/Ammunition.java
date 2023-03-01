package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * Ammunition class for all the weapon in the game 
 * @author yinghuang
 *
 */
public class Ammunition extends PortableItem {
	
	/**
	 * reload amount for the ammunition
	 */
	private int reloadAmount; 
	
	/**
	 * Constructor
	 * @param newAmount The reload amount from the ammunition
	 */
	public Ammunition(int newAmount) {
		super("Ammunition", '$');
		reloadAmount = newAmount;
	}
	
	/**
	 * Add ReloadAction to this item if the actor is carrying range weapon
	 * 
	 * @param currentLocation The location of actor carrying this item
	 * @param actor The actor carrying this item
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		this.allowableActions.clear();
		if (actor.hasCapability(RangeWeaponCapability.RANGED)) {
			this.allowableActions.add(new ReloadAction(reloadAmount, this));
		}
	}
	
	/**
	 * Clear all the available action for this item when it's on the ground
	 * 
	 * @param currentLocation The location of this item
	 */
	@Override
	public void tick(Location currentLocation) {
		this.allowableActions.clear();
	}

}
