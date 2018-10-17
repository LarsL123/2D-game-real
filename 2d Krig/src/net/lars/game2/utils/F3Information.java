package net.lars.game2.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Text;
import net.lars.game2.input.MyKeyboard;
import net.lars.game2.main.Handler;

/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info:
 * 		Debugging tool. Displays information when pressing F3, like in Minecraft :-)
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class F3Information {
	
	private Handler handler;
	private boolean active;
	
	public F3Information(Handler handler){
		this.handler = handler;
		active = false;
	}
	
	public void tick(){
		if(MyKeyboard.getCurrentKeyboard().isKeyDownThisFrame(Keyboard.KEY_F3)){
			active = !active;
		}
		if(!active){
			return;
		}
	}
	
	public void display(){
		if(!active)
			return;
		System.out.println("Pos: " + handler.getGameCamera().getxOffset() + ", " + handler.getGameCamera().getyOffset());
	}
}
