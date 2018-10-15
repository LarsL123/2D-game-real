package net.lars.game2.game;

import java.awt.Font;
import java.awt.FontMetrics;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import net.lars.game2.display.DisplayManager;
import net.lars.game2.engine.openGl.Loader;
import net.lars.game2.engine.openGl.MasterRenderer;
import net.lars.game2.engine.textures.TextureMaster;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.input.MouseManager;
import net.lars.game2.input.MyKeyboard;
import net.lars.game2.states.GameState;
import net.lars.game2.states.State;
import net.lars.game2.tiles.tileRendering.TileRenderer;

/**
 * 	
 * @Date 21. mai 2018
 * @Author LarsL123
 *
 * @Info
 * 		The main game file.
 *
 * @TODO
 * 
 * Read about OpenGL optimizations: https://www.khronos.org/opengl/wiki/GLSL_Optimizations 
 *  Calculate what image sh9uld be renderd in the front. dont know the best sultutiomn and if i need the depth buffer. Since i know the x, y width height i think i will be able to calculate it.
 *  
 *  
 *  Fra 2.30 snakkker han om en model cache som jeg vil prøve å implemente, bare med images.
 *  https://www.youtube.com/watch?v=UhksMuKwqrU&index=18&list=PLRIWtICgwaX1XtrjChFm9iCxLe1Hlg6s-
 *  Husk å se i informationComponenten for mere informasjon.
 *  
 *  
 *  Se en du kommer til å like den
 *  https://www.youtube.com/watch?v=kujcUdWAIwc
 *  https://www.youtube.com/watch?v=RDEUcNRZ8p0
 * 
 * @Suggestions
 *
 */
public class Game implements Runnable {
	
	private Loader loader = new Loader();
	private MasterRenderer renderer;
	
	private RenderData renderData;

	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;

	
	//states
	public State gameState;
	public State menuState;
	public State mapMakerState;
	public State testState;
	
	//input
	private MouseManager mouseManager;
	
	//camera
	private GameCamera camera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		mouseManager = new MouseManager();				
	}
	private void init(){
		
		Assets2.init();
		handler = new Handler(this);
		
		renderer = new MasterRenderer(handler, loader);
		renderData = new RenderData();
		camera = new GameCamera(this, handler, 0, 0);
		
		gameState = new GameState(handler);
		
//		menuState = new MenuState(handler);
//		mapMakerState = new MapMakerState(handler);
//		testState = new TestState(handler);
		
		State.setState(gameState);
	
	}
	
	private void tick(){
		MyKeyboard.getCurrentKeyboard().tick();
		
//		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_W)) {
//			camera.move(0.3f, 0.3f);
//		}else {
//			camera.move(-0.3f,- 0.3f);
//		}
			
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	private void render(){
		if(State.getState() != null){
			State.getState().render(renderData);
		}
		
		//Clear screen colour buffer
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		renderer.render(renderData, camera);
		

		
		
		//End drawing

	}

	@Override
	public void run() {
		DisplayManager.createDisplay();
		init();
		
		int fps = 120;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running && !Display.isCloseRequested()){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				
				tick();
				render();
				
				ticks++;
				delta--;
				if(timer >= 1000000000){
					System.out.println("Ticks and Frames : " + ticks);
					timer = 0;
					ticks = 0;
				}
			}
			/**
			 * Turns out you need to create and update the display in the same thread.
			 */
			DisplayManager.updateDisplay();
		}
		
		TextureMaster.cleanUp();
		renderer.cleanUP();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
		stop();
		
	}
	
	public FontMetrics getFontMetrics(Font f) {
		return null;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getCamera() {
		return camera;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
