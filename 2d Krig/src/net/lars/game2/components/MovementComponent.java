package net.lars.game2.components;

import java.awt.Rectangle;

import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.component.Component;
import net.lars.game2.entity.Entity;
import net.lars.game2.game.Handler;
import net.lars.game2.tiles.Tile;


/**
 * 
 * @author lars
 * 		May be broken....
 * 		Find/replaced a lot of the code.
 *
 */
public class MovementComponent extends Component{
	public static final float DEFAULT_MOVEMENTSPEED = 1F;
	
	public float moveX, moveY;
	public float movementSpeed;
	
	private Entity parent;
	public MovementComponent(Handler handler, Entity parent) {
		super(handler);
		this.movementSpeed = DEFAULT_MOVEMENTSPEED;
		this.parent = parent;
		
	}
	
	public MovementComponent(Handler handler, Entity parent, float movementSpeed) {
		super(handler);
		this.movementSpeed = movementSpeed;
		this.parent = parent;
		
	}
	
	public void move(){
		if(!checkEntityCollisions(moveX, 0f)){
			moveX();
		}
		if(!checkEntityCollisions(0f, moveY)){
			moveY();
		}
		
	}
	
	public void moveX(){
		Rectangle bounds = parent.getComponents(BoundingBoxComponent.class).getBounds();
		
		if(moveX > 0){//moving right
			int tx = (int)(parent.getX() +  moveX + bounds.x + bounds.width)/Tile.TILEWIDTH;
			
			if(!collisionWithTile2(tx, (int) (parent.getY() + bounds.y +1)/Tile.TILEWIDTH )&&
					!collisionWithTile2(tx,(int) (parent.getY() + bounds.y + bounds.height -1)/Tile.TILEWIDTH)){
				parent.increasePosition(new Vector2f(moveX, 0f));
			}else{
				parent.setX(tx * Tile.TILEWIDTH - bounds.x - bounds.width);
			}
				
		}else if(moveX < 0){//moving left
			int tx = (int)(parent.getX() + moveX + bounds.x)/Tile.TILEWIDTH;
				
			if(!collisionWithTile2(tx, (int) (parent.getY() + bounds.y +1)/Tile.TILEWIDTH )&&
				!collisionWithTile2(tx,(int) (parent.getY() + bounds.y + bounds.height -1)/Tile.TILEWIDTH)){
				parent.increasePosition(new Vector2f(moveX, 0f));
			}else{
				parent.setX(tx * Tile.TILEWIDTH + bounds.x + bounds.width); 
			}
		}	
	}
	
	public void moveY(){
		Rectangle bounds = parent.getComponents(BoundingBoxComponent.class).getBounds();
		
		if(moveY > 0){//moving down
			int ty = (int)((parent.getY() + moveY + bounds.y + bounds.height)/Tile.TILEWIDTH);
				
			if(!collisionWithTile2((int)(parent.getX() + bounds.x +1)/Tile.TILEWIDTH, ty)&& !collisionWithTile2((int)(parent.getX() + bounds.x + bounds.width -1)/Tile.TILEWIDTH, ty)){
				parent.increasePosition(new Vector2f(0f, moveY));
			}else{
				parent.setY(ty * Tile.TILEWIDTH - bounds.y - bounds.height);
			}
		}else if(moveY < 0){//moving up
			int ty = (int)((parent.getY() + moveY + bounds.y)/Tile.TILEWIDTH);
				
			if(!collisionWithTile2((int)(parent.getX() + bounds.x +1)/Tile.TILEWIDTH, ty)&& !collisionWithTile2((int)(parent.getX() + bounds.x + bounds.width -1)/Tile.TILEWIDTH, ty)){
				parent.increasePosition(new Vector2f(0f, moveY));
				
			}else{
				parent.setY(ty * Tile.TILEWIDTH + bounds.x + bounds.width);
			}
		}
	
	}
	
	public boolean collisionWithTile2(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntityArray()){
			if(e.equals(parent)){
				continue;
			}
			if(e.hasComponents(BoundingBoxComponent.class)){
				if(e.getComponents(BoundingBoxComponent.class).getCollisionBounds(0f, 0f).
						intersects(parent.getComponents(BoundingBoxComponent.class).getCollisionBounds(xOffset, yOffset))){
					//e.getHit(this);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		
	}
	
	
}
