package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;

/**
 * An action which shows the available target within the range of the SniperRifle
 * The range of the SniperRifle is 12 x 12, with actor located in the center of the range
 * 
 * @author yinghuang
 *
 */
public class SniperRifleAction extends Action {
	
	private Weapon weapon;
	private Display display = new Display();
	private Menu subMenu = new Menu();
	
	/**
	 * Constructor
	 * 
	 * @param newWeapon The SniperRifle weapon that is going to be used
	 */
	public SniperRifleAction(Weapon newWeapon) {
		weapon = newWeapon;
	}
	
	/**
	 * Perform the action
	 * Shows a menu that contains the available target within the range of the SniperRifle
	 * 
	 * @param actor The actor that is performing the action
	 * @param map The map that the actor is currently at
	 * @return A descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		Actions actions = new Actions();
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		int actorXCoor = map.locationOf(actor).x();
		int actorYCoor = map.locationOf(actor).y();
		
		for (int i = -6; i < 7; i++) {
			for (int j = -6; j < 7; j++) {
				if (i != 0 || j != 0) {
					Location newLocation = new Location(map, actorXCoor + i, actorYCoor + j);
					if (xRange.contains(actorXCoor + i) && yRange.contains(actorYCoor + j)
							&& map.isAnActorAt(newLocation) && 
							map.getActorAt(newLocation).hasCapability(ZombieCapability.UNDEAD)) {
						actions.add(new ChooseTargetAction(map.getActorAt(newLocation), weapon));
					}
				}
			}
		}
		
		actions.add(new DoNothingAction());
		Action selectedAction = subMenu.showMenu(actor, actions, display);
		
		return selectedAction.execute(actor, map);
	}
	
	/**
	 * A descriptive message for the menu
	 * 
	 * @param actor The actor that is performing the action
	 * @return A descriptive message to be shown in the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " choose to use sniper";
	}

}
