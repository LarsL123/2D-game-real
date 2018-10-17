package net.lars.game2.components;

import net.lars.game2.component.Component;
import net.lars.game2.main.Handler;

//NOT IN USE!!!!!!

/**
 * 	
 * @Date 18. mai 2018
 * @Author LarsL123
 *
 * @Info:
 * 		A component to hold a simple position.
 *
 * @TODO:
 * 
 * @Suggestions:
 *
 */
public class PositionComponentNotInUse extends Component{
	private float x, y;
	
	public PositionComponentNotInUse(Handler handler, float x, float y){
		super(handler);
		this.x = x;
		this.y = y;
	}
	
	public void increasePosition(float x, float y){
		this.x += x;
		this.y += y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void tick() {
	}

	
	
}
