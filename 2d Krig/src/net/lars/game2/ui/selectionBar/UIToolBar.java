package net.lars.game2.ui.selectionBar;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.ui.UIObject;

public abstract class UIToolBar extends UIObject{
	
	

	public UIToolBar(Handler handler, UIObject parent, Vector2f position, int width, int height) {
		super(handler, parent, position, width, height);
		
	}

}
