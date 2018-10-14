package net.lars.game2.tiles;

import java.util.ArrayList;
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
 * 		Integrate mutithreing. Then the loading and unloading can be done a an separate thread. In world class too.
 *
 */
public class TileSetManager {
	private ArrayList<Tileset> loadedTilesets;
	private Handler handler;
	
	
	public TileSetManager(Handler handler){
		this.handler = handler;
		loadedTilesets = new ArrayList<Tileset>();
	}
	
	public void addTileset(String name){
		Tileset t = new Tileset(new MyFile(FileUtils.TILESET_FOLDER + name + ".png"),new MyFile( FileUtils.TILESET_FOLDER + name + ".txt"));
		t.setTilesetID(loadedTilesets.size());
		loadedTilesets.add(t);

	}
	
	public void removeTileset(int tilesetID){
		loadedTilesets.remove(tilesetID);
			
	}
	
	public Tile getTile(int tileSetID, int tileId){
		return loadedTilesets.get(tileSetID).getTile(tileId);
	}
	
	//TODO implement this
	public Tile getTile(TileID i ){
		return loadedTilesets.get(i.getTilesetID()).getTile(i.getTileID());
	}
}
