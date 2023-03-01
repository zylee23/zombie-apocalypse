package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;

/**
 * An action which allows actor to shoot the shotgun at different direction 
 * 
 * @author yinghuang
 *
 */
public class ShotGunAction extends Action {
	
	private Weapon weapon;
	private Display display = new Display();
	private Menu subMenu = new Menu();
	
	/**
	 * Constructor 
	 * @param newWeaponGun The weapon that the actor is holding 
	 */
	public ShotGunAction(Weapon newWeaponGun) {
		weapon = newWeaponGun;
	}
	
	/**
	 * Perform the action
	 * Shows a menu which contains the possible directions to shoot at
	 * 
	 * @param actor The actor that is performing the action
	 * @param map The map that the actor currently at
	 * @return A descriptive message which shows the direction where the shot was taken
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		Actions actions = new Actions();
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		
		for (Exit e : exits) {
			actions.add(new ShotgunAttackAction(e,weapon));
		}
		
		Action selectedAction = subMenu.showMenu(actor, actions, display);
		return selectedAction.execute(actor, map);
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * 
	 * @param actor The actor who is performing the action
	 * @return A message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " choose to use shotgun";
	}

}
