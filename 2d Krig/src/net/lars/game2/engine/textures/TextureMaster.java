package net.lars.game2.engine.textures;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import net.lars.game2.utils.fileUtils.MyFile;


/**
 * 	
 * @Info
 * 		Manages loading an unloading of textures.
 *
 * @Date 20. aug. 2018
 * @Author LarsL123 , Credit: ThinMatarix.
 *
 *
 * @TODO
 * 
 * @Suggestions
 *
 */
public class TextureMaster {
	
	/**
	 * Holds an instance of all textureID's.
	 */
	private static List<Integer> loadedTextures = new ArrayList<Integer>();
	
	/**
	 * Credit: ThinMatrix.
	 * @Info
	 * 		Reads a textureFile and stores informationAbout it to a Texture
	 *
	 * @param file
	 * @return
	 */
	protected static TextureData decodeTextureFile(MyFile file) {
		int width = 0;
		int height = 0;
		ByteBuffer buffer = null;
		try {
			//FIXME This is really weard! You can not have a / at the start of an URL when loading a texture. In loading worlds tho, you need a / to not get an error in the loading. kind of bad solution, but using a substring for now.
			InputStream in = new FileInputStream(file.getPath().substring(1));
			PNGDecoder decoder = new PNGDecoder(in);
			width = decoder.getWidth();
			height = decoder.getHeight();
			buffer = ByteBuffer.allocateDirect(4 * width * height);
			decoder.decode(buffer, width * 4, Format.BGRA);
			buffer.flip();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Tried to load texture " + file.getName() + " , didn't work");
			System.exit(-1);
		}
		return new TextureData(buffer, width, height);
	}
	
	
	/**
	 * 
	 * Credit: ThinMatrix
	 * 
	 * @Info
	 * 		-Loads a texture in to openGL.
	 *
	 * @param data
	 * 		A TextureData Object containing the data files.
	 * @param builder
	 * 		-A textureTemplate class. Holds information about how the texture should be loaded up.
	 * @return
	 * 		-The texureID for the load texture.
	 */
	protected static int loadTextureToOpenGL(TextureData data, TextureTemplate builder) {
		int texID = GL11.glGenTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL12.GL_BGRA,
				GL11.GL_UNSIGNED_BYTE, data.getBuffer());
		if (builder.isMipmap()) {
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			if (builder.isAnisotropic()) {
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, 0);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT,
						4.0f);
			}
		} else if (builder.isNearest()) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		}
		if (builder.isClampEdges()) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		} else if (builder.isClampToBorder()) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
			GL11.glTexParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_BORDER_COLOR, builder.getBorderColour().getAsFloatBuffer());
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		}
		loadedTextures.add(texID);
		return texID;
	}
	
	/**
	 * 
	 * @Info
	 * 	-Deletes a texture from openGL.
	 *
	 * @param textureID
	 */
	protected static void deleteTexure(int textureID) {
		loadedTextures.remove(textureID);
		GL11.glDeleteTextures(textureID);
	}
	
	/**
	 * 
 	 * @Info
 	 *   -Deletes all textures from openGL.
	 *
	 */
	public static void cleanUp() {
		for(int i: loadedTextures) {
			GL11.glDeleteTextures(i);
		}
		loadedTextures.clear();
	}
}
