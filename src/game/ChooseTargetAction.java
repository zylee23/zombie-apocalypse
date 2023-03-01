package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;

/**
 * An action that returns the available zombies target within the 
 * 12 x 12 range with actor standing at the centre of the range.
 * 
 * @author yinghuang
 *
 */
public class ChooseTargetAction extends Action {
	
	private Display display = new Display();
	private Menu subMenu = new Menu();
	private Actor target;
	private Weapon weapon;
	
	/**
	 * Constructor for ChooseTargetAction class
	 * 
	 * @param newTarget The chosen target
	 * @param newWeapon	The weapon that is going to be used on the chosen target
	 */
	public ChooseTargetAction(Actor newTarget, Weapon newWeapon) {
		target = newTarget;
		weapon = newWeapon;
	}
	
	/**
	 * Perform the action
	 * Shows a menu which contains two other possible actions on the targeted actor
	 * 
	 * @param actor Actor that is performing the action
	 * @param map The map that the actor is currently at
	 * @return A descriptive message of the chosen action on the targeted actor
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Actions actions = new Actions();
		if (actor.getTarget() != target) {
			actor.setFocus(0);
			actor.setTarget(target);
		}
		if (actor.getFocus() < 2) {
			actions.add(new AimAction(target));
			actions.add(new SniperRifleAttackAction(weapon, target));
		}
		else {
			actions.add(new SniperRifleAttackAction(weapon, target));
		}
		Action selectedAction = subMenu.showMenu(actor, actions, display);
		return selectedAction.execute(actor, map);
		
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * 
	 * @param actor The actor that is performing the action
	 * @return A message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " choose " + target + " as a target";
	}

}
