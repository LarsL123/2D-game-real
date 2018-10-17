package net.lars.game2.ui.tinyButtons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.Handler;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UIObject;

public class UIImageButton extends UIbutton{
	
	private BufferedImage texture;

	public UIImageButton(Handler handler,UIObject parent,Vector2f position, int width, int height, BufferedImage texture, ClickListener clicker) {
		super(handler,parent, position, width, height, clicker);
		this.texture = texture;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int) getXRelativeToParent(), (int)getYRelativeToParent(), width, height, null);
		if(hoveringTimer > 100) {
			renderInfoGui(g);
		}
	}
}
