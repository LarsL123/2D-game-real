package net.lars.game2.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import net.lars.game2.main.Config;


public class DisplayManager {

	
	
	public static void createDisplay() {
		
		ContextAttribs attribs = 
		new ContextAttribs(3,3)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(Config.WIDTH , Config.HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("First 2D game");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, Config.WIDTH, Config.HEIGHT);
		
		
	}
	
	public static void updateDisplay() {
		Display.sync(Config.FPS_CAP);
		Display.update();
		
	}
	
	public static void closeDisplay() {	
		Display.destroy();
	}
	
}
