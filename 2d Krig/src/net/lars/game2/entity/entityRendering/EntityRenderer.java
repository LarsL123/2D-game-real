package net.lars.game2.entity.entityRendering;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import net.lars.game2.engine.models.RawModel;
import net.lars.game2.engine.openGl.Loader;
import net.lars.game2.engine.shaders.renderingTemplate.Renderer;
import net.lars.game2.engine.textures.Texture;
import net.lars.game2.entity.Entity;



public class EntityRenderer extends Renderer {
	
	private StaticShader shader;

	public EntityRenderer(StaticShader shader, Loader loader) {
		super(loader);
		model = loader.loadToVAO(POSITIONS, 2);
		this.shader = shader;
	}
	
	public void prepare() {
		//Bind the VAO.
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		
	}
	
	public void render(Map<Texture, List<Entity>> enitties) {
		for(Texture tex: enitties.keySet()) {
			prepareTexture(tex);
			List<Entity> batch = enitties.get(tex);
			for(Entity entity: batch) {
				prepareInstace(entity);
				GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, model.getVertextCount());
			}
		}
		unbindModel();
	}
	
	
	private void unbindModel() {
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);	
	}

	private void prepareTexture(Texture tex) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getTextureID());
	}
	
	private void prepareInstace(Entity e) {
		shader.loadTranformation(e);
	}

	@Override
	public void cleanUP() {
		shader.cleanUp();
		
	}
}
