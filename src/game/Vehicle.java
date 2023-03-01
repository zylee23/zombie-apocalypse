package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A vehicle that is use to travel an actor from one map to another
 * 
 * @author yinghuang
 *
 */
public class Vehicle extends Item {
	
	private GameMap map;
	private int xCoor;
	private int yCoor;
	private String message;
	
	/**
	 * Constructor
	 * @param newMap The destination map
	 * @param x	The destination x coordinate
	 * @param y The destination y coordinate
	 * @param newMessage A descriptive message for the action
	 */
	public Vehicle(GameMap newMap, int x, int y,String newMessage) {
		super("Vehicle", 'V', false);
		map = newMap;
		xCoor = x;
		yCoor = y;
		message = newMessage;
		addMoveActorAction();
	}
	
	/**
	 * add MoveActorAction to enable player to move from one place to another.
	 */
	public void addMoveActorAction() {
		this.allowableActions.add(new MoveActorAction(map.at(xCoor, yCoor),message));
	}
	
	/**
	 * Implementing ItemInterface, method does nothing 
	 */
	@Override
	public void shoot() {
	}
	
	/**
	 * Implementing ItemInterface, method does nothing
	 */
	@Override
	public void reload(int number) {
	}
	
	/**
	 * Implementing ItemInterface, method does nothing
	 */
	@Override
	public int getShootDamage() {
		return 0;
	}

}
