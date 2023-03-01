package game;

/**
 * Class that represents food that can be eaten by Human.
 * 
 * @author zylee
 *
 */
public class Food extends PortableItem {
	
	/**
	 * Constructor.
	 * 
	 * Add action that can be done to food.
	 */
	public Food() {
		super("food", '^');
		addCapability(FoodCapability.EDIBLE);
		allowableActions.add(new EatAction(this));
	}

}
