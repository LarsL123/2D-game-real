package net.lars.game2.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.entity.Entity;
import net.lars.game2.input.MyKeyboard;

/**
 * 	
 * @Info:
 * 		The camera in the world. Represented by a x and y offset to the worlds center
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
public class GameCamera {
	
	/**
	 * The offset used when rendering the world.
	 */
	private float xOffset, yOffset;
	
	private Game game;
	private Handler handler;
	private float movementspeed = 4f;
	
	public GameCamera(Game game, Handler handler, float xoffset, float yoffset){
		this.game = game;
		xOffset = xoffset; 
		yOffset = yoffset; 
		this.handler = handler;
	}
	
	/**
	 * 
	 * @Info:
	 * 		Manually move the camera.
	 *
	 * @param xAmt
	 * @param yAmt
	 */
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
	}
	
	/**
	 * 
	 * @Info:
	 * 		set the camera directly over the given entity.
	 *
	 * @param e
	 */
	public void centerOnEntity(Entity e){
		xOffset = (float) (e.getX() - game.getWidth() / 2 + e.getWidth() / 2);
		yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;
	}
	
	/**
	 * 
	 * @Info:
	 *      Used when there is not a character used to move the game camera.
	 *
	 */
	public void moveWidthInputs(){
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_W)){
			yOffset -= movementspeed;
		}
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_S)){
			yOffset += movementspeed;
		}
		
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_D)){
			xOffset += movementspeed;
		}
		
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_A)){
			xOffset -= movementspeed;
		}
	}
	
	

	public float getxOffset() {
		return xOffset;
	}
	
	public Vector2f getAsVector (){
		return new Vector2f(xOffset, yOffset);
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
