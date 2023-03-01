package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class for any weaponItem
 * 
 * @author yinghuang
 *
 */
public class WeaponTemplate extends WeaponItem {
	
	/**
	 * Constructor 
	 * @param name The name of the weapon
	 * @param displayChar The character use to represent this item if it's on the group
	 * @param damage The damage that this weapon does
	 * @param verb verb to use for this weapon
	 * @param team enum type that represents a capability
	 */
	public WeaponTemplate(String name, char displayChar, int damage, String verb, Enum<?> team) {
		super(name, displayChar, damage, verb);
		addCapability(team);
	}
	
	/**
	 * When the item is being carried, returns a CraftWeaponAction if the weapon is craftable
	 * 
	 * @param currentLocation The location of actor carrying this item
	 * @param actor The actor carrying this item
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		allowableActions.clear();
		if (this.hasCapability(CraftingCapability.CRAFTABLE)) {
			this.allowableActions.add(new CraftWeaponAction(this));
		}
	}
	
	/**
	 * When the item is on the ground, remove the CraftWeaponAction 
	 * 
	 * @param currentLocation The location of actor 
	 */
	@Override
	public void tick(Location currentLocation) {
		allowableActions.clear();
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getShootDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
