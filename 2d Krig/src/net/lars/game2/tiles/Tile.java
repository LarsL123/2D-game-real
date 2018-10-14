package net.lars.game2.tiles;

import org.lwjgl.util.vector.Vector2f;

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
public class Tile {
	
	
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	public static Tile errorTile = new Tile((float)(10* Tileset.tileSizeInTextureCoords), (float)(10*Tileset.tileSizeInTextureCoords));
	
	
	//Class
	private boolean isSolid;
	
	private Vector2f pos;
	
	
	public Tile(float x, float y){
		pos = new Vector2f(x, y);
		isSolid = false;
	}

	
	public void tick(){
		
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	public void setSolid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public float getX() {
		return this.pos.x;
	}
	
	public float getY() {
		return this.pos.y;
	}
	
	public Vector2f getPosition() {
		return this.pos;
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
