package net.lars.game2.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.lars.game2.tiles.tiles.BasicGrass;
import net.lars.game2.tiles.tiles.CaveEntrence;
import net.lars.game2.tiles.tiles.DirtTile;
import net.lars.game2.tiles.tiles.GrassTile;
import net.lars.game2.tiles.tiles.HillTile;
import net.lars.game2.tiles.tiles.StandardWater;
import net.lars.game2.tiles.tiles.StoneTile;

public class Tile {
	
	//Static 
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile grassTile0 = new BasicGrass(0,0);
	public static Tile grassTile1 = new BasicGrass(1,1);
	public static Tile grassTile2 = new BasicGrass(2,2);
	public static Tile grassTile3 = new BasicGrass(3,3);
	public static Tile grassTile4 = new BasicGrass(4,4);
	public static Tile grassTile5 = new BasicGrass(5,5);
	public static Tile grassTile6 = new BasicGrass(6,6);
	public static Tile grassTile7 = new BasicGrass(7,7);
	public static Tile grassTile8 = new BasicGrass(8,8);
	
	public static Tile hillTile1 = new HillTile(9,0);
	public static Tile hillTile2 = new HillTile(10,1);
	public static Tile hillTile3 = new HillTile(11,2);
	public static Tile hillTile4 = new HillTile(12,3);
	public static Tile hillTile5 = new HillTile(13,4);
	public static Tile hillTile6 = new HillTile(14,5);
	public static Tile hillTile7 = new HillTile(15,6);
	public static Tile hillTile8 = new HillTile(16,7);
	
	public static Tile waterTile0 = new StandardWater(17,0);
	public static Tile waterTile1 = new StandardWater(18,1);
	public static Tile waterTile2 = new StandardWater(19,2);
	public static Tile waterTile3 = new StandardWater(20,3);
	public static Tile waterTile4 = new StandardWater(21,4);
	public static Tile waterTile5 = new StandardWater(22,5);
	public static Tile waterTile6 = new StandardWater(23,6);
	public static Tile waterTile7 = new StandardWater(24,7);
	public static Tile waterTile8 = new StandardWater(25,8);
	
	public static Tile caveEntrance0 = new CaveEntrence(26,0);
	public static Tile caveEntrance1 = new CaveEntrence(27,1);
	public static Tile caveEntrance2 = new CaveEntrence(28,2);
	public static Tile caveEntrance3 = new CaveEntrence(29,3);
	public static Tile caveEntrance4 = new CaveEntrence(30,4);
	public static Tile caveEntrance5 = new CaveEntrence(31,5);
	

	
	
	
	//Class
	
	public static final int TILESIZE = 32;
		
	protected BufferedImage texture;
	
	protected final int id;
	protected int type;
	
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;	
		this.id = id;
		
		tiles[id] = this;
	}
	
	public Tile(BufferedImage texture ,int id, int type ){
		this.texture = texture;	
		this.id = id;
		this.type = type;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILESIZE, TILESIZE, null);
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getType(){
		return type;
	}
}
