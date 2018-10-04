package net.lars.game2.ui.tinyButtons;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UIObject;

public abstract class UIbutton extends UIObject{

	private ClickListener clicker;
	protected int hoveringTimer = 0;
	
	
	/**
	 * -create a button shaped as a rectangle.
	 * @param handler
	 * 		-the game handler
	 * @param parent
	 * 		-The parent UIObject
	 * @param position
	 * 		The x,y position (on its parent) of the button.
	 * @param width
	 * 		-The button width
	 * @param height
	 * 		-The button height
	 * @param clicker
	 * 		-A clicker method. Execute when onClick is called.
	 */
	public UIbutton(Handler handler, UIObject parent, Vector2f position,
			int width, int height, ClickListener clicker) {
		super(handler, parent, position, width, height);
		this.clicker = clicker;
	}
	/**
	 * -creates a square button.
	 * @param handler
	 * 		-the game handler
	 * @param parent
	 * 		-The parent UIObject
	 * @param position
	 * 		-The x,y position (on its parent) of the button. Given as a vector
	 * @param size
	 * 		-the height and length of the button
	 * @param clicker
	 * 		-A clicker method. Execute when onClick is called.
	 */
	public UIbutton(Handler handler, UIObject parent, Vector2f position,
			int size, ClickListener clicker) {
		super(handler, parent, position, size, size);
		this.clicker = clicker;
	}

	@Override
	public void onClick(Handler handler, MouseEvent e) {
		clicker.onClick(handler);
		
	}
	
	@Override
	public void tick() {
		if(hovering) {
			hoveringTimer++;
		}else {
			hoveringTimer = 0;
		}
	}
	
	protected void renderInfoGui(Graphics g) {
		g.fillRect((int)handler.getMouseManager().getCurrentMousePosition().getX(), (int)handler.getMouseManager().getCurrentMousePosition().getX(), 150, 50);
	}
}
