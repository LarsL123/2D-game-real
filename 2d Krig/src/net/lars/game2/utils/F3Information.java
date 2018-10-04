package net.lars.game2.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Text;

/**
 * 	
 * @Date 19. juni 2018
 * @Author LarsL123
 *
 * @Info:
 * 		Held by the character, lets you see a lot of technical information. Used mostly for debugging.
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
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F3)){
			active = !active;
		}
		if(!active){
			return;
		}
	}
	
	public void render(Graphics g){
		if(!active)
			return;
		Text.drawString(g, "Posison: x "+ (int) handler.getWorld().getEntityManager().getCaracter().getX() + " y " + +  (int) handler.getWorld().getEntityManager().getCaracter().getY(), 0, 20, false, Color.WHITE, Assets.font28);
		
	}
}
