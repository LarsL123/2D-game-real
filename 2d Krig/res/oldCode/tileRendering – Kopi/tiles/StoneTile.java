package net.lars.game2.tiles.tiles;

import net.lars.game2.graphics.Assets;
import net.lars.game2.tiles.Tile;

public class StoneTile extends Tile{

	public StoneTile(int id) {
		super(Assets.stoneTile, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
		
	}

}
