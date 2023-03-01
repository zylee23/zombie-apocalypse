package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		
		if (biteWeapon(weapon) && rand.nextDouble() > 0.4) {
			return actor + " misses " + target + ".";
		} else if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		if (biteWeapon(weapon)) {
			actor.heal(5);
			result += System.lineSeparator() + actor + " heals for 5 hitpoints.";
		}

		target.hurt(damage);
		
		result += target.dropLimbs(map, target);
		
		if (!target.isConscious()) {
			if (target.hasCapability(ZombieCapability.ALIVE)) {
				Item corpse = new Corpse(target.toString(), '%');
				map.locationOf(target).addItem(corpse);
			}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
	
	public boolean biteWeapon(Weapon weapon) {
		if (weapon.verb().toLowerCase().contains("bite")) {
			return true;
		}
		return false;
	}
}
