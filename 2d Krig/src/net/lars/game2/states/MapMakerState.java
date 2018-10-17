package net.lars.game2.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.graphics.Assets;
import net.lars.game2.main.Handler;
import net.lars.game2.main.RenderData;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UI2DItemSelection;
import net.lars.game2.ui.UITextField;
import net.lars.game2.ui.selectionBar.UIToolbarHorisontal;
import net.lars.game2.ui.tinyButtons.UIImageButton;
import net.lars.game2.utils.fileUtils.MyFile;
import net.lars.game2.worlds.World;
import net.lars.game2.worlds.WorldEditor;


/**
 * 	
 * @Info:
 * 		The state that is activated what you are editing a world.
 *
 * @Date 26. juni 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class MapMakerState extends State{
	
	private WorldEditor worldEditor;
	private UITextField tf;
	
	public MapMakerState(Handler handler) {
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
//		if(worldEditor != null){
//			worldEditor.getWorld().render(g);
//			worldEditor.getUiManager().render(g);
//		}
		
	}
	
	public void saveWorld() {
		
	}

	@Override
	public void onStateSet() {
		System.out.println("--------------------- Map maker state set --------------------");
		
		World world = new World(handler, new MyFile("worlds/world1NewTile.txt"), false);
		handler.setWorld(world);

		worldEditor = new WorldEditor(handler, world);
		
//		String[] text = {"Knapp 1", "Knapp 2", "Knapp 3", "knapp 4", "knapp 5"};
//		ClickListener[] clicListener = 
//			{
//				
//				new ClickListener() {
//			
//					@Override
//					public void onClick(Handler handler) {
//						worldEditor.setTile(Tile.grassTile0);
//				
//					};}
//				
//				, new ClickListener() {
//					
//					@Override
//					public void onClick(Handler handler) {
//						worldEditor.setTile(Tile.caveEntrance0);
//						
//					};
//				}
//				, new ClickListener() {
//					
//					@Override
//					public void onClick(Handler handler) {
//						worldEditor.setTile(Tile.hillTile1);
//						
//					};
//				}
//				, new ClickListener() {
//	
//					@Override
//					public void onClick(Handler handler) {
//						worldEditor.setTile(Tile.waterTile0);
//		
//					};
//				}	
//				, new ClickListener() {
//	
//					@Override
//					public void onClick(Handler handler) {
//						worldEditor.setTile(Tile.hillTile8);
//		
//					};
//				}
//		};
		
//		bar = new UITextSelectionBar(handler, new Vector2f(100,400), 600, 30, new Rectangle( 0, 0, 20, 20), text, clicListener);
//		
//		worldEditor.getUiManager().addObject(bar);
		
		//2D selecton 
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for(int i = 0; i < 10; i++){
			images.add(Assets.item1);
			
		}
		for(int i = 10; i < 30; i++){
			images.add(Assets.item2);
		}
		UI2DItemSelection ui = new UI2DItemSelection(handler, worldEditor.getUiManager().getMainUIObject(),new Vector2f(200,200), images , 6, 5);
		
		worldEditor.getUiManager().addObject(ui);
		
		UIToolbarHorisontal bar = new UIToolbarHorisontal(handler,  worldEditor.getUiManager().getMainUIObject(), new Vector2f(0,0), 600,40);
	
		
		tf = new UITextField(handler, worldEditor.getUiManager().getMainUIObject(), new Vector2f(40,0), 200,bar.getHeight());
		bar.addChild(tf);
		bar.addChild(new UIImageButton(handler, bar, new Vector2f(0,0), bar.getHeight(), bar.getHeight(), Assets.buttonImage1, new ClickListener() {
			
			@Override
			public void onClick(Handler handler) {
				System.out.println("Saving the world as" + tf.getText());
				worldEditor.getWorld().saveWorld(new MyFile("worlds/" + tf.getText() + ".txt"));
				
				
			}
		}));
		worldEditor.getUiManager().addObject(bar);
		
		handler.getMouseManager().setUIManager(worldEditor.getUiManager());
	}

}
