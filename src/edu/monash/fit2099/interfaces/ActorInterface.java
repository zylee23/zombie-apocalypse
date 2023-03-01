package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	void setNumberOfArms(int n);
	void setNumberOfLegs(int n);
	int getNumberOfArms();
	int getNumberOfLegs();
	String dropLimbs(GameMap map,Actor actor);
	String dropItems(GameMap map,Actor actor);
	boolean canMove();
	boolean isInjured();
	Actor getTarget();
	void setTarget(Actor newTarget);
	int getFocus();
	void setFocus(int newFocus);
}
