package net.lars.game2.entity;

import java.awt.image.BufferedImage;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.components.BoundingBoxComponent;
import net.lars.game2.components.InformationComponent;
import net.lars.game2.engine.textures.Texture;
import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;



public class Entity extends Entities{
	
	protected Texture texture;
	
	protected Vector2f position;
	protected float width, height;
	
	public Entity(Handler handler, Texture textureGL, Vector2f pos, float width, float height) {
		super(handler);
		this.texture = textureGL;
		position = new Vector2f(pos);
		this.width = width;
		this.height = height;
		InformationComponent info = new InformationComponent(handler, this);
		BoundingBoxComponent box = new BoundingBoxComponent(handler, this);
		addComponent(box);
		addComponent(info);
	}
	
	public Entity(Handler handler, Texture textureGL, Vector2f pos, float width, float height, int boundsX, int boundsY, int boundsWidth, int boundsHeight) {
		super(handler);
		this.texture = textureGL;
		position = new Vector2f(pos);
		this.width = width;
		this.height = height;
		InformationComponent info = new InformationComponent(handler, this);
		BoundingBoxComponent box = new BoundingBoxComponent(handler, this, boundsX, boundsY, boundsWidth, boundsHeight);
		addComponent(box);
		addComponent(info);
	}

	public void render(RenderData data){
//		g.drawImage(getComponents(InformationComponent.class).texture, (int) (position.getX() - handler.getGameCamera().getxOffset()),
//				(int) (position.getY() - handler.getGameCamera().getyOffset()), (int) (width + getComponents(BoundingBoxComponent.class).getBounds().getWidth()),
//				(int)(height + getComponents(BoundingBoxComponent.class).getBounds().getHeight()), null);
//		
		data.addEntity(this);
		
		//Show hitbox
//		g.setColor(Color.RED);
//		g.fillRect((int)(x + getComponents(BoundingBoxComponent.class).getBounds().getX() - handler.getGameCamera().getxOffset()), (int)(y + getComponents(BoundingBoxComponent.class).getBounds().getY() - handler.getGameCamera().getyOffset()), (int)getComponents(BoundingBoxComponent.class).getBounds().getWidth(),(int) getComponents(BoundingBoxComponent.class).getBounds().getHeight());		
//		
	}
	
	public Texture getTexture() {
		return texture;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void increasePosition(Vector2f v){
		Vector2f.add(position, v, position);
	}
	
	public void setPosition(Vector2f v){
		this.position.set(v);
	}

	public float getX() {
		return position.getX();	
	}

	public void setX(float x) {
		this.position.setX(x);
	}

	public float getY() {
		return position.getY();
	}

	public void setY(float y) {
		this.position.setY(y);
	}
}
