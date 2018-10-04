package net.lars.game2.tiles.tiles;

import net.lars.game2.graphics.Assets;
import net.lars.game2.tiles.Tile;

public class HillTile extends Tile{

	public HillTile(int id, int type) {
		super(Assets.hill[type], id);

	}
	
	@Override
	public boolean isSolid(){
		return true;
		
	}

}
