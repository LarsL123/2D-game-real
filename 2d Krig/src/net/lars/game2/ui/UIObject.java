package net.lars.game2.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.Handler;

/**
 * 	
 * @Info
 * 		The superclass for all UIObject / elements
 *
 * @Date 9. juli 2018
 * @Author LarsL123
 *
 *
 * @TODO
 * 		-Port the game to openGl. This will make scaling and stuff like that easier.
 * 
 * @Suggestions
 *
 */
public abstract class UIObject {
	
	protected Vector2f position;
	protected int width, height;
	protected boolean active = true;
	protected boolean hovering = false;
	protected Rectangle bounds;
	protected Handler handler;
	
	protected UIObject parent;
	
	protected List<UIObject> childUIObjects;
	
	public UIObject(Handler handler, UIObject parent, Vector2f position, int width, int height){
		this.position = new Vector2f(position);
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
		this.handler = handler;
		this.parent = parent;
		this.childUIObjects = new ArrayList<UIObject>();
	}
	
	public UIObject(Handler handler, Vector2f position, int width, int height){
		this.position = new Vector2f(position);
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
		this.handler = handler;
		this.childUIObjects = new ArrayList<UIObject>();
	}
	
	
	public float getXRelativeToParent(){
		if(this.parent == null){
			return this.position.getX();
		}else{
			return this.position.getX() + parent.getXRelativeToParent();
		}
		
	}
	
	public float getYRelativeToParent(){
		if(this.parent == null){
			return this.position.getY();
		}else{
			return this.position.getY() + parent.getXRelativeToParent();
		}
		
	}
	
	/**
	 * 
	 * @Info:
	 * 		Ticking all components. If Overwritten then the child's will not be ticked.
	 *
	 */
	public void tick(){
		if( childUIObjects.size() == 0 ){
			return;
		}
		for(UIObject o : childUIObjects){
			if(o.active) {
				o.tick();
			}
			
		}
	}
	
	/**
	 * 
	 * @Info:
	 * 	Provides a copy of this class. If an element has not implemented the method, then the method will return null.
	 * 	I don't know if this method is working. Find that out if you use it.
	 *
	 * @return
	 * 		The new UIObject.
	 */
	public UIObject dupliacte(){
		try {
			return (UIObject) this.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println("Was not able to duplicate the UIObject" + this);
			e.printStackTrace();
			return null;
		}
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void onClick(Handler handler, MouseEvent e);
	
	/**
	 * 
	 * @Info
	 *		Called every time the game is UIManger is ticked.
	 * 		Updates the {@link #hovering} variable.
	 *
	 * @param mouseX
	 * 		The current mouse x position
	 * @param mouseY
	 * 		The current move y position
	 */
	public void onMouseMove(int mouseX, int mouseY){
		if(this.bounds.contains(mouseX, mouseY)){
			hovering = true;
			for(UIObject o: childUIObjects) {
				o.onMouseMove(mouseX, mouseY);
			}
		}else{
			if(!hovering) return;
			
			hovering = false;
			for(UIObject o : childUIObjects) {
				o.setHovering(false);
			}
		}
	}
	
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
	
	//Getter and setters
	
	public float getX(){
		return this.position.getX();
		
	}
	
	public void setX(float x){
		this.position.setX(x);
	}
	
	public float getY(){
		return this.position.getX();
		
	}
	
	public void setY(float y){
		this.position.setY(y);
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	public void addChild(UIObject o){
		childUIObjects.add(o);
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean visible) {
		this.active = visible;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public UIObject getParent() {
		return parent;
	}

	public ArrayList<UIObject> getChildUIObjects() {
		return (ArrayList<UIObject>) childUIObjects;
	}

	public void removeChild(UIObject o){
		childUIObjects.remove(o);
	}
}
