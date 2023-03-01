package game;

import java.util.*;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A behaviour that returns a PickUpItemAction if the actor's location contains a weapon
 * 
 * @author yinghuang
 *
 */
public class PickUpItemBehaviour implements Behaviour {
	
	/**
	 * Return a PickUpItemAction to pick up a weaponItem if possible
	 * If no action is possible, return null
	 * Actor can only pick up weapon item
	 * In order to be able to pick up an item, actor must has at least 1 hand 
	 * Actor can only pick up a weapon 
	 * 
	 * @param actor The actor enacting the behaviour 
	 * @param map The map that actor is currently on
	 * @return a PickUpItemAction, or null if no PickUpItemAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = map.locationOf(actor).getItems();
		if (items.size() != 0) {
			for (Item item: items) {
				if (item.asWeapon() != null && actor.getNumberOfArms() > 0 && actor.getInventory().size() == 0) {
					return new PickUpItemAction(items.get(0));
				}
			}
		}
		return null;
	}

}
