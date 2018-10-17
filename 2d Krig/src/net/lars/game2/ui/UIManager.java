package net.lars.game2.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.Handler;

/**
 * 	
 * @Date 17. June 2018
 * @Author LarsL123
 *
 * @Info:
 * 		A class for managing UI elements.
 * 
 * {@value}objects;
 * 		All the UIobjects.
 * 		The object with the lowest index will be rendered first.
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class UIManager {
	
	private Handler handler;
	
	/**
	 * The top level UIObject. All other UIObjects are controlled form this object.
	 */
	private UIObject mainUIObject;
	
	public UIManager(Handler handler){
		this.handler = handler;
		mainUIObject = new UIObject(handler, new Vector2f(0,0), handler.getWidth(), handler.getHeight()) {
			
			@Override
			public void render(Graphics g) {
				for(int i = 0; i <= childUIObjects.size() -1; i++) {
					childUIObjects.get(i).render(g);
				}
			}
			
			@Override
			public void onClick(Handler handler, MouseEvent e) {
				System.out.println("The main UIobject was clicked");
			}
			
			@Override
			public void onMouseRelease(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
					if(hovering){
						onClick(handler,e);
						for(UIObject o : childUIObjects){
							o.onMouseRelease(e);
						}
					}
				}
			}
		};
	}
	
	/**
	 * 
	 * @Info
	 * 		First calling on onMouseMove to update important variables.
	 * 		Then calling the tick method for all UIObject.
	 * @see 
	 */
	public void tick(){
	
		mainUIObject.onMouseMove((int)handler.getGame().getMouseManager().getCurrentMousePosition().getX(), (int)handler.getGame().getMouseManager().getCurrentMousePosition().getY());
		mainUIObject.tick();
	}
	
	/**
	 * 
	 * @Info
	 * 		Render all ui objects to a Graphics g object.
	 *
	 * @param g
	 * 		The Graphics object rendered to.
	 */
	public void render(Graphics g){
		mainUIObject.render(g);
	}
	
	//TODO make the one clicked the first
	public void onMouseRelese(MouseEvent e){
		mainUIObject.onMouseRelease(e);
//		int i2 = objects.size();
//		for(int i = 0; i < objects.size(); i++ ){
//			UIObject object = objects.get(i);
//			if(object.bounds.contains(e.getX(), e.getY())){
//				object.onMouseRelease(e);
//				if(i != 0){
//					objects.remove(i);
//					objects.add(0, object);
//				}
//				break;
//			}
//		}
	}
	
	public void addObject(UIObject o){
		mainUIObject.addChild(o);
	}
	
	public void removeObject(UIObject o){
		mainUIObject.removeChild(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public ArrayList<UIObject> getObjects() {
		return mainUIObject.getChildUIObjects();
	}
	
	public UIObject getMainUIObject() {
		return this.mainUIObject;
	}
}
