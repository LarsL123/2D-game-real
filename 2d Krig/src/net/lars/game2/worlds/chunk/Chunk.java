package net.lars.game2.worlds.chunk;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.tiles.TileID;

/**
 * 	
 * @Info
 * 		This class represents a chunk of the tiles in my world.
 *
 * @Date 12. oct. 2018
 * @Author LarsL123
 *
 *
 * @TODO
 * 
 * @Suggestions
 * 	Make a tile-entity system. the tile entities will probably be stored in the class.
 *
 */
public class Chunk {
	
	public static final int CHUNK_SIZE = 8;
	
	/*
	 * A 8x8 array holding the tiles on a chunk.
	 * 
	 * May want to store tileEntities here to..
	 */
	private TileID[][] tiles;
	
	/**
	 * The world position of the top left in in the chunk;
	 */
	private Vector2f worldPosition;
	
	public Chunk(Vector2f worldposition) {
		this.worldPosition = worldposition;
		
		tiles = new TileID[7][7];
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y<8; y++) {
				//add tiles
			}
		}
	}
	
	/**
	 * 
	 *@Info
	 *
	 * Get a tile from this chunk. This in is not supposerd to be used by other packages- @see {@link ChunkManager #getTile(int, int)}
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	TileID getTileFromChunk(int x,int y) {
		return tiles[x][y];	
	}
	
	public Vector2f getWorldPosition(){
		return this.worldPosition;
	}
	
	public TileID[][] getTiles() {
		return this.tiles;
	}
	
}
