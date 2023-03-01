package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * SniperRifle weapon class
 * 
 * @author yinghuang
 *
 */
public class SniperRifle extends WeaponGun {
	
	/**
	 * Constructor
	 */
	public SniperRifle() {
		super("SniperRifle", ']', 8, "snipes",30);
		this.addCapability(RangeWeaponCapability.RANGED);
	}
	
	/**
	 * When the weapon is being carried, SniperRifleAction if the weapon is not running out of ammo
	 * 
	 * @param currentLocation The location of actor carrying the weapon
	 * @param actor The actor carrying the weapon
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		this.allowableActions.clear();
		if (this.ableToShoot()) {
			this.allowableActions.add(new SniperRifleAction(this));
		}
	}
	
	/**
	 * When the weapon is not being carried, remove all the available actions for this weapon
	 * 
	 * @param currentLocation The location of the item
	 */
	@Override 
	public void tick(Location currentLocation) {
		this.allowableActions.clear();
	}

}
