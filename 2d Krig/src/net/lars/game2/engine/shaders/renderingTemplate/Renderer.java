package net.lars.game2.engine.shaders.renderingTemplate;

import net.lars.game2.engine.models.RawModel;
import net.lars.game2.engine.openGl.Loader;
import net.lars.game2.main.Handler;




public abstract class Renderer {
	
	//Making it protected since this is a 2d game.
	protected static final float[] POSITIONS = { 0, 0 ,0, 1, 1, 0, 1, 1 };
	protected RawModel model;
	protected Loader loader;
	
	/**
	 * Null if you are usenig the second coinstructer.
	 */
	protected Handler handler;
	
	public Renderer(Handler handler, Loader loader) {
		this.handler = handler;
		this.loader = loader;
	}
	
	public Renderer(Loader loader) {
		this.loader = loader;
	}
	
	public abstract void cleanUP();
}

