package net.lars.game2.tiles.tileRendering;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.util.vector.Matrix4f;

import net.lars.game2.engine.openGl.Loader;
import net.lars.game2.engine.shaders.renderingTemplate.Renderer;
import net.lars.game2.engine.textures.Texture;
import net.lars.game2.game.Handler;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.tiles.Tile;
import net.lars.game2.tiles.Tileset;



public class TileRenderer extends Renderer{
	
	Random random = new Random();
	
	private static final float[] TEXTURE_COORDS_THING = { 0,0 ,0, 0, 0.25f, 0.25f, 0.25f, 0.25f };
	/**
	 * All four vertices. Rendering using triangle strips so only four.
	 */
	private static final float[] POSITIONS_IN_PIXELS = {0,50, 0, 0, 50,50,50,0};
	
	//The max number of tiles.
	private static final int MAX_INSTANCES = 1000;
	//How many floats loaded to each instance.
	private static final int INSTANCED_DATA_LENGTH = 2;
	
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
		//Add the the offset to texture thing.
		loader.addAttributeToVAO(model.getVaoID(),3, 2, TEXTURE_COORDS_THING);
		//Stores the grid position and texture of the thing.
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
		int numberOfTiles = 64;
		float[] vboData = new float[INSTANCED_DATA_LENGTH * numberOfTiles];
		//Add positions and tile tetxureCoords
		int pointer = 0;

		ArrayList<Tile> til = handler.getWorld().getTileRenderData();
		for(Tile t : til) {
			vboData[pointer++] = t.getX();
			vboData[pointer++] = t.getY();
		}
		
			

		loader.updateVbo(vbo, vboData, buffer);
		//Stet the last parameter to the number of tiles(instaces)
		GL31.glDrawArraysInstanced(GL11.GL_TRIANGLE_STRIP, 0, model.getVertextCount(), numberOfTiles);
		shader.stop();
		unbindModel();
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
