package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;

/**
 * An attack action for shotgun weapon
 * 
 * @author yinghuang
 *
 */
public class ShotgunAttackAction extends Action {
	
	private Weapon weapon;
	private Exit exit;
	private Random rand = new Random();
	
	/**
	 * Constructor
	 * 
	 * @param newDirection Direction where the shot was taken
	 * @param newWeaponGun The shotgun weapon used
	 */
	public ShotgunAttackAction(Exit newDirection, Weapon newWeaponGun) {
		exit = newDirection;
		weapon = newWeaponGun;
	}
	
	/**
	 * Perform the actions
	 * Deals a 90% cone pellet Aoe damage at the direction where the shot was taken
	 * 
	 * @param actor The actor who is performing the action
	 * @param map The map that the actor is currently at
	 * @return A descriptive message which shows the direction where the shot was taken and damage deal to the actor within the aoe
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		String results = menuDescription(actor);
		Item weaponItem = (Item) weapon;
		int actorXCoor = map.locationOf(actor).x();
		int actorYCoor = map.locationOf(actor).y();
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		weaponItem.shoot();;
		
		// shoot at north 
		if (exit.getName() == "North") {
			for (int i = 1; i < 4; i++) {
				for (int j = i; j > -i - 1; j--) {
					Location newLocation = new Location(map, actorXCoor + j, actorYCoor - i);
					if (xRange.contains(actorXCoor + j) && yRange.contains(actorYCoor - i) 
							&& map.isAnActorAt(newLocation) == true) {
						results += attack(actor, map, newLocation, weaponItem.getShootDamage());
					}
				}
			}
		}
		// shoot at east
		else if (exit.getName() == "East") {
			for (int i = 1; i < 4; i++) {
				for (int j = i; j > -i - 1; j--) {
					Location newLocation = new Location(map, actorXCoor + i, actorYCoor - j);
					if (xRange.contains(actorXCoor + i) && yRange.contains(actorYCoor - j)
							&& map.isAnActorAt(newLocation)) {
						results += attack(actor, map, newLocation, weaponItem.getShootDamage());
					}
				}
			}
		}
		// shoot at south
		else if (exit.getName() == "South") {
			for (int i = 1; i < 4; i++) {
				for (int j = i; j > -i - 1; j--) {
					Location newLocation = new Location(map, actorXCoor + j, actorYCoor + i);
					if (xRange.contains(actorXCoor + j) && yRange.contains(actorYCoor + i)
							&& map.isAnActorAt(newLocation)) {
						results += attack(actor, map, newLocation, weaponItem.getShootDamage());
					}
				}
			}
		}
		// shoot at west
		else if (exit.getName() == "West") {
			for (int i = 1; i < 4; i++) {
				for (int j = i; j > -i - 1; j--) {
					Location newLocation = new Location(map, actorXCoor - i, actorYCoor + j);
					if (xRange.contains(actorXCoor - i) && yRange.contains(actorYCoor + j)
							&& map.isAnActorAt(newLocation)) {
						results += attack(actor, map, newLocation, weaponItem.getShootDamage());
					}
				}
			}
		}
		// shoot at north-east
		else if (exit.getName() == "North-East") {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i != 0 || j != 0) {
						Location newLocation = new Location(map, actorXCoor + i, actorYCoor - j);
						if (xRange.contains(actorXCoor + i) && yRange.contains(actorYCoor - j)
								&& map.isAnActorAt(newLocation)) {
							results += attack(actor, map, newLocation, weaponItem.getShootDamage());
						}
					}
				}
			}
		}
		// shoot at south-east
		else if (exit.getName() == "South-East") {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i != 0 || j != 0) {
						Location newLocation = new Location(map, actorXCoor + i, actorYCoor + j);
						if (xRange.contains(actorXCoor + i) && yRange.contains(actorYCoor + j)
								&& map.isAnActorAt(newLocation)) {
							results += attack(actor, map, newLocation, weaponItem.getShootDamage());
						}
					}
				}
			}
		}
		// shoot at north-west
		else if (exit.getName() == "North-West") {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j< 4; j++) {
					if (i != 0 || j != 0) {
						Location newLocation = new Location(map, actorXCoor - i, actorYCoor - j);
						if (xRange.contains(actorXCoor - i) && yRange.contains(actorYCoor - j)
								&& map.isAnActorAt(newLocation)) {
							results += attack(actor, map, newLocation, weaponItem.getShootDamage());
						}
					}
				}
			}
		}
		// shoot at south-west
		else if (exit.getName() == "South-West") {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i != 0 || j != 0) {
						Location newLocation = new Location(map, actorXCoor - i, actorYCoor + j);
						if (xRange.contains(actorXCoor - i) && yRange.contains(actorYCoor + j)
								&& map.isAnActorAt(newLocation)) {
							results += attack(actor, map, newLocation, weaponItem.getShootDamage());
						}
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * 
	 * @param actor The actor who is performing the action
	 * @return A message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoot at " + exit.getName();
	}
	
	/**
	 * An attack action to reduce the reusing of code
	 * 
	 * @param actor Actor that is performing the action
	 * @param map The map that the actor is currently at
	 * @param location One of the location from the bullet's path
	 * @param damage Damage dealt by the weapon
	 * @return A descriptive message which shows what happened throughout the action
	 */
	public String attack(Actor actor, GameMap map, Location location, int damage) {
		String results = "";
		Actor target = map.getActorAt(location);
		
		if (rand.nextDouble() <= 0.75) {
			
			target.hurt(damage);
			results += System.lineSeparator() + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage."
					+ target.dropLimbs(map, target);
			
			if (!target.isConscious()) {
				if (target.hasCapability(ZombieCapability.ALIVE)) {
					Item corpse = new Corpse(target.toString(), '%');
					map.locationOf(target).addItem(corpse);
				}
				
				Actions dropActions = new Actions();
				for (Item item : target.getInventory()) {
					dropActions.add(item.getDropAction());
				}
				for (Action drop : dropActions) {		
					drop.execute(target, map);
				}
				map.removeActor(target);	
				
				results += System.lineSeparator() + target + " is killed.";
			}
			return results;
		}
		return System.lineSeparator() + actor + " misses " + target + ".";
	}

}
