package net.lars.game2.game;


/**
 * 	
 * @Info:
 * 		Were i lunch the game and determine the screen size.
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
public class Launcher {
	
	public static void main(String[] args){
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Game game = new Game("Tile game !" , (int)screenSize.getWidth(), (int)screenSize.getHeight());		
		
//		OpenGlTester t = new OpenGlTester();
//		t.testOpenGl();
		Game game = new Game("Tile game !" , 1000, 700);
		game.start();
	}
}
