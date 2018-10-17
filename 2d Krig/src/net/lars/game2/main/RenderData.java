package net.lars.game2.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lars.game2.engine.textures.Texture;
import net.lars.game2.entity.Entity;

public class RenderData {
	
	private Map<Texture, List<Entity>> entitiesToRender = new HashMap<Texture, List<Entity>>();
	
	public void clear() {
		entitiesToRender.clear();
	}
	
	public Map<Texture, List<Entity>> getTexturesTorender() {
		return entitiesToRender;
	}
	
	public void addEntity(Entity e) {
		Texture tex = e.getTexture();
		List<Entity> batch = entitiesToRender.get(tex);
		if(batch != null) {
			batch.add(e);
		}else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(e);
			entitiesToRender.put(tex, newBatch);
		}
	}
}
