package net.lars.game2.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class MyKeyboard{
	
	private static final MyKeyboard ACTIVE_KEYBOARD = new MyKeyboard();
	
	private List<Integer> keysUpThisFrame = new ArrayList<Integer>();
	private List<Integer> keysDownThisFrame = new ArrayList<Integer>();
	private List<Integer> charsThisFrame = new ArrayList<Integer>();
	
	private List<Integer> keysCurrentlyDown = new ArrayList<Integer>();
	
	private boolean blocked = false;
	

	
	
	/**
	 * final active keyboard
	 * keys down and keys up this frame list.
	 * chars this frame list
	 */
	
	
	public MyKeyboard() {
		
	}
	
	public static MyKeyboard getCurrentKeyboard() {
		return ACTIVE_KEYBOARD;	
	}
	

	public void tick() {
		keysDownThisFrame.clear();
		keysUpThisFrame.clear();
		charsThisFrame.clear();
		
		//If the display is not in the foreground of the operating system. Then clear all keys. This will stop keys form being held down when the display is not used.
		if (!Display.isActive()) {
			keysCurrentlyDown.clear();
			return;
		}
		
		//Wile there are keys left.
		while (Keyboard.next()) {
			//get the key in the current iteration.
			int key = Keyboard.getEventKey();
			//Tryue If the key is pressed down. False if it was relesed.
			if (Keyboard.getEventKeyState()) {
				keysDownThisFrame.add(key);
				int ascii = getValidAsciiValue(Keyboard.getEventCharacter());
				if (ascii != 0) {
					charsThisFrame.add(ascii);
				}
				keysCurrentlyDown.add(key);
			} else {
				keysUpThisFrame.add(key);
				keysCurrentlyDown.remove((Integer) key);
			}
		}

	}
	/**
	 * 
	 * @Info
	 * 		checks if the specified key is  held down.
	 *
	 * @param key
	 * @return
	 * 		True if if the key is held down and the keyboard is not blocked.
	 */
	public boolean isKeyDown(int key) {
		if(keysCurrentlyDown.contains(key)) {
			return !blocked;
		}
		return false;
	}
	
	/**
	 * 
	 * @Info
	 * 		-Checks if the key is pressed down this frame
	 *
	 * @param key
	 * @return
	 * 		True if the key is pressed down THIS frame and KeyBoard is not blocked.
	 */
	public boolean isKeyDownThisFrame(int key) {
		if(keysDownThisFrame.contains(key)) {
			return !blocked;
		}
		return false;
	}
	
	/**
	 * 
	 * @Info
	 * 		-Checks if the key is released this frame.
	 *
	 * @param key
	 * @return
	 * 		True if the key is released this frame and the KeyBord is not blocked.
	 */
	public boolean isKeyUpThisFrame(int key) {
		if(keysUpThisFrame.contains(key)) {
			return !blocked;
		}
		return false;
	}
	
	/**
	 * 
	 * @Info
	 *
	 * @return
	 * 		Returns a list of the Chairs pressed this frame. 
	 */
	public List<Integer> getChairsThisFrame() {
		return charsThisFrame;
	}
	
	
	
	/**
	 * 
	 * @Info
	 * 		Checks if the ASCII is a: number(0-9), letter(A-Z or a-z) or a Special type(Not implemented).
	 *
	 * @param eventCharacter
	 * @return
	 * 		The ASCII of the eventCaracter if it meets the requirements.
	 * 		Or 0 if it does not meet the requirements.
	 */
	private int getValidAsciiValue(char eventCharacter) {
		int ascii = (int) eventCharacter;
		if(isNumber(ascii) || isLetter(ascii) || isSpecialType(ascii)) {
			return ascii;
		}
		return 0;
	}
	
	//Not implemented yet, see MYKeyborad in equillinox
	//Probably going to use it to identify spaces, enter presses on stuff like that.
	private boolean isSpecialType(int ascii) {
		return false;
	}
	
	/**
	 * 
	 * @Info	
	 * 		-Checks if the given ASCII is a latter(Between A-Z or a-z)
	 *
	 * @param ascii
	 * @return
	 * 		True if the ASCII is a letter
	 */
	private boolean isLetter(int ascii) {
		return (ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122);
	}
	/**
	 * 
	 * @Info
	 * 		Check if the ASCII is a number.
	 *
	 * @param ascii
	 * @return True if the ASCII given is a number(between 0-9);
	 */
	private boolean isNumber(int ascii) {
		return (ascii >= 48 && ascii<=57);
	}
	
	/**
	 * 
	 * @Info
	 * 		Block / unblock the KeyBoard
	 *
	 * @param blocked
	 */
	public void block(boolean blocked) {
		this.blocked = blocked;
	}
	
}
