package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * A subclass of World from the representing the game world, including the locations of all Actors,
 * the player, and the playing grid. Unlike the parent class, the are ways to end the game.
 * Player can either quit the game, win when all zombies & Mambo Marie are wiped out
 * or lose when player/all the other humans are killed.
 * 
 * @author zylee
 *
 */
public class EndableWorld extends World {
	
	/**
	 * World has only one Mambo Marie 
	 */
	private MamboMarie mamboMarie = new MamboMarie();
	/**
	 * Random number generator
	 */
	private Random random = new Random();
	/**
	 * Determine whether player win or lose at the end of the game
	 */
	private boolean playerWins;
	
	/**
	 * Constructor
	 * 
	 * @param display the Display that will display this World.
	 */
	public EndableWorld(Display display) {
		super(display);
	}
	
	/**
	 * Spawns Mambo Marie into the world. She has 5% chance per turn of appearing at the edge 
	 * of the player's map if she is not currently on any map. If she is not killed, she will 
	 * vanish after 30 turns. Mambo Marie will keep coming back until she is killed.
	 * 
	 */
	public void mamboMarieAppear() {
		int x, y;
		boolean inMap = false;
		for (GameMap m : gameMaps) {
			if (m.contains(mamboMarie)) {
				inMap = true;
			}
		}
		GameMap playerMap = actorLocations.locationOf(player).map();
		if (!inMap && random.nextDouble() < 0.05 && mamboMarie.isConscious()) {
			do {
				x = random.nextInt(4);
				y = random.nextInt(25); 
			}
			while (playerMap.at(x, y).containsAnActor());
			playerMap.at(x, y).addActor(mamboMarie);
		}
	}
	
	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following: - displays the player's
	 * map - processes the actions of every Actor in the game, regardless of map.
	 * Calls mamboMarieAppear() every turn. 
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors. We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}
			if (stillRunning()) {
				mamboMarieAppear();
			}
		}
		display.println(endGameMessage());
	}

	
	/**
	 * Returns true if the game is still running, false otherwise.
	 * 
	 * The game is considered to still be running if the player is still around.
	 * 
	 * The game is considered to be not running when player quits, wins or loses.
	 *
	 * @return true if the player is still on the map, false if game is considered to not be running.
	 */
	@Override
	protected boolean stillRunning() {
		boolean humansSurvive = false;
		boolean zombiesSurvive = false;
		
		if (!actorLocations.contains(player) || !player.isConscious()) {
			return false;
		}
		
		for (Actor actor : actorLocations) {
			if (Human.class.isInstance(actor) && !Player.class.isInstance(actor)) {
				humansSurvive = true;
			}
			else if (Zombie.class.isInstance(actor)) {
				zombiesSurvive = true;
			}
		}
		
		if (!zombiesSurvive && !mamboMarie.isConscious()) {
			playerWins = true;
			return false;
		}
		else if (!humansSurvive) {
			playerWins = false;
			return false;
		}
		return actorLocations.contains(player);
	}
	
	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Player Wins!" or "Player loses!" depending on the outcome of the game
	 * "and Game Over"
	 */
	@Override
	protected String endGameMessage() {
		String out = "";
		if (playerWins) {
			out += "Player wins! \n";
		}
		else if (!playerWins) {
			out += "Player loses! \n";
		}
		out += "Game Over";
		return out;
	}

}