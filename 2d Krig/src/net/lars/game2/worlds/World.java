package net.lars.game2.worlds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.entity.Caracter;
import net.lars.game2.entity.EntityManager;
import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;
import net.lars.game2.graphics.Assets;
import net.lars.game2.items.ItemManager;
import net.lars.game2.tiles.Tile;
import net.lars.game2.tiles.TileID;
import net.lars.game2.tiles.TileSetManager;
import net.lars.game2.utils.Utils;
import net.lars.game2.utils.fileUtils.MyFile;

/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info
 * 		The world. Contains the tile grid and all the entities.
 *
 * @TODO
 * 		Lock at the memory management thing. find out how memory a byte will help us when defining tiles.
 * 		https://stackoverflow.com/questions/258120/what-is-the-memory-consumption-of-an-object-in-java
 * 
 * @Suggestions:
 *
 */
public class World {
	
	private Handler handler;

	private Vector2f spawnPoint;
	private int width, height;
	private TileID[][] worldTiles;

	/**
	 * 	True if the members of the world should read input from the keyboard, and false if another task is reading form the keyBoard.
	 * 	Used by all members of world.
	 */
	private boolean shouldReadInput = true;
	
	private MyFile file;
	
	//Entities
	private EntityManager entityManager;
	
	//Tilesets
	private TileSetManager tilesetManager;
	private String[] tilesetsInUse;

	//Items
	private ItemManager itemManager;
	
	/**
	 * 	Load a world from a world file.
	 * @param handler
	 * @param path
	 * @param spawnCaracter
	 */
	public World(Handler handler, MyFile path, boolean spawnCaracter){
		this.handler = handler;
		this.file = path;
		try {
			loadWorld(file);
		} catch (Exception e) {
			System.err.println("Was not able to load wordl: " + path.getPath());
			e.printStackTrace();
		}
		
		tilesetManager = new TileSetManager(handler);
		//Manually adding tileset
		for(String s : tilesetsInUse) {
			tilesetManager.addTileset(s);
		}
		
		if(spawnCaracter){
			entityManager = new EntityManager(handler, new Caracter(handler, new Vector2f(100f, 100f), 50f, 50f));
			entityManager.getCaracter().setPosition(new Vector2f(spawnPoint));
		}else{
			entityManager = new EntityManager(handler, null);
		}
		
		itemManager = new ItemManager(handler);	
	}
	
	public World(Handler handler, int width, int height){
		this.handler = handler;
		
		loadWorld(width, height);
		
		tilesetManager = new TileSetManager(handler);
		//Manually adding tileset
		for(String s : tilesetsInUse) {
			tilesetManager.addTileset(s);
		}
		
		entityManager = new EntityManager(handler, null);
		
		itemManager = new ItemManager(handler);
	}


	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(RenderData data){
		//Entities
		entityManager.render(data);
		
//		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILESIZE);
//		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILESIZE +1);
//		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILESIZE);
//		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILESIZE +1);
//		
//		List<Vector2f> positions = new ArrayList<Vector2f>();
//		
//		for(int y = yStart;y < yEnd; y++){
//			for(int x = xStart; x < xEnd; x++){
//				positions.add(new Vector2f((float )x, (float)y));
//				
////				getTile(x, y).render(g, (int)(x * Tile.TILESIZE - handler.getGameCamera().getxOffset()),(int) (y * Tile.TILESIZE - handler.getGameCamera().getyOffset()));
//			}
//		}
//		return  positions;
//		//Items
//		itemManager.render(g);
		

	}
	
