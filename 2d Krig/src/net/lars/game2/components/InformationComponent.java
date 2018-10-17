package net.lars.game2.components;

import java.awt.image.BufferedImage;

import net.lars.game2.component.Component;
import net.lars.game2.entity.Entity;
import net.lars.game2.main.Handler;


/**
 * 	
 * @Date 29. mai 2018
 * @Author LarsL123
 *
 * @Info:
 *
 * @TODO:
 * 		Make this component hold a int that coresponds to a texture in a model cache in assets. 
 *		Fra 2.30 snakkker han om en model cache som jeg vil prøve å implemente, bare med images.
 *
 *		https://www.youtube.com/watch?v=UhksMuKwqrU&index=18&list=PLRIWtICgwaX1XtrjChFm9iCxLe1Hlg6s-
 * 
 * @Suggestions:
 *
 */
public class InformationComponent extends Component{
	public Entity parentEntity;
	public boolean active = true;
	
	public InformationComponent(Handler handler, Entity parentEntiy){
		super(handler);
		this.parentEntity = parentEntiy;
	}

	@Override
	public void tick() {
		
	}
}
