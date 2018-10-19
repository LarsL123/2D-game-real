package net.lars.game2.worlds.chunk;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.Config;
import net.lars.game2.main.Handler;
import net.lars.game2.tiles.Tile;
import net.lars.game2.tiles.TileID;
import net.lars.game2.utils.Utils;
import net.lars.game2.utils.fileUtils.FileUtils;
import net.lars.game2.utils.fileUtils.MyFile;

public class ChunkManager {
	
	
	private Handler handler;
	
	private Map<Integer, HashMap<Integer, Chunk>> chunks  = new HashMap<Integer, HashMap<Integer, Chunk>>();
	
	public ChunkManager(Handler handler) {
		this.handler = handler;
	}
	
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
	
	public Chunk getChunk(int x, int y){
		try {
			return chunks.get(x).get(y); 
		} catch(NullPointerException  e){
//			System.out.println(x +", "+ y +" Gave no chunk.");
			return null;
		}
		
	}
	
	public void deleteChunkFromMemory() {
		
	}
	
	public void saveChunkToFile() {
		
	}
	
	public void loadChunkToMemoy(int chunkX, int chunkY) throws Exception{
		BufferedReader reader;
		try {
			reader = new MyFile(FileUtils.WORLD_FOLDER + chunkX +"-" + chunkY).getReader();
		}catch(Exception e){
			if(Config.loadNewChunks) {
				createEmptyChunk(chunkX, chunkY);
				return;
			}
			throw e;
		}
		{
		
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
		
		Chunk chunk = new Chunk(new Vector2f(chunkX,chunkY ), tiles);
		
		
		HashMap<Integer, Chunk> currentChunks = new HashMap<Integer, Chunk>();
		currentChunks.put(chunkY, chunk);
		chunks.put(chunkX, currentChunks);
	}
	
	private void createEmptyChunk() {
		
	}

	public void update() {
		int xStart = (int)(handler.getGameCamera().getxOffset() / Tile.TILEHEIGHT)/8 - 8;
		int xEnd = (int)((handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEHEIGHT)/8 + 8;
		int yStart = (int) (handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT)/8 - 8;
		int yEnd = (int)((handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT)/8 + 8;
		addNewChunks(xStart, xEnd, yStart, yEnd);
		removeOldChunks(xStart, xEnd, yStart, yEnd);
	}
	
	private void removeOldChunks(int xStart, int xEnd ,int yStart, int yEnd) {
		for(Integer x : chunks.keySet()){		
			for(Integer y: chunks.get(x).keySet()) {
				if(x < xStart || x>xEnd) {
					if(y < yStart || y > yEnd) {
						chunks.get(x).remove(y);
						if(chunks.get(x).size() <1) {
							chunks.remove(x);
						}
					}
				}
			}
		}
	}
	
	private void addNewChunks(int xStart, int xEnd ,int yStart, int yEnd) {
		for(int y = yStart; y<yEnd; y++){		
			for(int x = xStart; x<xEnd; x++) {
				if(chunks.get(x).get(y) == null) {
					loadChunkToMemoy(x, y);
				}
			}
		}
	}
//	public Chunk createEmpyChunk(int x, int y) {
//		BufferedReader reader = path.getReader();
//		String lines = Utils.loadFileAsString(reader);
//
//		String[] tokens = lines.split("\\s+");
//		//I don't really see a reason to have a width and height stored since every chunk i 8x8. Can easily be removed.
//		int width = Utils.parseInt(tokens[0]);
//		int height = Utils.parseInt(tokens[1]);
//		
//		if(width != 8 && height != 8) {
//			System.err.println("The chunk does not have the right size");
//		}
//		
//		TileID[][] tiles = new TileID[Chunk.CHUNK_SIZE][Chunk.CHUNK_SIZE];
//		for(int x =0;x < height; x++){
//			for(int y = 0; y< width; y++){
//				tiles[x][y] = new TileID(tokens[(x + y * width) + 2]);
//			}
//		}
//		reader.close();
//		
//		Chunk chunk = new Chunk(new Vector2f(chunkX,chunkY ), tiles);
//		
//		
//		HashMap<Integer, Chunk> currentChunks = new HashMap<Integer, Chunk>();
//		currentChunks.put(chunkY, chunk);
//		chunks.put(chunkX, currentChunks);
//	}
//	
//	public void saveChunk(MyFile file) {
//		try {
//			int width;
//			int height;
//			Vector2f spawnPoint;
//		
//			//Building a string from the world data.
//			StringBuilder sb = new StringBuilder();
//			
//			sb.append(width + " " + height + System.lineSeparator());
//			sb.append(spawnPoint.getX() +  " " + spawnPoint.getY() + System.lineSeparator());
//			
//			for(int y =0;y < height; y++){
//				for(int x = 0; x < width; x++){
//					sb.append(worldTiles[x][y].getTilesetID() + ":" + worldTiles[x][y].getTileID() + " ");
//				}
//			}
//			
//			//Writing the String to the String fileName file.
//			File f = new File(FileUtils.RES_FOLDER + file.getPath());
//			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//			bw.write(sb.toString());
//			bw.close();
//			
//		} catch (IOException e) {
//			System.err.println("Was not able to save world: " + file.getPath());
//			e.printStackTrace();
//		}
//	}
}
