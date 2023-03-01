package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * Shotgun weapon class
 * 
 * @author yinghuang
 *
 */
public class Shotgun extends WeaponGun {
	
	/**
	 * Constructor 
	 */
	public Shotgun() {
		super("Shotgun", '/', 8, "bangg", 25);
		this.addCapability(RangeWeaponCapability.RANGED);
	}
	
	/**
	 * When the weapon is being carried, add ShotgunAction if the shotgun is not out of ammo
	 * 
	 * @param currentLocation The location of actor carrying this item
	 * @param actor The actor carrying this item
	 */
	@Override 
	public void tick(Location currentLocation, Actor actor) {
		this.allowableActions.clear();
		if (this.ableToShoot()) {
			this.allowableActions.add(new ShotGunAction(this));
		}
	}
	
	/**
	 * When item is on the ground, remove all actions available for this weapon
	 * 
	 * @param currentLocation the location of the item
	 */
	@Override 
	public void tick(Location currentLocation) {
		this.allowableActions.clear();
	}

}
