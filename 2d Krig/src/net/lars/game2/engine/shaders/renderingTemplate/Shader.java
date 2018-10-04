package net.lars.game2.engine.shaders.renderingTemplate;

import net.lars.game2.engine.shaders.ShaderProgram;

public class Shader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/net/lars/game2/tiles/tileRendering/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/net/lars/game2/tiles/tileRendering/fragmentShader.txt";

	public Shader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "positions");
	}
	
}
