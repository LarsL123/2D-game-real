package net.lars.game2.worlds.chunk;

import net.lars.game2.tiles.TileID;

public class Chunk {
	
	/*
	 * A 8x8 array holding the tiles on a chunk.
	 * 
	 * May want to store tileEntities here to..
	 */
	private TileID[][] tiles;
	
	public Chunk() {
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y<7; y++) {
				//add tiles
			}
		}
	}
	
	public TileID getTileFromChunk(int x,int y) {
		return tiles[x][y];	
	}
	
}
