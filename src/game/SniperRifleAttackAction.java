package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * An attack action for SniperRifle weapon
 * 
 * @author yinghuang
 *
 */
public class SniperRifleAttackAction extends Action {
	
	private Random rand = new Random();
	private Weapon weapon;
	private Actor target;
	
	/**
	 * Constructor
	 * 
	 * @param newWeapon The SniperRifle weapon that is going to be used 
	 * @param newTarget The actor that is going to be attacked 
	 */
	public SniperRifleAttackAction(Weapon newWeapon, Actor newTarget) {
		weapon = newWeapon;
		target = newTarget;
	}
	
	/**
	 * Perform the action
	 * Damage deal and hit chance by the SniperRifle is determine by the total number of aim rounds spent on the target
	 * 
	 * @param actor The actor that is performing the action
	 * @param map The map that the actor is currently at
	 * @return A descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String results = menuDescription(actor);
		Item weaponItem = (Item) weapon;
		weaponItem.shoot();
		
		if (actor.getFocus() == 0) {
			results += attack(actor, weaponItem.getShootDamage(), map, 0.75);
		}
		else if (actor.getFocus() == 1) {
			results += attack(actor, weaponItem.getShootDamage()*2, map, 0.90);
			actor.setFocus(0);
		}
		else {
			results += attack(actor, 90000, map, 1.00);
		}
		return results;
	}
	
	/**
	 * A descriptive message for the Menu
	 * 
	 * @param actor The actor that is performing the action
	 * @return A descriptive message to be shown in the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoot at " + target;
	}
	
	/**
	 * An attack action to reduce the code duplicating
	 * 
	 * @param actor The actor that is performing the action
	 * @param damage The damage dealt by the SniperRifle which depends on the number of aim rounds spent on the target
	 * @param map The map that the actor is currently at
	 * @param probability The chance of landing a successful hit on the target
	 * @return
	 */
	public String attack(Actor actor, int damage, GameMap map, double probability) {
		String results = "";
		
		if (rand.nextDouble() <= probability) {
			target.hurt(damage);
			
			results += System.lineSeparator() + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage."
					+ target.dropLimbs(map, target);
			
			if (!target.isConscious()) {
				
				actor.setTarget(null);
				actor.setFocus(0);
				
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
		return System.lineSeparator() + actor + " misses " + target;
	}

}
