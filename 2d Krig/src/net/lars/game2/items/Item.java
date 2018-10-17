package net.lars.game2.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.lars.game2.components.BoundingBoxComponent;
import net.lars.game2.graphics.Assets;
import net.lars.game2.main.Handler;

public class Item {
	
	// Handler
	
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.item1, "Wood", 0, 20); 
	
	// Class
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int maxSize;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id, int maxSize){
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.maxSize = maxSize;
		count = 1;
		
		bounds = new Rectangle(x,y,ITEMWIDTH,ITEMHEIGHT);
		
		items[id] = this;
	}
	
	public void tick(){
		if(handler.getWorld().getEntityManager().getCaracter().getComponents(BoundingBoxComponent.class).getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getCaracter().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g){
		if(handler == null)
			return;
		render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, ITEMHEIGHT, ITEMHEIGHT, null);
	}
	
	public Item createNew(int x, int y){
		Item i = new Item(texture, name, id, maxSize);
		i.setPosison(x, y);
		return i;
	}
	
	public Item createNew(int count){
		Item i = new Item(texture, name, id, maxSize);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	
	
	public void setPosison(int x, int y){
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	//Getters and setters
	
	//Removes @param count form this classes count.
	// returns the remainder of the subtraction;
	public int adjustAmount(int count){
		if(this.count >= count){
			this.count -= count;
			return 0;
		}else{
			count -= this.count;
			this.count = 0;
			return count;
		}
		
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public int getCount() {
		return count;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
	
	public void setHandeler(Handler handler){
		this.handler = handler;
	}
}
