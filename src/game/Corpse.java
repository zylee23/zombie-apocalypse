package game;

import java.util.*;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;

/**
 * Class representing a corpse when an Actor is killed.
 * Corspe will rise from the dead to be a Zombie.
 * 
 * @author zylee
 *
 */
public class Corpse extends PortableItem {
	
	/**
	 * The number of turns for a corpse to rise as a Zombie.
	 */
	private final int NUM_OF_TURNS_TO_RISE = 8;
	/**
	 * The number of turns since the corpse is created.
	 */
	private int numOfTurnsDead = 0;
	/**
	 * The name of the Actor that is killed.
	 */
	private String name;
	
	/**
	 * Constructor
	 * 
	 * @param name The name of the Actor that is killed.
	 * @param displayChar The character that will represent the Corpse in the display.
	 */
	public Corpse(String name, char displayChar) {
		super("dead " + name, displayChar);
		this.name = name;
	}

	/**
	 * Adds a Zombie to the location of the corpse when it is time to rise from the dead.
	 * If there is an Actor at the location, Zombie will rise next turn.
	 * @param location The location of the ground on which the corpse lie.
	 */
	@Override
	public void tick(Location location) {
		numOfTurnsDead++;
		if (numOfTurnsDead == NUM_OF_TURNS_TO_RISE) {
			if (!location.containsAnActor()) {
				location.removeItem(this);
				location.addActor(new Zombie(name, true));
			}
			else {
				numOfTurnsDead--;
			}
		}
	}
	
	/**
	 * Adds a Zombie to an adjacent location of the Actor that is carrying the corpse when it is time to
	 * rise from the dead.
	 * If there is an Actor at the adjacent location, Zombie will rise next turn.
	 * @param location The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
	 */
	@Override
	public void tick(Location location, Actor actor) {
		numOfTurnsDead++;
		if (numOfTurnsDead == NUM_OF_TURNS_TO_RISE) {	
			List<Location> coord = new ArrayList<>();
			for (Exit exit : location.map().locationOf(actor).getExits()) {
	            Location surrounding = exit.getDestination();
	            if (surrounding.canActorEnter(actor)) {
	            	coord.add(surrounding);
	            }
			}
			if (!coord.isEmpty()) {
				Collections.shuffle(coord);
				actor.removeItemFromInventory(this);
				coord.get(0).addActor(new Zombie(name, true));
			}
			else {
				numOfTurnsDead--;
			}
		}
	}
	
}
