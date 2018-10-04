//package net.lars.game2.game;
//
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.image.BufferStrategy;
//
//import net.lars.game2.display.Display;
//import net.lars.game2.graphics.Assets;
//import net.lars.game2.input.KeyManager;
//import net.lars.game2.input.MouseManager;
//import net.lars.game2.states.GameState;
//import net.lars.game2.states.MapMakerState;
//import net.lars.game2.states.MenuState;
//import net.lars.game2.states.State;
//import net.lars.game2.states.TestState;
//
///**
// * 	
// * @Date 21. mai 2018
// * @Author LarsL123
// *
// * @Info
// * 		The main game file.
// *
// * @TODO
// *  Fra 2.30 snakkker han om en model cache som jeg vil prøve å implemente, bare med images.
// *  https://www.youtube.com/watch?v=UhksMuKwqrU&index=18&list=PLRIWtICgwaX1XtrjChFm9iCxLe1Hlg6s-
// *  Husk å se i informationComponenten for mere informasjon.
// *  
// *  
// *  Se en du kommer til å like den
// *  https://www.youtube.com/watch?v=kujcUdWAIwc
// *  https://www.youtube.com/watch?v=RDEUcNRZ8p0
// * 
// * @Suggestions
// *
// */
//public class Game2 implements Runnable {
//	
//	private Display display;
//	private int width, height;
//	public String title;
//	
//	private boolean running = false;
//	private Thread thread;
//	
//	private BufferStrategy bs;
//	private Graphics g;
//	
//	
//	//states
//	public State gameState;
//	public State menuState;
//	public State mapMakerState;
//	public State testState;
//	
//	//input
//	private KeyManager keyManager;
//	private MouseManager mouseManager;
//	
//	//camera
//	private GameCamera camera;
//	
//	//Handeler
//	private Handler handler;
//	
//	public Game2(String title, int width, int height){
//		this.width = width;
//		this.height = height;
//		this.title = title;
//		keyManager = new KeyManager();
//		mouseManager = new MouseManager();				
//	}
//	private void init(){
//		display = new Display(title, width, height);
//		display.getFrame().addKeyListener(keyManager);
//		display.getFrame().addMouseListener(mouseManager);
//		display.getFrame().addMouseMotionListener(mouseManager);
//		display.getCanvas().addMouseListener(mouseManager);
//		display.getCanvas().addMouseMotionListener(mouseManager);
//		Assets.init();
//		
//		handler = new Handler(this);
//		
//		camera = new GameCamera(this, handler, 0, 0);
//		
//		gameState = new GameState(handler);
//		menuState = new MenuState(handler);
//		mapMakerState = new MapMakerState(handler);
//		testState = new TestState(handler);
//		
//		State.setState(menuState);
//	
//	}
//	
//	private void tick(){
//		keyManager.tick();
//		
//		if(State.getState() != null){
//			State.getState().tick();
//		}
//	}
//	
//	private void render(){
//		bs = display.getCanvas().getBufferStrategy();
//		if(bs == null){
//			display.getCanvas().createBufferStrategy(3);
//			return;
//		}
//		g = bs.getDrawGraphics();
//		//Clear Screen
//		g.clearRect(0, 0, width, height);
//		
//		//Draw Here
//		
//		if(State.getState() != null){
//			State.getState().render(g);
//		}
//		
//		
//		//End drawing
//		bs.show();
//		g.dispose();
//	}
//
//	@Override
//	public void run() {
//		init();
//		
//		int fps = 60;
//		double timePerTick = 1000000000 / fps;
//		double delta = 0;
//		long now;
//		long lastTime = System.nanoTime();
//		long timer = 0;
//		int ticks = 0;
//		
//		while(running){
//			now = System.nanoTime();
//			delta += (now - lastTime) / timePerTick;
//			timer += now - lastTime;
//			lastTime = now;
//			
//			if(delta >= 1){
//				
//				tick();
//				render();
//				
//				ticks++;
//				delta--;
//				if(timer >= 1000000000){
//					System.out.println("Ticks and Frames : " + ticks);
//					timer = 0;
//					ticks = 0;
//				}
//			}
//		}
//		
//		stop();
//		
//	}
//	
//	public FontMetrics getFontMetrics(Font f) {
//		return g.getFontMetrics(f);
//	}
//	
//	public KeyManager getKeyManager(){
//		return keyManager;	
//	}
//	
//	public MouseManager getMouseManager(){
//		return mouseManager;
//	}
//	
//	public GameCamera getCamera() {
//		return camera;
//	}
//	
//	public int getHeight(){
//		return height;
//	}
//	
//	public int getWidth(){
//		return width;
//	}
//	
//	public synchronized void start(){
//		if(running){
//			return;
//		}
//		running = true;
//		thread = new Thread(this);
//		thread.start();
//	}
//	
//	public synchronized void stop(){
//		if(!running){
//			return;
//		}
//		running = false;
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}
