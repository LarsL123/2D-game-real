package net.lars.game2.component;

import net.lars.game2.main.Handler;

/**
 * 	
 * @date 13. mai 2018
 * @author LarsL123
 *
 * @Info:
 * The superclass of all Components
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public abstract class Component implements ComponentInterface {
	protected Handler handler;

	public Component(Handler handler){
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	
}
