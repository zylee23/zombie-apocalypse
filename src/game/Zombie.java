package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

import java.util.Random;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	
	private int numberOfTurns = 0;
	private Random rand = new Random();
	private boolean riseFromDead = false;
	
	private Behaviour[] behaviours = {
			new PickUpItemBehaviour(),
			new SayBehaviour("BRAINNNS!"),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * The protected constructor that creates Zombies rising from the dead.
	 * @param name The zombie's display name.
	 * @param riseFromDead The boolean true indicating corpse rising form the dead.
	 */
	protected Zombie(String name, boolean riseFromDead) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		if (!riseFromDead) {
			throw new IllegalArgumentException("Argument should be true to indicate corpse rising from the dead");
		}
		this.riseFromDead = riseFromDead;
	}
	
	/**
	 * Returns the IntricsicWeapon for the actor based on the probability 
	 * Bite and punch has the same probability at the beginning 
	 * punch probability decreases as the numberOfArms of the actor decreases 
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (rand.nextDouble() <= (0.25 * getNumberOfArms())) {
			return new IntrinsicWeapon(10,"punches");
		}
		return new IntrinsicWeapon(20,"bites");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (riseFromDead) {
			riseFromDead = false;
			display.println(name + " rose from the dead");
		}
		if (this.getNumberOfLegs() < 2) {
			numberOfTurns++;
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	/**
	 * Implementing the ActorInterface method 
	 * A method to drop the actor's limbs when being attacked 
	 * When being attacked, there's a chance of 25% that one of the actor's limbs will drop off
	 * The actor can only drop at most four limbs
	 * 
	 * @param map The map that the actor is standing on
	 * @param actor The actor who's limbs is getting dropped 
	 * @return Returns a descriptive message to be shown in the IO, null if there's nothing dropped
	 */
	@Override
	public String dropLimbs(GameMap map,Actor actor) {
		String results = "";
		Action newAction;
		if (rand.nextDouble() <= 0.25) {
			
			if (rand.nextDouble() <= 0.5 && getNumberOfLegs() > 0) {
				setNumberOfLegs(getNumberOfLegs() - 1);
				newAction = new LimbsFallOffAction(new WeaponTemplate("zombieLeg", 'l', 10, "zip", CraftingCapability.CRAFTABLE));
				results += System.lineSeparator() + newAction.execute(actor, map);
				return results;
				
			} else if (getNumberOfArms() > 0){
				setNumberOfArms(getNumberOfArms() - 1);
				newAction = new LimbsFallOffAction(new WeaponTemplate("zombieArm", 'a', 8, "zap", CraftingCapability.CRAFTABLE));
				results += dropItems(map,actor) + System.lineSeparator() + newAction.execute(actor, map);
				return results;
			}
			return results;
		}
		return results;
	}
	
	/**
	 * Implementing the ActorInterface method 
	 * A method to drop the item that the actor is holding when one the actor's arm was dropped.
	 * Actor will definitely drop item when both of it's arm were gone 
	 * When one of it's arm were dropped, it has a chance of 50% to drop item onto the ground
	 * 
	 * @param map The map that the actor is standing on
	 * @param actor The actor whose arms are getting dropped 
	 * @return A descriptive message to be shown in the IO, null if there's nothing dropped
	 */
	@Override
	public String dropItems(GameMap map,Actor actor) {
		Action newAction;
		if (actor.getInventory().size() > 0) {
			Item item = actor.getInventory().get(0);
			
			if (actor.getNumberOfArms() == 0) {
				newAction = new DropItemAction(item);
				return System.lineSeparator() + newAction.execute(actor, map);
				
			} else {
				if (rand.nextDouble() <= 0.5) {
					newAction = new DropItemAction(item);
					return System.lineSeparator() + newAction.execute(actor, map);
				}
			}
		}
		return "";
	}
	
	/**
	 * Implementing the ActorInterface method 
	 * A method which returns true when the zombie can move, and false when he can't
	 * When zombie lost one of it's leg, it can only move every two turns 
	 * Zombie can't move if he lost both of his legs
	 */
	@Override
	public boolean canMove() {
		if (this.getNumberOfLegs() == 2) {
			return true;
		}
		else if (this.getNumberOfLegs() == 1 && numberOfTurns%2 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Implementing ActorInterface method. Since we do not need to know if Zombies are injured,
	 * always return false.
	 */
	@Override
	public boolean isInjured() {
		return false;
	}

	@Override
	public Actor getTarget() {
		return null;
	}

	@Override
	public void setTarget(Actor target) {
	}

	@Override
	public int getFocus() {
		return 0;
	}

	@Override
	public void setFocus(int newFocus) {
	
	}
	
}
