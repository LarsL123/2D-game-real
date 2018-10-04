package net.lars.game2.engine.openGl;
 
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
 
 
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import net.lars.game2.engine.models.RawModel;

 
public class Loader {
     
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();
     
	public RawModel loadToVAO(float[] positions,float[] textureCoords,float[] normals, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		unbindVAO();
		return new RawModel(vaoID,indices.length);		
	}
	
	public int createEmptyVBO(int floatCount) {
		int vbo = GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatCount * 4, GL15.GL_STREAM_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vbo;
	}
	
	public void addAttributeToVAO(int vao, int attributeNumber, int dimentions, float[] data ) {
		GL30.glBindVertexArray(vao);
		storeDataInAttributeList(attributeNumber, dimentions, data);
		unbindVAO();
		
	}
	
	public void addinstacedAttribute(int vao, int vbo, int attributeNum, int dataSize, int instacedDataLength, int offset) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL30.glBindVertexArray(vao);
		GL20.glVertexAttribPointer(attributeNum, dataSize,GL11.GL_FLOAT, false, instacedDataLength * 4, offset *4);
		GL33.glVertexAttribDivisor(attributeNum, 1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
	}
	
	public void updateVbo(int vbo, float[] data, FloatBuffer buffer) {
		buffer.clear();
		buffer.put(data);
		buffer.flip();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * 4, GL15.GL_STREAM_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); 
	}
	
	/**
	 *  Creates a RawMoel.
	 *  Used when i want only a gui or other simple object.
	 *  This is the method that i will us in my 2d game.
	 * @param positions
	 * 		- The vertex positions.
	 * @param dimensions
	 * 		- The number of dimensions the model will be made in;
	 * 		- 2D, 3D ect.
	 * @return
	 * 		-Returns a RawModel
	 */
	public RawModel loadToVAO(float[] positions, int dimensions){
		int vaoID = createVAO();
		this.storeDataInAttributeList(0, dimensions, positions);
		unbindVAO();
		return new RawModel(vaoID, positions.length/dimensions);
	}
     
//    public int loadTexture(String fileName) {
//        Texture texture = null;
//        try {
//            texture = TextureLoader.getTexture("PNG",
//                    new FileInputStream("res/" + fileName + ".png"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Tried to load texture " + fileName + ".png , didn't work");
//            System.exit(-1);
//        }
//        textures.add(texture.getTextureID());
//        return texture.getTextureID();
//    }
     
    public void cleanUp(){
        for(int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo:vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int texture:textures){
            GL11.glDeleteTextures(texture);
        }
    }
     
    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
     
    private void storeDataInAttributeList(int attributeNumber, int coordinateSize,float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
     
    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }
    
   /**
    * 
    * @Info
    * 		Not sure, but it binds the IdiciesBuffer. This mostly used in 3D tho.
    *
    * @param indices
    */
    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
    
    /**
     * 
     * @Info
     * 		Stores a int array to a int buffer.
     *
     * @param data
     * @return
     */
    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    /**
     * 
     * @Info
     *		Stores a float array to a float buffer.
     * @param data
     * 		
     * -The input data.
     * @return
     * 		-The floatBuffer.
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
     
     
 
}
