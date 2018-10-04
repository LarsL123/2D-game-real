package net.lars.game2.tiles.tileRendering;

import org.lwjgl.util.vector.Matrix4f;

import net.lars.game2.engine.shaders.ShaderProgram;

public class TileShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/net/lars/game2/tiles/tileRendering/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/net/lars/game2/tiles/tileRendering/fragmentShader.txt";
	
	private int loctaion_orthograpickMatrix;
	private int location_size;
	private int location_tilesPerRowAndColumn;
	
	

	public TileShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		loctaion_orthograpickMatrix = super.getUniformLocation("orthoMatrix");
		location_size = super.getUniformLocation("scale");
		location_tilesPerRowAndColumn = super.getUniformLocation("tilesPerRowAndColumn");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "positions");
		super.bindAttribute(1, "firstXY");
		super.bindAttribute(2, "secondXY");
	}
	
	public void loadOrthoMatrix(Matrix4f orthoMatrix) {
		super.loadMatrix(loctaion_orthograpickMatrix, orthoMatrix);
	}
	
	public void loadScale(float scale) {
		super.loadFloat(location_size, scale);
	}
	
}
