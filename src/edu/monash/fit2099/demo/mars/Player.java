package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		return menu.showMenu(this, actions, display);
	}


	@Override
	public int getNumberOfArms() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLegs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item dropLimbs(GameMap map, Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dropItems(GameMap map, Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumberOfArms(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumberOfLegs(int n) {
		// TODO Auto-generated method stub
		
	}
}
