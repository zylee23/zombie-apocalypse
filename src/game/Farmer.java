package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.DoNothingAction;

/**
 * Class that represents an ordinary Farmer
 * 
 * @author zylee
 *
 */
public class Farmer extends Human {
	
	/**
	 * An array of Farmer's behaviours. 
	 */
	private Behaviour[] behaviours = {new EatBehaviour(), new HarvestBehaviour(), new FertilizeBehaviour(), 
			new SowingBehaviour(), new WanderBehaviour()};
	
	/**
	 * Constructor.
	 * 
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	
	/**
	 * Select and return an action to perform on the current turn. 
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}

}
