package net.lars.game2.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import org.lwjgl.input.Keyboard;

import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets;
import net.lars.game2.input.MyKeyboard;
import net.lars.game2.items.Item;

/**
 * 	
 * @Info
 * IDK yet.
 *
 * @Date 10. aug. 2018
 * @Author LarsL123
 *
 *
 * @TODO
 * 
 * @Suggestions
 *
 */
public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invX , invY, invWidth, invHeight;
	
	private int rowLength = 5;
	private int rows = 3;
	private int itemXStart;
	private int itemYStart;
	private int itemXSpace;
	private int itemYSpace;
	private int itemsInOneRow;
	private int maxRows;

	public Inventory(Handler handler){
		this.handler = handler;
		invX = handler.getGame().getWidth() / 2 - Assets.inventorey.getWidth() / 4;
		invY = handler.getGame().getHeight() / 2 - Assets.inventorey.getHeight() / 4;
		invWidth = Assets.inventorey.getWidth() / 2;
		invHeight =  Assets.inventorey.getHeight() / 2;
		itemXStart = invX + 305;
		itemYStart = invY + 30;
		itemXSpace = 16;
		itemYSpace = 32;
		itemsInOneRow = 9;
		maxRows = 6;
		inventoryItems = new ArrayList<Item>();
		
		//TODO Fjern den legger til noen wood i inventoryen
		addItem(Item.woodItem.createNew(200));	
	}
	
	//Cheks if the inventory shuld be active, and;
	//removes Items width a count of 0 or below;
	public void tick(){
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_E))
			active = !active;
		
		for(int i = 0;i < inventoryItems.size(); i++ ){
			Item item = inventoryItems.get(i);
			if(item.getCount() <= 0){
				inventoryItems.remove(i);
			}
		}
	}
	
	public void render(Graphics g){
		if(!active){
			return;
		}
		
		g.drawImage(Assets.inventorey, invX, invY, invWidth, invHeight,null);
		
		for(int i = 0; i < inventoryItems.size(); i++){
			Item item = inventoryItems.get(i);
			int cR = i / itemsInOneRow;
			item.render(g, itemXStart + ((i % itemsInOneRow) * (item.getTexture().getWidth() + itemXSpace)), itemYStart + (cR * (item.getTexture().getHeight() + itemYSpace)));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(item.getCount()), (itemXStart + ((i % itemsInOneRow) * (item.getTexture().getWidth() + itemXSpace))) + 10, itemYStart + (cR * (item.getTexture().getHeight() + itemYSpace)));
		}
	}
	
	//***Inventory methods****//
	
	
	/**
	 * 
	 * @param item 
	 * 				-the item that is added;
	 * 
	 */
	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				if(i.getCount() + item.getCount() <= i.getMaxSize()){
					i.setCount(i.getCount() + item.getCount());
					return;
				}
				if(i.getMaxSize() - i.getCount() > 0){
					int spacesLeft = i.getMaxSize() - i.getCount();
					i.setCount(i.getCount() + spacesLeft);
					item.setCount(item.getCount() - spacesLeft);
				}
			}
		}
		//if the inserted item has a bigger count than the max stack, 
		//then this part is splitting it up in stacks;
		while(item.getCount() >= item.getMaxSize()){
			inventoryItems.add(item.createNew(20));
			item.setCount(item.getCount() - 20);
		}
		
		//Add the remaining items
		inventoryItems.add(item);
		return;
	}
	
	/**
	 * 
	 * @param item
	 * 			the item type that should be removed;
	 * @param amount
	 * 			the amount of the itemtype that should be removed;
	 */
	public void removeItem(Item item, int amount){
		int remaider = amount;
		for(Item i: inventoryItems){
			if(item.getId() != i.getId()){
				continue;
			}else{
				
				remaider = i.adjustAmount(remaider);
				System.out.println(remaider);
			}
			
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Handler getHandler() {
		return handler;
	}
}
