package net.lars.game2.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import net.lars.game2.component.Component;
import net.lars.game2.game.Handler;


/**
 * 	
 * @Date 18. mai 2018
 * @Author LarsL123
 *
 * @Info:
 * 		All the component based arcitexture;
 * 		Found the code at https://gist.github.com/Root3287/d2ef12cfca9c3c8f9010c2a337bd0ecb. 
 * 		There was one error. You need to convert the T componet to a .class object in the .addCompoent  methods. 
 *
 * @TODO:
 * 	Move the entity list to the entity manager.
 *  Make a way to remove entities and their components when they die. Dette kan gjøres på an annen thread.
 * @Suggestions:
 *
 */
public class Entities {
	
	public UUID id;
	protected Handler handler;
	

	protected static HashMap<Class, HashMap<UUID, ? extends Component>> components = new HashMap<>();
	
	protected Entities(Handler handler){
		this.id = UUID.randomUUID();
		this.handler = handler;
	}
	
	public static final <T extends Component> void addComponent(UUID entity, T component){
		synchronized (components) {
			HashMap<UUID, ? extends Component> store = components.get(component.getClass());
			if(store == null){
				store = new HashMap<UUID, T>();
				components.put(component.getClass(), store);
			}
			((HashMap<UUID, T>)store).put(entity, component);
		}
	}
	
	public final <T extends Component> void addComponent(T component){
		synchronized (components) {
			HashMap<UUID, ? extends Component> store = components.get(component.getClass());
			if(store == null){
				store = new HashMap<UUID, T>();
				components.put(component.getClass(), store);
			}
			((HashMap<UUID, T>)store).put(this.id, component);
		}
	}
	
	public final static <T> T getComponents(UUID entity, Class<T> component){
		HashMap<UUID, ? extends Component> store = components.get(component);
		T results = (T) store.get(entity);
		if(results == null)
			throw new IllegalArgumentException("Get Fail: "+entity.toString()+" does not posses Component of Class \n missing: "+ component);
		return results;
	}
	public final <T> T getComponents( Class<T> component) {
		HashMap<UUID, ? extends Component> store = components.get(component);
		T results = (T) store.get(this.id);
		if(results == null)
			throw new IllegalArgumentException("Get Fail: "+this.id.toString()+" does not posses Component of Class \n missing: "+ component);
		return results;
	}
	public final static <T> boolean hasComponents(UUID entity, Class<T> component){
		HashMap<UUID, ? extends Component> store = components.get(component);
		T results = (T) store.get(entity);
		if(results == null)
			return false;
		return true;
	}
	public final <T> boolean hasComponents( Class<T> component) {
		HashMap<UUID, ? extends Component> store = components.get(component);
		if(store != null){
			if((T) store.get(id) == null)
				return false;
			return true;
		}
		return false;
		
	}
	
	/**
	 * 
	 * @Info:
	 * 		A method i make myself.
	 *
	 * @param component
	 * @return
	 * 		Returns a list of all entities width then given component.
	 */
	public static final <T> ArrayList<Entity> getEntitiesWidthComponent(Handler handler, Class<T> component){
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		for(Entity e : handler.getWorld().getEntityManager().getEntityArray()){
			if(hasComponents(e.id, component)){
				entityList.add(e);
			}
		}
		return entityList;
	}
	
}	
