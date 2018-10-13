package net.lars.game2.worlds.chunk;

import java.util.HashMap;
import java.util.Map;

import net.lars.game2.tiles.TileID;

public class ChunkManager {
	
	private Map<Integer, HashMap<Integer, Chunk>> chunks = new HashMap<Integer, HashMap<Integer, Chunk>>();
	
	/**
	 * 	Get a tile using a world position.
	 * @param x
	 * 		The world x position.
	 * @param y
	 * 		The world y position.
	 * 
	 */
	public TileID getTile(int x, int y) {
		int chunkX = x/Chunk.CHUNK_SIZE;		
		int chunkY = y/Chunk.CHUNK_SIZE;
		
		int tileX = x%Chunk.CHUNK_SIZE;
		int tileY = y%Chunk.CHUNK_SIZE;
		
		TileID tile;
		
		//TODO not a good error catch. Look at a better solution later.
		try {
			 tile = chunks.get(chunkX).get(chunkY).getTileFromChunk(tileX, tileY);
		} catch (Exception e) {
			System.err.println("Was not able to get tile at: "  + x + ", " +y);
			tile = null;
		} 
		return tile;
		
	}
	
	public Chunk getChunk(int x, int y) {
		x/= 8;
		y/=8;
		return chunks.get(++x).get(++y);
	}
	
	public void deleteChunkFromMemory() {
		
	}
	
	public void loadChunkToMemoy(int chunkX, int chunkY, Chunk chunk) {
		HashMap<Integer, Chunk> currentChunks = new HashMap<Integer, Chunk>();
		currentChunks.put(chunkY, chunk);
		chunks.put(chunkX, currentChunks);
	}
	
}
