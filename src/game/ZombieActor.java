package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	/**
	 * The number of limbs a zombieActor has
	 */
	private int numberOfArms = 2;
	private int numberOfLegs = 2;
	
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		addCapability(team);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD)) {
			list.add(new AttackAction(this));
		}
		return list;
	}
	
	/**
	 * setter for numberOfArms
	 * @param n number of arms
	 */
	public void setNumberOfArms(int n) {
		if (n > 2 && n < 0) {
			throw new IllegalArgumentException("The number of arms must in the range of 0 and 2");
		}
		numberOfArms = n;
	}
	
	/**
	 * setter for numberOfLegs
	 * @param n number of legs 
	 */
	public void setNumberOfLegs(int n) {
		if (n > 2 && n < 0) {
			throw new IllegalArgumentException("The number of legs must be in the range of 0 and 2");
		}
		numberOfLegs = n;
	}
	
	/**
	 * getter for numberOfArms
	 */
	public int getNumberOfArms() {
		return numberOfArms;
	}
	
	/**
	 * getter for numberOfLegs
	 */
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
}
