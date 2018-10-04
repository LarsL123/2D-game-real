package net.lars.game2.tiles.tiles;

import net.lars.game2.graphics.Assets;
import net.lars.game2.tiles.Tile;

public class CaveEntrence extends Tile{

	public CaveEntrence(int id, int type) {
		super(Assets.caveEntrance[type], id,type);
	}
	
	@Override
	public boolean isSolid(){
		if(type == 4){
			return false;
		}
		return true;
	}
		
}
