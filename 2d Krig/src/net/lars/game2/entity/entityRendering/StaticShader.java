package net.lars.game2.entity.entityRendering;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import net.lars.game2.engine.shaders.ShaderProgram;
import net.lars.game2.entity.Entity;
import net.lars.game2.main.GameCamera;
import net.lars.game2.utils.Maths;


public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/net/lars/game2/entity/entityRendering/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/net/lars/game2/entity/entityRendering/fragmentShader.txt";
	
	private int location_transformation;
	private int location_projectionMatrx;
	private int location_viewMatrix;
	

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrx = super.getUniformLocation("projectionMatrx");
		location_transformation = super.getUniformLocation("transform");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "positions");
	}
	
	public void loadTranformation(Entity e) {
		super.loadVector(location_transformation, new Vector4f(e.getX(), e.getY(), e.getWidth(), e.getHeight()));
	}
	
	public void loadProjectionMatrix(Matrix4f p) {
		super.loadMatrix(location_projectionMatrx, p);
	}
	
	public void loadViewMatrix(GameCamera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}

}
