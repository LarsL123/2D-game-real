package net.lars.game2.tiles;

import java.io.BufferedReader;
import java.util.ArrayList;

import net.lars.game2.engine.textures.Texture;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.graphics.ImageLoader;
import net.lars.game2.graphics.SpriteSheet;
import net.lars.game2.utils.fileUtils.MyFile;

/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info:
 * 		Holds the information about a tile set.
 * 		Tilesets are loaded up from a info and a spriteSheet file.
 *		Spritesheet and infosheet examples are provides in res/examples/Tileset example/
 * @TODO:
 * 		Make the tileset Id working.
 * 
 * @Suggestions:
 *
 */
public class Tileset {
	
	//Private
	private int tilesetID;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private Texture tileSet;
	
	//TODO not the prettyest
	//Accessed by the Tile2 class, so public static
//	public static double tileSizeInTextureCoords = 0.04545454545000000;
	public static double tileSizeInTextureCoords = 0.05;
	
	
	
	public Tileset(MyFile imageSheet, MyFile infoSheet) {
		try {
			//Not the way it should be done.
			tileSet = Assets2.loadNearestTexture("res" + imageSheet.getPath());
			loadTileset(infoSheet);
			tilesetID = 0;
		} catch (Exception e) {
			System.err.println("Was not able to load tilset");
			e.printStackTrace();
		}
	}
	
	private ArrayList<Tile> loadTileset(MyFile infoSheet) throws Exception{
		BufferedReader infoSheetReader = infoSheet.getReader();

		String line;
		line = infoSheetReader.readLine();
		do{
			//Skipping the line if it is a comment.
			if(line.startsWith("//")){
				line = infoSheetReader.readLine();
				continue;
			}
			
			//Dividing the line into smaller pieces. Each piece has a identification string and a value. Example: isSolid=t
			String[] currentLine = line.split(";");
			
			//Creating the tile.
			String[] xyPosition = currentLine[0].split(",");
			Tile t = new Tile((float)(Integer.parseInt(xyPosition[0]) * tileSizeInTextureCoords), (float)(Integer.parseInt(xyPosition[1]) * tileSizeInTextureCoords));
			
			//Adding all values
			for(int i = 1; i < currentLine.length; i++){
				String[] values = currentLine[i].split("=");
				t.setValue(values[0], values[1]);
			}
			tiles.add(t);
			line = infoSheetReader.readLine();
		}while(line != null);
		infoSheetReader.close();
		
		return tiles;
		
	}
	
	
	public Tile getTile(int id){
		return tiles.get(id);
		
	}
	
	public Texture getTilsetTexture() {
		return this.tileSet;
	}
	
	public int getTilesetID() {
		return tilesetID;
	}

	public void setTilesetID(int tilesetID) {
		this.tilesetID = tilesetID;
	}
	
	/**
	 * 
	 * @Info:
	 *
	 * @param incrementer
	 * 			What you want to add to the current tilesetID.
	 */
	public void incrementTilesetID(int incrementer) {
		this.tilesetID += incrementer;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
}
