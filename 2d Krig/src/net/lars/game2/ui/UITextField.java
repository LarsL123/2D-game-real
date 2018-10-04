package net.lars.game2.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Text;
import net.lars.game2.input.KeyPrssedListener;

/**
 * 	
 * @Info
 * 		A text filed usable were you need it.
 * 		Just add it to the UIManager, then it should work.
 *
 * @Date 20. jul. 2018
 * @Author LarsL123
 *
 *
 * @TODO
 * 		
 * 
 * @Suggestions
 * 		Being able to choose font?
 *
 */
public class UITextField extends UIObject implements KeyPrssedListener{ 
	
	private Vector2f textOffset = new Vector2f(0,25);
	private String stringOnDisplay = "";
	
	/**
	 * the Color you want your text in
	 */
	private Color textColor = Color.WHITE;
	
	/**
	 * The max amount of characters you want.
	 * If the stringOnDisplay argument has a bigger width then this.width, the you will not be able to write more characters either.
	 */
	private int maxCaracters = 40;

	
	/**
	 *  Create a new Text field.
	 * @param handler
	 * @param parent
	 * 		The UIObject responsible for ticking, rendering etc.
	 * @param position
	 * 		The position on the screen.
	 * @param width
	 * @param height
	 */
	public UITextField(Handler handler, UIObject parent, Vector2f position, int width, int height) {
		super(handler, parent, position, width, height);
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(UIUtils.LIGHTER_BACKGROUD_COLOR);
		g.fillRect((int)getXRelativeToParent(), (int)getYRelativeToParent(), width, height);
		g.drawImage(Assets.editTextIcon, (int)getXRelativeToParent() + width - height, (int)getYRelativeToParent(), height, height, null); 
		Text.drawString(g, stringOnDisplay,(int) (getXRelativeToParent()  + textOffset.getX()), (int)(getYRelativeToParent()   + textOffset.getY()), false, textColor, Assets.openSansRegular28);
	}

	@Override
	public void onClick(Handler handler, MouseEvent e) {
		handler.getWorld().setShouldReadInput(false);
		handler.getKeyManager().setKeyListener(this);
	}
	
	
	@Override
	public void keyTyped(KeyEvent k) {
			int keyCode = k.getKeyCode();
			
			if(keyCode >= 65 && keyCode <= 90) {
				stringOnDisplay += k.getKeyChar();
				
				//vet ikke om jeg skal ha me denne:
				int stringWidth = handler.getGame().getFontMetrics(Assets.openSansRegular28).stringWidth(stringOnDisplay);
				
				if(stringWidth > getWidth() - height || stringOnDisplay.length() > maxCaracters) {
					stringOnDisplay = stringOnDisplay.substring(0, stringOnDisplay.length()-1);
				}
				return;
			}
			
			switch(keyCode){
				// if a space id pressed.
				case KeyEvent.VK_SPACE:
					stringOnDisplay += k.getKeyChar();
					return;
				//If user don't want to write any more:
				case KeyEvent.VK_ESCAPE:
				case  KeyEvent.VK_ENTER:
					handler.getKeyManager().removeKeyListener(this);
					handler.getWorld().setShouldReadInput(true);
					
					return;
				//If user want to remove a character.	
				case 8:
					if(stringOnDisplay.length() > 0) {
						stringOnDisplay = stringOnDisplay.substring(0, stringOnDisplay.length()-1);
					}
					return;
			}
	}
	
	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public int getMaxCaracters() {
		return maxCaracters;
	}

	public String getText() {
		return stringOnDisplay;
	}

	public void setText(String stringOnDisplay) {
		this.stringOnDisplay = stringOnDisplay;
	}

	public void setMaxCaracters(int maxCaracters) {
		this.maxCaracters = maxCaracters;
	}
}
