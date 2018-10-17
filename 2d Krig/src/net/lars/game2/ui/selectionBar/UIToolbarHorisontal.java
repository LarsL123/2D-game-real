package net.lars.game2.ui.selectionBar;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.Handler;
import net.lars.game2.ui.UIObject;
import net.lars.game2.ui.UIUtils;
import net.lars.game2.ui.exceptions.TooBigUIObjectExepion;

public class UIToolbarHorisontal extends UIToolBar{

	public UIToolbarHorisontal(Handler handler, UIObject parent, Vector2f position, int width, int height) {
		super(handler, parent, position, width, height);
	}
	
	@Override
	public void addChild(UIObject c) {
		if(c.getHeight() > getHeight()) {
			System.err.println("The Compoent you tried to add are to high. The tollbar is: " + getHeight() + " high and the given UIObject is: " + c.getHeight() + " high." );
			throw new TooBigUIObjectExepion();
		}
		childUIObjects.add(c);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(UIUtils.BACKGROUD_COLOR);
		g.fillRect((int)getXRelativeToParent(), (int)getYRelativeToParent(), width, height);
		for(UIObject o : childUIObjects) {
			o.render(g);
		}
		
	}

	@Override
	public void onClick(Handler handler, MouseEvent e) {
		System.out.println("UITollbar clicked");
	}

}
