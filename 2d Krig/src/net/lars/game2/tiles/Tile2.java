package net.lars.game2.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.lars.game2.utils.Utils;

/**
 * 	
 * @Info:
 *		A class for holding information about a tile.
 *		A Tile is loaded up from text and image files. 
 * 		You will never load up a sprite alone, only in a tilset. You can find the load method in the Tileset class
 * 
 * @Date 4. juli 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions:
 *		Better ticking system ??
 */
public class Tile2 {
	
	public Tile2(BufferedImage texture){
		this.texture = texture;
		isSolid = false;
	}
	//Class
	
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
		
	private BufferedImage texture;
	private boolean isSolid;

	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEHEIGHT, TILEWIDTH, null);
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	public void setSolid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public void setValue(String id, String value){
		switch(id){
		case "isSolid":
			this.isSolid = Utils.readBoolean(value);
			break;		
		default:
			System.err.println("Invalid value was given to a Tile. Maybe an error in the Tilsets infoSheet");
			throw new IllegalArgumentException();
		
		}
	}
}
