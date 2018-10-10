package net.lars.game2.tiles.tiles;

import net.lars.game2.graphics.Assets;
import net.lars.game2.tiles.Tile;

public class StandardWater extends Tile{

	public StandardWater(int id, int type) {
		super(Assets.standardWater[type], id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
