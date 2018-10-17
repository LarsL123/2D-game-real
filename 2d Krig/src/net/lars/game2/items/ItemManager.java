package net.lars.game2.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import net.lars.game2.main.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> itemArrayList;

	public ItemManager(Handler handler){
		this.handler = handler;
		itemArrayList = new ArrayList<Item>();
	}
	
	public void tick(){
		Iterator<Item> it = itemArrayList.iterator();
		while(it.hasNext()){
			Item i = it.next();
			i.tick();
			if(i.isPickedUp()){
				it.remove();
			}
		}
	}
	
	public void render(Graphics g){
		for(Item i : itemArrayList){
			i.render(g);
		}
	}
	
	public void addItem(Item i){
		i.setHandeler(handler);
		itemArrayList.add(i);
	}
}
