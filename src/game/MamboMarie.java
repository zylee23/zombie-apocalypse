package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that represents the Voodoo priestess Mambo Marie
 * 
 * @author zylee
 *
 */
public class MamboMarie extends ZombieActor {
	
	/**
	 * The behaviour of Mambo Marie when she is not chanting
	 */
	private Behaviour behaviour = new WanderBehaviour();
	/**
	 * The number of turns since MamboMarie appeared on the map.
	 */
	private int numberOfTurns = 0;
	
	/**
	 * Constructor
	 */
	public MamboMarie() {
		super("Mambo Marie", 'M', 150, ZombieCapability.UNDEAD);
	}
	
	/**
	 * Select and return an action to perform on the current turn.
	 * She starts at the edge of the map and wanders randomly. 
	 * Every 10 turns, she will stop and spend the turn chanting.
	 * If she is not killed, she will vanish after 30 turns. 
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (numberOfTurns == 0) {
			display.println(this + " appears!");
			numberOfTurns++;
		}
		
		numberOfTurns++;
		if (numberOfTurns == 30) {
			numberOfTurns = 0;
			map.removeActor(this);
		}
		else if (numberOfTurns%10 == 0) {
			return new ChantAction();
		}
		else if (behaviour.getAction(this, map) != null) {
			return behaviour.getAction(this, map);
		}
		return new DoNothingAction();
	}

	/**
	 * Implementing GroundInterface method. Method does nothing.
	 */
	@Override
	public String dropLimbs(GameMap map, Actor actor) {
		return "";
	}

	/**
	 * Implementing ActorInterface method. Method does nothing.
	 */
	@Override
	public String dropItems(GameMap map, Actor actor) {
		return null;
	}

	/**
	 * Implementing ActorInterface method. Since Mambo Marie can always move around, return true.
	 */
	@Override
	public boolean canMove() {
		return true;
	}

	/**
	 * Implementing ActorInterface method. Since we do not need to know if Mambo Marie is injured,
	 * always return false.
	 */
	@Override
	public boolean isInjured() {
		return false;
	}

	/**
	 * Implementing ActorInterface method. Method does nothing.
	 */
	@Override
	public Actor getTarget() {
		return null;
	}

	/**
	 * Implementing ActorInterface method. Method does nothing.
	 */
	@Override
	public void setTarget(Actor newTarget) {
	}

	/**
	 * Implementing ActorInterface method. Method does nothing.
	 */
	@Override
	public int getFocus() {
		return 0;
	}

	/**
	 * Implementing ActorInterface method. Method does nothing.
	 */
	@Override
	public void setFocus(int newFocus) {
	}
	
}