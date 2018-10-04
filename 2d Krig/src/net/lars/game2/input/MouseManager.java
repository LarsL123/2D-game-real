package net.lars.game2.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.ui.UIManager;

/**
 * 	
 * @Info:
 * 		Manages the mouse input.
 * 		You can get the current mouse position and the current UIManager will be notified when mouse clicked.
 *
 * @Date 4. juli 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed, rightPressed;
	
	/**
	 * Stores the current mouse position.
	 * Updated every time the mouse is moved.
	 * 
	 * @see #getCurrentMousePosition()
	 */
	private Vector2f currentMousePosition = new Vector2f(0,0);
	
	private UIManager UIManager;
	
	public MouseManager(){
		
	}
	
	public void setUIManager(UIManager uiManager){
		this.UIManager = uiManager;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.currentMousePosition.setX(e.getX());
		this.currentMousePosition.setY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = false;
		}
		if(UIManager != null){
			UIManager.onMouseRelese(e);
		}
	}
	
	//Getters
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public Vector2f getCurrentMousePosition() {
		return currentMousePosition;
	}

	public void setCurrentMousePosition(Vector2f currentMousePosition) {
		this.currentMousePosition = currentMousePosition;
	}
}
