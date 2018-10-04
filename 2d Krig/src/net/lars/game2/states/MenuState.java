 package net.lars.game2.states;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;
import net.lars.game2.graphics.Assets;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UIManager;
import net.lars.game2.ui.tinyButtons.UIImageButton;

public class MenuState extends State{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
	
		uiManager.addObject(new UIImageButton(handler,uiManager.getMainUIObject(),new Vector2f(200,200), 150, 40, Assets.startButton[0], new ClickListener(){
			
			@Override
			public void onClick(Handler handler) {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler,uiManager.getMainUIObject(),new Vector2f(200,400), 150, 40, Assets.startButton[0], new ClickListener(){
			
			@Override
			public void onClick(Handler handler) {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().mapMakerState);
				
				
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler, uiManager.getMainUIObject(), new Vector2f(200,300), 150, 40, Assets.startButton[0], new ClickListener(){
			
			@Override
			public void onClick(Handler handler) {
				System.out.println("");
				System.out.println("------- Test State set -------------");
				System.out.println("");
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().testState);
				
				
			}
		}));

	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(RenderData d) {
//		uiManager.render(g);
	}

	@Override
	public void onStateSet() {
		
	}

}
