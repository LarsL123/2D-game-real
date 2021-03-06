package net.lars.game2.graphics;

import java.awt.image.BufferedImage;

/**
 * 	
 * @Info:
 * 		Simple class for holding an animation.
 *
 * @Date 21. juni 2018.
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class Animation {
	
	private long lastTime, timer;
	private int speed, index;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames){
			this.speed = speed;
			this.frames = frames;
			index = 0;
			timer = 0;
			lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length){
				index = 1;
			}
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	public BufferedImage getStandingStillImage(){
		return frames[0];
	}
}