	public ArrayList<Tile> getTileRenderData() {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEHEIGHT);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEHEIGHT +1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT +1);
		
		ArrayList<Tile> tilesArray = new ArrayList<Tile>();
		
		for(int y = yStart;y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				tilesArray.add(getTile(x, y));
//				getTile(x, y).render(g, (int)(x * Tile.TILESIZE - handler.getGameCamera().getxOffset()),(int) (y * Tile.TILESIZE - handler.getGameCamera().getyOffset()));
			}
		}
		System.out.println("111");
		return tilesArray;
	}
	
	public ArrayList<Tile> getTileRenderDataa() {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEHEIGHT);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEHEIGHT +1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT +1);
		
		ArrayList<Tile> tilesArray = new ArrayList<Tile>();
		
		for(int y = yStart;y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				tilesArray.add(getTile(x, y));
//				getTile(x, y).render(g, (int)(x * Tile.TILESIZE - handler.getGameCamera().getxOffset()),(int) (y * Tile.TILESIZE - handler.getGameCamera().getyOffset()));
			}
		}
		System.out.println("111");
		return tilesArray;
	}

	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.errorTile;	
		}
		Tile t = tilesetManager.getTile(tilesetsInUse[worldTiles[x][y].getTilesetID()], worldTiles[x][y].getTileID());
		if(t == null){
			return Tile.errorTile;
		}
		return t;
	}
	
	/**
	 * 
	 * @Info:
	 * 		Saving all the world data to a file.
	 *
	 * @param saveFile
	 * 		The file that will be written  to.
	 *  	If it exists, it will be overwritten.
	 */ 
	public void saveWorld(MyFile file) {
		try {
			//Building a string from the world data.
			StringBuilder sb = new StringBuilder();
			for(String s: tilesetsInUse) {
				sb.append(s + ";");
			}
			sb.append(System.lineSeparator());
			sb.append(width + " " + height + System.lineSeparator());
			sb.append(spawnPoint.getX() +  " " + spawnPoint.getY() + System.lineSeparator());
			
			for(int y =0;y < height; y++){
				for(int x = 0; x < width; x++){
					sb.append(worldTiles[x][y].getTilesetID() + ":" + worldTiles[x][y].getTileID() + " ");
				}
			}
			
			//Writing the String to the String fileName file.
			File f = new File("res" + file.getPath());
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(sb.toString());
			bw.close();
			
		} catch (IOException e) {
			System.err.println("Was not able to save world: " + file.getPath());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Info:
	 * 		Loading the world from a world file. 
	 *
	 * @param path
	 * @throws Exception
	 */
	public void loadWorld(MyFile path) throws Exception{
		BufferedReader reader = path.getReader();
		String firstLine = Utils.readLine(reader);
		String theRest = Utils.loadFileAsString(reader);
		
		tilesetsInUse = firstLine.split(";");
		
		String[] tokens = theRest.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		this.spawnPoint = new Vector2f(Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]));
		
		worldTiles = new TileID[width][height];
		for(int y =0;y < height; y++){
			for(int x = 0; x < width; x++){
				worldTiles[x][y] = new TileID(tokens[(x + y * width) + 4]);
			}
		}
		reader.close();
	}
	
	public void loadWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.spawnPoint = new Vector2f(0,0);
		
		tilesetsInUse = new String[1];
		tilesetsInUse[0] = "world1tiles";
		
		worldTiles = new TileID[width][height];
		for(int y =0;y < height; y++){
			for(int x = 0; x < width; x++){
				worldTiles[x][y] = new TileID(0,3);
			}
		}
	}
	
	
	//Getters and setters
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public void setWorldTile(int x, int y, TileID t) {
		this.worldTiles[x][y] = t;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isShouldReadInput() {
		return shouldReadInput;
	}

	public void setShouldReadInput(boolean shouldReadInput) {
		this.shouldReadInput = shouldReadInput;
	}

	/**
	 * 
	 * @Info:
	 * 		kind of hard to use, room for a lot of error.
	 *
	 * @param x
	 * @param y
	 * @param tileset
	 * 		The name of the tileset you want to use.
	 * @param id
	 */
	public void setWorldTile(int x, int y, String tileset, int id) {
		this.worldTiles[x][y] = new TileID(tileset +  ":" + id);
	}
}
