package net.lars.game2.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;

import net.lars.game2.component.Component;
import net.lars.game2.components.BoundingBoxComponent;
import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;

public class EntityManager {
	
	private Handler handler;
	private Caracter caracter;
	protected static ArrayList<Entity> entityArray = new ArrayList<Entity>();
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getComponents(BoundingBoxComponent.class).getBounds().getHeight() <  
					b.getY() + b.getComponents(BoundingBoxComponent.class).getBounds().getHeight()){
				return -1;
			}
			return 1;
		}
	};
	
	public EntityManager(Handler handler,Caracter caracter) {
		this.handler = handler;
		
		if(caracter != null){
			this.caracter = caracter;
			addEntity(this.caracter);
		}
		
	}
	
	public void tick(){
		for(HashMap<UUID, ? extends Component> value : Entities.components.values()){
			for(Component c : value.values()){
				c.tick();
			}
		}
		if(caracter != null){
			caracter.tick();
		}
		
		entityArray.sort(renderSorter);
	}
	
	public void render(RenderData data){
		for(Entity e : entityArray){
			e.render(data);
		}
	}
	
	public void addEntity(Entity e){
		entityArray.add(e);
	}

	//GET AND SET
	public Caracter getCaracter(){
		return this.caracter;
	}

	public ArrayList<Entity> getEntityArray() {
		return entityArray;
	}
		
}
