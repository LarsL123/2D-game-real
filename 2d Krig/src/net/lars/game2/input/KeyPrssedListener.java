package net.lars.game2.input;

import java.awt.event.KeyEvent;

/**
 * 	
 * @Info
 *		All classes that implements this interface can receive kyEvents every time a key is typed.
 *		To start receiving KeyEventsd say:
 *											 handler.getKeyMager.addKeyListener(the Class);
 *
 *		To stop receiving KeyEvents say:
 *						
 *											hanler.getKeyManger.removeKeyListener(the class);
 * @Date 20. jul. 2018
 * @Author LarsL123
 *
 *
 * @TODO
 * 
 * @Suggestions
 *
 */
public interface KeyPrssedListener {
	/**
	 * 
	 * @Info:
	 * 		Called every time a key is pressed.
	 *
	 * @param k
	 */
	public void keyTyped(KeyEvent k);
}
