package net.lars.game2.components;

import java.awt.Rectangle;
import java.util.UUID;

import net.lars.game2.component.Component;
import net.lars.game2.entity.Entities;
import net.lars.game2.entity.Entity;
import net.lars.game2.game.Handler;

public class BoundingBoxComponent extends Component {
	
	public static final int DEFAULT_HEIGHT = 40;
	public static final int DEFAULT_WIDTH = 20;
	
	private Rectangle bounds;

	public Entity parent;
	
	public BoundingBoxComponent(Handler handler, Entity parent,int x, int y, int width, int height){
		super(handler);
		this.parent = parent;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public BoundingBoxComponent(Handler handler, Entity parent){
		super(handler);
		this.parent = parent;
		bounds = new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int)(parent.getX() + bounds.x + xOffset),
				(int) (parent.getY() + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	@Override
	public void tick() {
	}
	
}
