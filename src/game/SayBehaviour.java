package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A behaviour that returns a SayAction which allow an actor to say something 
 * 
 * @author yingh
 *
 */
public class SayBehaviour implements Behaviour{
	
	/**
	 * A random number generator
	 */
	private Random rand = new Random();
	
	/**
	 * The word that is going to be said
	 */
	private String word;
	
	/**
	 * Constructor 
	 * @param newWord The word that is going to be said 
	 */
	public SayBehaviour(String newWord) {
		if (newWord == null) {
			throw new NullPointerException();
		}
		word = newWord;
	}
	
	/**
	 * Returns a sayAction if possible 
	 * If no action is possible, return null
	 * 
	 * @param actor The actor enacting the behaviour 
	 * @param map The map that the actor is standing on
	 * @return a SayAction, or null if no SayAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (rand.nextDouble() <= 0.1) {
			return new SayAction(word);
		}
		return null;
	}
	
}
