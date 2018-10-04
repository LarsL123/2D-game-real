package net.lars.game2.ui.tinyButtons;

import java.awt.Color;
import java.awt.Graphics;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Text;
import net.lars.game2.ui.ClickListener;
import net.lars.game2.ui.UIObject;

public class UITextButton extends UIbutton{

	private String text;
	public UITextButton(Handler handler, UIObject parent, Vector2f position,
			int width, int height, ClickListener clicker, String text) {
		super(handler, parent, position, width, height, clicker);
		this.text = text;
	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, text, (int)getXRelativeToParent() + width/2, (int)getYRelativeToParent() + height/2, true, Color.WHITE, Assets.font18);
	}

}
