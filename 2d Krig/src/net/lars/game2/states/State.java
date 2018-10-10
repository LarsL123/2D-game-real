package net.lars.game2.states;

import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;

/**
 * 	
 * @Info:
 * 		A state is a class that holds information about a specific state of the game.
 *
 * @Date 26. juni 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public abstract class State {
	
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
		state.onStateSet();
	}
	
	public static State getState(){
		return currentState;
	}
	
	//Abstracts
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	
	/**
	 * 
	 * @Info
	 * 		-This method is sent to all classes that want to be rendered.
	 * 		-To render a object, use {@link RenderData}} 's addEntity() method and the object will be rendered.
	 *
	 * @param data
	 */
	public abstract void render(RenderData data);
	public abstract void onStateSet();
	
	
	
}
