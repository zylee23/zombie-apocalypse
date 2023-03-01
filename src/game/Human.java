package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	
	/**
	 * An array of Human's behaviours. 
	 */
	private Behaviour[] behaviours = {new EatBehaviour(), new WanderBehaviour()};

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	
	/**
	 * Implementing ActorInterface method
	 * Human can't drop any limbs when being attacked
	 * So empty string is returned all the time
	 */
	@Override
	public String dropLimbs(GameMap map,Actor actor) {
		return "";
	}
	
	/**
	 * Implementing ActorInterface method 
	 * Human's arm can't be dropped when being attacked
	 * Empty string is returned all the time since no items will be dropped 
	 */
	@Override
	public String dropItems(GameMap map,Actor actor) {
		return "";
	}
	
	/**
	 * Implementing ActorInterface method 
	 * Human's legs can't be dropped when being attacked
	 * Return true all the time 
	 */
	@Override
	public boolean canMove() {
		return true;
	}

	/**
	 * Returns true if human is damaged from attacks. 
	 * Returns False otherwise.
	 */
	@Override
	public boolean isInjured() {
		return hitPoints < maxHitPoints;
	}

	@Override
	public Actor getTarget() {
		return null;
	}

	@Override
	public void setTarget(Actor target) {
		
	}

	@Override
	public int getFocus() {
		return 0;
	}

	@Override
	public void setFocus(int newFocus) {
		
	}
}
