package net.lars.game2.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.errorHandeling.ToLongArrayExeption;
import net.lars.game2.graphics.Assets;
import net.lars.game2.main.Handler;

/**
 * 	
 * @Info:
 * 		Displays a list of elements on a 2d space.
 * 		ItemsPerRow and ItemsPerColumn can be chosen.
 *
 * @Date 26. juni 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 		Add a way to return the element index that is clicked.
 * 
 * @Suggestions:
 *
 */
public class UI2DItemSelection extends UIObject{
	
	private ArrayList<BufferedImage> items;
	private int itemsPerRow;
	private int itemsPerColumn;
	
	private static int imageWidth = 64; 
	
	
	public UI2DItemSelection(Handler handler,UIObject parent, Vector2f position, ArrayList<BufferedImage> items, int itemsPerRow, int itemsPerColumn) {
		super(handler, parent, position, itemsPerRow * imageWidth, itemsPerColumn * imageWidth);
		
		//Checks if array has to many elements.
		if(items.size() > itemsPerColumn * itemsPerRow){
			System.out.println("Your Array has " + items.size() + " elements. Max number of arguments is: itemsPerColumn * itemnsPerRow = " + itemsPerColumn * itemsPerRow);
			throw new ToLongArrayExeption();
		}
		
		this.items = items;
		this.itemsPerColumn = itemsPerColumn;
		this.itemsPerRow = itemsPerRow;
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.SelectionUI2D,(int) (getXRelativeToParent() - 10),(int)(getYRelativeToParent()- 10),null);
		//The incrementor
		int i = 0;
		for(int buttonY = 0; buttonY < itemsPerColumn; buttonY++ ){
			for(int buttonX = 0; buttonX < itemsPerRow; buttonX++){
					 
					//May throw an error if one of the element in the array is null;
					g.drawImage(items.get(i), (int)getXRelativeToParent() + buttonX * imageWidth, (int)getYRelativeToParent() + buttonY * imageWidth, imageWidth, imageWidth, null);
				i++;
			}
		}
		g.setColor(Color.red);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public void onClick(Handler handler, MouseEvent e) {
		float xx = e.getX() - bounds.x;
		float yy = e.getY() - bounds.y;
		
		xx /= imageWidth;
		yy /= imageWidth;
		
		xx++;
		yy++;
		System.out.println((int)xx);
		System.out.println((int)yy);
		
		
		
		 //Send the  index of the List that was clicked
	}

}
