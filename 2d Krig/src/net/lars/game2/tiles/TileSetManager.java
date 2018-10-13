package net.lars.game2.tiles;

import java.util.HashMap;

import net.lars.game2.game.Handler;
import net.lars.game2.utils.fileUtils.FileUtils;
import net.lars.game2.utils.fileUtils.MyFile;

/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info:
 * 		Manages all loaded tilesets.
 * 		All Tilsets that are currently in use will be loaded and the ones that is not being used are unloaded.
 *
 * @TODO:
 * 
 * @Suggestions:
 * 		Integrate mutithreing. Then the loading and unloading can be done a an separate therad. In world class too.
 *
 */
public class TileSetManager {
	
	private HashMap<String, Tileset> loadedTilesets;
	private Handler handler;
	
	
	public TileSetManager(Handler handler){
		this.handler = handler;
		loadedTilesets = new HashMap<String, Tileset>();
	}
	
	public void addTileset(String name){
		Tileset t = new Tileset(new MyFile(FileUtils.TILESET_FOLDER + name + ".png"),new MyFile( FileUtils.TILESET_FOLDER + name + ".txt"));
		if(loadedTilesets.get(name) == null) {
			loadedTilesets.put(name, t);
		}else {
			loadedTilesets.get(name).incrementTilesetID(1);
		}
		
	}
	
	public void removeTileset(String name){
		if(loadedTilesets.get(name).getTilesetID() <= 1) {
			loadedTilesets.remove(name);
		}else {
			loadedTilesets.get(name).incrementTilesetID(-1);
		}
			
	}
	
	public Tile getTile(String tilesetName, int id){
		return loadedTilesets.get(tilesetName).getTile(id);
	}
}
