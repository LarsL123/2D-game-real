package net.lars.game2.tiles.tileRendering;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.engine.openGl.Loader;
import net.lars.game2.engine.shaders.renderingTemplate.Renderer;
import net.lars.game2.engine.textures.Texture;
import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.worlds.chunk.Chunk;



public class TileRenderer extends Renderer{
	/**
	 * All four vertices. Rendering using triangle strips so only four.
	 */
	private static final float[] POSITIONS_IN_PIXELS = {0,50, 0, 0, 50,50,50,0};
	
	//The max number of tiles.
	private static final int MAX_INSTANCES = 1000;
	//How many floats loaded to each instance.
	private static final int INSTANCED_DATA_LENGTH = 2;
	private int numberOfTilesPerChunk = 64;
	
	//Reusable FloatBuffer.
	private static final FloatBuffer buffer = BufferUtils.createFloatBuffer(MAX_INSTANCES * INSTANCED_DATA_LENGTH);
	
	private int vbo;

	private TileShader shader = new TileShader();
	
	private Texture t = Assets2.loadNearestTexture("res/textures/tileSets/world1tilest.png");
	
	public TileRenderer(Handler handler, Loader loader, Matrix4f orthoMatrix) {
		super(handler, loader);
		this.vbo = loader.createEmptyVBO(INSTANCED_DATA_LENGTH * MAX_INSTANCES);
		//Add the primitives cordinastes.
		model = loader.loadToVAO(POSITIONS_IN_PIXELS, 2);
		//Setup an instanced attribute. Contains the vboData array.
		loader.addinstacedAttribute(model.getVaoID(), vbo, 1, 2, INSTANCED_DATA_LENGTH, 0);
		
		shader.start();
		shader.loadOrthoMatrix(orthoMatrix);
		shader.loadScale(200);
		shader.stop();	
	}
	
	public void prepare() {
		//Bind the VAO.
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);	
	}
	
	public void render(Texture texture) {
		prepare();	
		prepareTexture(t);
		shader.start();
		
		ArrayList<Chunk> chunks = handler.getWorld().getTileRenderData();
		for(Chunk c : chunks) {
			if(c != null) {
				renderChunk(c);
			}
		}
		shader.stop();
		unbindModel();
	}
	
	private void renderChunk(Chunk chunk) {
		float[] vboData = new float[INSTANCED_DATA_LENGTH * numberOfTilesPerChunk];
		//Add positions and tile tetxureCoords
		int pointer = 0;

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Vector2f v = handler.getWorld().getTile(chunk.getTiles()[x][y]).getPosition();
				vboData[pointer++] = v.x;
				vboData[pointer++] = v.y;
			}
		}

		loader.updateVbo(vbo, vboData, buffer);
		shader.loadChunkPosition(chunk.getWorldPosition());
		
		
		GL31.glDrawArraysInstanced(GL11.GL_TRIANGLE_STRIP, 0, model.getVertextCount(), numberOfTilesPerChunk);
	}

	private void unbindModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);	
	}

	private void prepareTexture(Texture texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	}
	
//	private void storeXYGridPosition(float [] data) {
//		data[pointer++] = 10f;
//		data[pointer++] = 10f;
//	}
//	
//	
//	private void storeXYTextureCoords(float[] data) {
//		data[pointer++] = 1f;
//		data[pointer++] = 1f;
//		
//	}

	@Override
	public void cleanUP() {
		shader.cleanUp();
		
	}
}
