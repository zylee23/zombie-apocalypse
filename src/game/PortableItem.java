package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getShootDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
