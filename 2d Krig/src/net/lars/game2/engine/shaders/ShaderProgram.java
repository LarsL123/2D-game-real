package net.lars.game2.engine.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public abstract class ShaderProgram {
	
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	/**
	 * 	Does everything needed to create a proper shaderProgram.
	 * 
	 * @param vertexFile
	 * @param fragmentFile
	 */
	public ShaderProgram(String vertexFile,String fragmentFile){
		vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
		
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		
		bindAttributes();
		
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		
		getAllUniformLocations();
	}
	
	/**
	 * 
	 * @Info
	 * 		Called when the games loads up.
	 * 		 Should bind all uniforms using the 
	 * 		{@link #getUniformLocation(String)} method.
	 *
	 */
	protected abstract void getAllUniformLocations();
	
	/**
	 * 
	 * @Info
	 * 		Get the uniform location to a shader uniform variable.
	 *
	 * @param uniformName
	 * 		The name of the uniform variable. VERY IMPORTANT! There will not be an error if you spell this wrong.
	 * @return
	 * 		The uniform location as an int.
	 */
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID,uniformName);
	}
	
	/**
	 * 
	 * @Info
	 *		Bind the shaderProgram
	 */
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	/**
	 * 
	 * @Info
	 * 		Unbind the shader program
	 *
	 */
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	/**
	 * 
	 * @Info
	 * 		Remove the shader's form memory.
	 *
	 */
	public void cleanUp(){
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	/**
	 * 
	 * @Info
	 *		Should bind the VAO attributes to the correct -in variable- in the vertex shader.
	 *		
	 *		Use {@link #bindAttribute(int, String)} method.
	 */
	protected abstract void bindAttributes();
	
	/**
	 * 
	 * @Info
	 * 		Binds a VAOs attribute list to the specified variable.
	 *
	 * @param attribute
	 * 		The VAO attribute loctaion. Range: 0-15.
	 * @param variableName
	 * 		The variable name in the vertex shader.
	 */
	protected void bindAttribute(int attribute, String variableName){
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	protected void loadFloat(int location, float value){
		GL20.glUniform1f(location, value);
	}
	
	protected void loadInt(int location, int value){
		GL20.glUniform1i(location, value);
	}
	
	protected void loadVector(int location, Vector3f vector){
		GL20.glUniform3f(location,vector.x,vector.y,vector.z);
	}
	
	protected void loadVector(int location, Vector4f vector){
		GL20.glUniform4f(location,vector.x,vector.y,vector.z, vector.w);
	}
	
	protected void load2DVector(int location, Vector2f vector){
		GL20.glUniform2f(location,vector.x,vector.y);
	}
	
	protected void loadBoolean(int location, boolean value){
		float toLoad = 0;
		if(value){
			toLoad = 1;
		}
		GL20.glUniform1f(location, toLoad);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix){
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	/**
	 * 
	 * @Info
	 * 		Loads and builds the shader.
	 *
	 * @param file
	 * 		The file.
	 * @param type
	 * 		GL20.GL_VERTEX_SHADER || GL20.GL_FRAGMENT_SHADER
	 * @return
	 */
	private static int loadShader(String file, int type){
		StringBuilder shaderSource = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
	}

}
