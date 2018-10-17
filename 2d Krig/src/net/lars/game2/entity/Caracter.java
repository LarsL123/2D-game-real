package net.lars.game2.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.components.BoundingBoxComponent;
import net.lars.game2.components.MovementComponent;
import net.lars.game2.graphics.Animation;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.input.MyKeyboard;
import net.lars.game2.inventory.Inventory;
import net.lars.game2.items.Item;
import net.lars.game2.main.Handler;
import net.lars.game2.main.RenderData;
import net.lars.game2.utils.F3Information;

public class Caracter extends Entity {

	//Animation
	private Animation animDown, animUp, animLeft, animRight;
	public BufferedImage dirImage;
	
	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	//Inventory
	private Inventory inventory;
	
	private F3Information f3Information;

	public  Caracter(Handler handler,Vector2f position, float width, float height){
		super(handler, Assets2.caracterHoleImage, position ,width, height);
		
		
		BoundingBoxComponent bounds = new BoundingBoxComponent(handler, this,6, 8, 20, 24);
		MovementComponent moveCompoenet = new MovementComponent(handler, this, 5f);
		
		addComponent(bounds);
		addComponent(moveCompoenet);

		
		//Animations
		animDown = new Animation(300, Assets.player_down);
		animUp = new Animation(300, Assets.player_up);
		animLeft = new Animation(300, Assets.player_left);
		animRight = new Animation(300, Assets.player_right);		
		dirImage = animDown.getStandingStillImage();
		
		//Inventory
		inventory  = new Inventory(handler);
		f3Information = new F3Information(handler);
	}
	
	public void tick(){
		//Animation
		//TODO bare update aniamsjo9nen som er aktiv
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		//Movement
		if(handler.getWorld().isShouldReadInput()) {
			getInput();
			getComponents(MovementComponent.class).move();
		}
		

		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttacs();
		
		//Inventory
		inventory.tick();
		f3Information.tick();
		
		//TODO Fjern denne//
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_V)){
			inventory.removeItem(Item.woodItem, 10);
		}
		
		
		
	}
	
	@Override
	public void render(RenderData d){
//		g.drawImage(getCurrentAnimationFrame(),(int)(position.getX() - handler.getGameCamera().getxOffset()), (int)(position.getY() - handler.getGameCamera().getyOffset()) 
//				, (int) getWidth(), (int)getHeight(), null);	
//		
////		show hitbox
//		g.setColor(Color.red);
//		g.fillRect((int)(position.getX() + getComponents(BoundingBoxComponent.class).getBounds().getX() - handler.getGameCamera().getxOffset()), (int)(position.getY() + getComponents(BoundingBoxComponent.class).getBounds().getY() - handler.getGameCamera().getyOffset()), (int)getComponents(BoundingBoxComponent.class).getBounds().getWidth(),(int) getComponents(BoundingBoxComponent.class).getBounds().getHeight());		
	}
	
	public void postRender(Graphics g){
		//Inventory
		f3Information.render(g);
		inventory.render(g);
	}
	
	private void checkAttacs(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown){
			return;
		}
		
		if(handler.getMouseManager().isLeftPressed()){
			int arSize = 20;
			Rectangle cb = getComponents(BoundingBoxComponent.class).getCollisionBounds(0, 0);
			Rectangle ar = new Rectangle(0,0,arSize,arSize);
				
			if(dirImage == animUp.getStandingStillImage()){
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			}else if(dirImage == animDown.getStandingStillImage()){
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			}else if(dirImage == animLeft.getStandingStillImage()){
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}else if(dirImage == animRight.getStandingStillImage()){
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}else{
				return;
			}
			
			attackTimer = 0;
			
			for(Entity e : handler.getWorld().getEntityManager().getEntityArray()){
				if(e.equals(this)){
					continue;
				}
				if(e.getComponents(BoundingBoxComponent.class).getCollisionBounds(0, 0).intersects(ar)){
					//e.hurtEntity(1);
				}
			}
		}
	}
	
	public void getInput(){
		float movementspeed = getComponents(MovementComponent.class).movementSpeed;
		
		getComponents(MovementComponent.class).moveX = 0;
		getComponents(MovementComponent.class).moveY = 0;
		
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_W)){
			getComponents(MovementComponent.class).moveY = -movementspeed;
		}
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_S)){
			getComponents(MovementComponent.class).moveY = movementspeed;
		}
		
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_D)){
			getComponents(MovementComponent.class).moveX = movementspeed;
		}
		
		if(MyKeyboard.getCurrentKeyboard().isKeyDown(Keyboard.KEY_A)){
			getComponents(MovementComponent.class).moveX = -movementspeed;
		}
	}

	private BufferedImage getCurrentAnimationFrame(){
		float moveX = getComponents(MovementComponent.class).moveX;
		float moveY = getComponents(MovementComponent.class).moveY;
		if(moveX == 0 && moveY == 0){
			return dirImage;
		}
		if(moveX < 0){
			dirImage = animLeft.getStandingStillImage();
			return animLeft.getCurrentFrame();
		}else if(moveX > 0){
			dirImage = animRight.getStandingStillImage();
			return animRight.getCurrentFrame();
		}else if(moveY > 0){
			dirImage = animDown.getStandingStillImage();
			return animDown.getCurrentFrame();
		}else if(moveY < 0){
			dirImage = animUp.getStandingStillImage();
			return animUp.getCurrentFrame();
		}else{
			//hvis noe er feil
			return animDown.getStandingStillImage();
		}
		
		
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}	
}
