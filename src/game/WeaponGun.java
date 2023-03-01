package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class for range weapon item
 * 
 * @author yinghuang
 *
 */
public class WeaponGun extends WeaponItem {
	
	private int ammunition = 5;
	private int shootDamage;
	
	/**
	 * Constructor 
	 * @param name The name of the item
	 * @param displayChar The character that is used to display the item in the map
	 * @param damage Melee damage deal by the weapon
	 * @param verb Verb use for this weapon
	 * @param newShootDamage Fires damage deal by this weapon
	 */
	public WeaponGun(String name, char displayChar, int damage, String verb, int newShootDamage) {
		super(name, displayChar, damage, verb);
		shootDamage = newShootDamage;
	}
	
	/**
	 * This method is called when it the weapon is fired
	 */
	@Override
	public void shoot() {
		if (ammunition == 0) {
			throw new RuntimeException("Weapon is out of bullets");
		}
		ammunition--;
	}
	
	/**
	 * This method is called when reloading
	 */
	@Override
	public void reload(int newAmmunition) {
		ammunition += newAmmunition;
	}
	
	/**
	 * A method to check whether the weapon is still able to fire
	 * 
	 * @return Return true if weapon is not out of ammo
	 */
	public boolean ableToShoot() {
		if(ammunition == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return Get the fire damage from the weapon
	 */
	@Override
	public int getShootDamage() {
		return shootDamage;
	}

}
