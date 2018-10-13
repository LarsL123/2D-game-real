package net.lars.game2.tiles;

/**
 * 	
 * @Info:
 * 		Information of a tile when it is loaded up from a world.
 * 		Used in the world class.
 *
 * @Date 8. juli 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 		change the tileID to a byte number. you will probably not have more the 255 tiles in one tileSet To save space and memory.
 * 
 * @Suggestions:
 *
 */
public class TileID {
	
	private int tilesetID;
	private int tileID;
	
	public TileID(int tilesetID, int tileID) {
		this.tilesetID = tilesetID;
		this.tileID = tileID;
	}
	
	/**
	 * 
	 * @Info:
	 * 		Load the tile from a string.
	 *
	 * @param idAsString
	 * 		Example: 20:10
	 * 		The above is tileset 20 and tile 10 in that tileset.
	 */
	public TileID(String idAsString){
		String[] nums = idAsString.split(":");
		this.tilesetID = Integer.parseInt(nums[0]);
		this.tileID = Integer.parseInt(nums[1]);
		
	}

	public int getTilesetID() {
		return tilesetID;
	}

	public void setTilesetID(int tilesetID) {
		this.tilesetID = tilesetID;
	}

	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}
	
	
	
	
}
