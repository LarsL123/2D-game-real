package net.lars.game2.engine.openGl;

import org.lwjgl.util.vector.Matrix4f;

import net.lars.game2.display.DisplayManager;
import net.lars.game2.engine.textures.Texture;
import net.lars.game2.entity.entityRendering.EntityRenderer;
import net.lars.game2.entity.entityRendering.StaticShader;
import net.lars.game2.game.GameCamera;
import net.lars.game2.game.Handler;
import net.lars.game2.game.RenderData;
import net.lars.game2.graphics.Assets2;
import net.lars.game2.tiles.tileRendering.TileRenderer;
import net.lars.game2.utils.Maths;



public class MasterRenderer {
	
	
	/**
	 * 
	 * @see https://github.com/LWJGL/lwjgl/blob/master/src/java/org/lwjgl/test/opengles/util/GLMatrix.java
	 * 
	 * Creates a orthographickProjectionMatrx Matrix. The Matrix has an y invention since all textures are stored upside down.
	 * 
	 * 
	 * 	This Matrix transforms a pixel coordinate between 0/0 and Width/Height to one between -1/1
	 * 	
	 * 	I can now use  x, y ,width and height attributes to represent a quad.
	 * 
	 * 	This is the screen coordinates now!(before applying the matrix) :
	 * 
	 * 
	 *         0.0  *-----------------------*  Width.0
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-	
	 *    0 height  *-----------------------* Width.Height
	 *    
	 *    
	 *	Converts it to the normal openGL coordinate system:
	 * 
	 *        -1.-1 *-----------------------*  1,-1
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  			-		   0,0			-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-
	 *  		    -						-	
	 *    1-1  		*-----------------------* 1,1
	 **/
	private Matrix4f orthographickProjectionMatrx = Maths.orthoProjectionMatrix(0.0f, DisplayManager.WIDTH, DisplayManager.HEIGHT, 0.0f, -1.0f, 1.0f);
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	private TileRenderer tileRenderer;
	
	private Texture tileTexture = Assets2.tree_one;
	
	public MasterRenderer(Handler handler, Loader loader) {
		//LoadProjectionMatrix
		shader.start();
		shader.loadProjectionMatrix(orthographickProjectionMatrx);
		shader.stop();
		
		tileRenderer = new TileRenderer(handler ,loader, orthographickProjectionMatrx);
		renderer = new EntityRenderer(shader, loader);
	}
	
	public void render(RenderData data, GameCamera camera) {
		
//		//TODO load camera matrix
		tileRenderer.render(Assets2.caracterHoleImage);
		
		
		shader.start();
		renderer.prepare();		
		shader.loadViewMatrix(camera);
		renderer.render(data.getTexturesTorender());
		shader.stop();
		
		data.clear();
	}

	public void cleanUP() {
		shader.cleanUp();
		tileRenderer.cleanUP();
	}
}
