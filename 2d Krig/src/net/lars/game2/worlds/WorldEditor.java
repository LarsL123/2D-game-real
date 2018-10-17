package net.lars.game2.worlds;


import java.awt.event.KeyEvent;

import net.lars.game2.main.Handler;
import net.lars.game2.tiles.Tile;
import net.lars.game2.tiles.TileID;
import net.lars.game2.ui.UIManager;
import net.lars.game2.utils.fileUtils.MyFile;

/**
 * 	
 * @Info:
 * 		Hold a world an the funcsonallety to edit it easely.
 *
 * @Date 4. juli 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions
 *
 */
public class WorldEditor {
	
	private World world;
	private Handler handler;
	private UIManager uiManager;
	
	private Tile tile = Tile.hillTile1;
	
	public WorldEditor(Handler handler, World world){
		this.world = world;	
		this.handler = handler;
		uiManager = new UIManager(handler);
	}
	
	public void tick(){
		int x = (int) (handler.getMouseManager().getCurrentMousePosition().getX() + handler.getGameCamera().getxOffset()) / Tile.TILESIZE;
		int y = (int) (handler.getMouseManager().getCurrentMousePosition().getY()  +handler.getGameCamera().getyOffset()) / Tile.TILESIZE;
		if(handler.getMouseManager().isLeftPressed() && world.isShouldReadInput()){
			if(x >= 0 && x < handler.getWorld().getHeight() && y >= 0 && y < handler.getWorld().getHeight() ) {
				handler.getWorld().setWorldTile(x, y, new TileID(0, 5));
			}
			
		}
		

		
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
}
