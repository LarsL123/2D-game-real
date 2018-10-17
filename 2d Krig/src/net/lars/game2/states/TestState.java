package net.lars.game2.states;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.graphics.Assets;
import net.lars.game2.main.Handler;
import net.lars.game2.main.RenderData;
import net.lars.game2.tiles.Tile;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UI2DItemSelection;
import net.lars.game2.ui.selectionBar.UITextSelectionBar;
import net.lars.game2.worlds.World;
import net.lars.game2.worlds.WorldEditor;

public class TestState extends State{
	
	private WorldEditor worldEditor;
	private UITextSelectionBar bar;
	
	public TestState(Handler handler) {
		super(handler);
		
	}

	@Override
	public void tick() {
		if(worldEditor != null){
			worldEditor.tick();
			worldEditor.getUiManager().tick();
			worldEditor.getWorld().tick();
			if(worldEditor.getWorld().isShouldReadInput()) {
				handler.getGameCamera().moveWidthInputs();
			}
			
		}
	}

	@Override
	public void render(RenderData d) {
		if(worldEditor != null){
//			worldEditor.getWorld().render(g);
//			worldEditor.getUiManager().render(g);
		}
		
	}

	@Override
	public void onStateSet() {
		World world = new World(handler, 20,20);
		handler.setWorld(world);

		worldEditor = new WorldEditor(handler, world);
		
		String[] text = {"Knapp 1", "Knapp 2", "Knapp 3", "knapp 4", "knapp 5"};
		ClickListener[] clicListener = 
			{
				
				new ClickListener() {
			
					@Override
					public void onClick(Handler handler) {
						worldEditor.setTile(Tile.grassTile0);
				
					};}
				
				, new ClickListener() {
					
					@Override
					public void onClick(Handler handler) {
						worldEditor.setTile(Tile.caveEntrance0);
						
					};
				}
				, new ClickListener() {
					
					@Override
					public void onClick(Handler handler) {
						worldEditor.setTile(Tile.hillTile1);
						
					};
				}
				, new ClickListener() {
	
					@Override
					public void onClick(Handler handler) {
						worldEditor.setTile(Tile.waterTile0);
		
					};
				}	
				, new ClickListener() {
	
					@Override
					public void onClick(Handler handler) {
						worldEditor.setTile(Tile.hillTile8);
		
					};
				}
		};
		
		bar = new UITextSelectionBar(handler,new Vector2f(100,400), 600, 30, new Rectangle( 0, 0, 20, 20), text, clicListener);
		
		worldEditor.getUiManager().addObject(bar);
		//2D Selecton 
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for(int i = 0; i < 10; i++){
			images.add(Assets.item1);
			
		}
		for(int i = 10; i < 30; i++){
			images.add(Assets.item2);
		}
		UI2DItemSelection ui = new UI2DItemSelection(handler,worldEditor.getUiManager().getMainUIObject(), new Vector2f(200,200), images , 6, 5);
		
		worldEditor.getUiManager().addObject(ui);
		
		handler.getMouseManager().setUIManager(worldEditor.getUiManager());
	}

}
