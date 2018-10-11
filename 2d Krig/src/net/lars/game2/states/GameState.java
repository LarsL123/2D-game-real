package net.lars.game2.states;


import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.entity.Entity;
import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;
import net.lars.game2.graphics.Assets;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.utils.fileUtils.MyFile;
import net.lars.game2.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);	
	}

	@Override
	public void tick() {
		handler.getGameCamera().moveWidthInputs();
		if(world != null){
			
			world.tick();
			
		}
		
	}

	@Override
	public void render(RenderData data) {
		if(world != null){
			world.render(data);
			
//			world.getEntityManager().getCaracter().postRender(g);
		}
	}
	
	public World getWorld(){
		return world;
	}

	@Override
	public void onStateSet() {
		
		
		world = new World(handler, new MyFile("worlds/world1NewTile.txt"), false);
		handler.setWorld(world);
		
		
		//Entities
		
		Entity entity_1 = new Entity(handler, Assets2.tree_one,  new Vector2f(300, 200), 200, 200, 40, 40, 40, 40);
		handler.getWorld().getEntityManager().addEntity(entity_1);
				
		Entity tree_1 = new Entity(handler, Assets2.tree_one,  new Vector2f(300, 300), 300, 300);
		handler.getWorld().getEntityManager().addEntity(tree_1);
		
	}

}
