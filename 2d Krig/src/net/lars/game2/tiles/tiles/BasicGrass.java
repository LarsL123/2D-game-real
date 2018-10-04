package net.lars.game2.tiles.tiles;

import net.lars.game2.graphics.Assets;
import net.lars.game2.tiles.Tile;

public class BasicGrass extends Tile{

	public BasicGrass(int id, int garssType) {
		super(Assets.grassTiles[garssType], id);
	}

}
