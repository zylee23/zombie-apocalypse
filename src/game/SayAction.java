package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action to say a word
 * 
 * @author yinghuang
 *
 */
public class SayAction extends Action{
	/**
	 * The word that is going to be said 
	 */
	private String word;
	
	/**
	 * Constructor 
	 * @param word The word that is going to be said
	 */
	public SayAction(String word) {
		if (word == null) {
			throw new NullPointerException("The word that is going to be said cannot be null");
		}
		this.word = word;
	}
	
	/**
	 * Perform the action
	 * @param actor The actor that performs the action
	 * @param map The map that the actor standing on
	 * @return a descriptive message to be shown in the IO
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}
	
	/**
	 * A descriptive message to be shown in the menu
	 * @param actor The actor that performs the action
	 * @return a descriptive message for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " say " + word;
	}

}
