package net.lars.game2.ui.selectionBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Text;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UIObject;


/**
 * 	
 * @Date 11. juni 2018
 * @Author LarsL123
 *
 * @Info:	
 * 		TextSelevtionBar width 
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class UITextSelectionBar extends UIObject{
	
	private Rectangle buttonBounds; 
	private String[] buttonText;
	private ClickListener[] clickListeners;
	private int buttonWidth;
	
	private int[] zeroPoints;
	private int[] zentralPoints;
	
	

	public UITextSelectionBar(Handler handler, Vector2f position, int width,
			int height, Rectangle buttonBounds, String[] buttonText, ClickListener[] clickListeners) {
		super(handler, position, width, height);
		
		this.buttonBounds = buttonBounds;
		this.buttonText = buttonText;
		this.clickListeners = clickListeners;

		//Calculating button positions
		int buttonCount = buttonText.length;
		buttonWidth = width / buttonCount;
		
		zeroPoints = new int[buttonCount];
		zentralPoints = new int[buttonCount];
		
		for(int i = 0; i<buttonText.length; i++){
			zeroPoints[i] = buttonWidth * i;
			zentralPoints[i] = zeroPoints[i] + buttonWidth/2;
			}
		}

	@Override
	public void render(Graphics g) {
		
		//Background
		g.setColor(Color.WHITE);
		g.fillRect((int)getXRelativeToParent(), (int)getYRelativeToParent(), width, height);
		//The buttons
		for(int i = 0; i < buttonText.length; i++){
			Text.drawString(g, buttonText[i], (int)position.getX() + zentralPoints[i], (int)position.getY() + height/2, true, Color.RED, Assets.font18);
		}
	}

	@Override
	public void onClick(Handler handler, MouseEvent e) {
		if(hovering){
			int x = e.getX() - bounds.x;
			for(int i = 0; i < buttonText.length; i++){
				if(!(x >= (i + 1) * buttonWidth)){
					clickListeners[i].onClick(handler);
					break;
				}
			}
		}
	}
	
}
