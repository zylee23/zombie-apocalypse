package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An aim action for sniper rifle weapon.
 * 
 * @author yinghuang
 *
 */
public class AimAction extends Action{
	
	/**
	 * The aimed target
	 */
	private Actor target;
	
	/**
	 * Constructor for AimAction class
	 * @param newTarget The aimed target
	 */
	public AimAction(Actor newTarget) {
		target = newTarget;
	}
	
	/**
	 * Perform the action
	 * Increment the counter(aim counter) by one 
	 * 
	 * @param actor The actor that is performing the action
	 * @param map The map the actor located at
	 * @return A descriptive message of how many total rounds the actor spent on aiming the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.setFocus(actor.getFocus() + 1);
		return menuDescription(actor) + " for a total of " + actor.getFocus() + " rounds.";
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * @param actor The actor that is performing the action
	 * @return A message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " aim at " + target;
	}

}
