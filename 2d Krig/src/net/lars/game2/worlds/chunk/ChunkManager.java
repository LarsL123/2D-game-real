package net.lars.game2.worlds.chunk;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.tiles.TileID;
import net.lars.game2.utils.Utils;
import net.lars.game2.utils.fileUtils.MyFile;

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
	
	public Chunk getChunk(int x, int y) throws NullPointerException{
		x/= 8;
		y/=8;
		return chunks.get(x).get(y);
	}
	
	public void deleteChunkFromMemory() {
		
	}
	
	public void saveChunkToFile() {
		
	}
	
	public void loadChunkToMemoy(int chunkX, int chunkY, MyFile path) throws Exception{
		
		BufferedReader reader = path.getReader();
		String lines = Utils.loadFileAsString(reader);

		String[] tokens = lines.split("\\s+");
		//I don't really see a reason to have a width and height stored since every chunk i 8x8. Can easily be removed.
		int width = Utils.parseInt(tokens[0]);
		int height = Utils.parseInt(tokens[1]);
		
		if(width != 8 && height != 8) {
			System.err.println("The chunk does not have the right size");
		}
		
		TileID[][] tiles = new TileID[Chunk.CHUNK_SIZE][Chunk.CHUNK_SIZE];
		for(int x =0;x < height; x++){
			for(int y = 0; y< width; y++){
				tiles[x][y] = new TileID(tokens[(x + y * width) + 2]);
			}
		}
		reader.close();
		
		Chunk chunk = new Chunk(new Vector2f(chunkX * Chunk.CHUNK_SIZE, chunkY * Chunk.CHUNK_SIZE), tiles);
		
		
		HashMap<Integer, Chunk> currentChunks = new HashMap<Integer, Chunk>();
		currentChunks.put(chunkY, chunk);
		chunks.put(chunkX, currentChunks);
	}
	
}
