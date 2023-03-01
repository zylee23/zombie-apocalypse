package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	
	private Actor target = null;
	private int focus = 0;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		if (map.locationOf(this).getGround().hasCapability(CropCapability.RIPE)) {
			actions.add(new HarvestAction(map.locationOf(this)));
		}
		actions.add(new QuitAction());
		Action selectedAction = menu.showMenu(this, actions, display);
		if (lastAction.getClass() != selectedAction.getClass()) {
			setFocus(0);
		}
		return selectedAction;
	}
	
	@Override 
	public void setTarget(Actor newtarget) {
		target = newtarget;
	}
	
	@Override
	public Actor getTarget() {
		return target;
	}
	
	@Override
	public int getFocus() {
		return focus;
	}
	
	@Override 
	public void setFocus(int newFocus) {
		focus = newFocus;
	}
	
	@Override
	public void hurt(int points) {
		super.hurt(points);
		setFocus(0);
	}

}
