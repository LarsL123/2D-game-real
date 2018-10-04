package net.lars.game2.engine.textures;

import java.nio.ByteBuffer;

public class TextureData {
	
	private int width;
	private int height;
	private ByteBuffer buffer;
	
	protected TextureData(ByteBuffer buffer, int width, int height){
		this.buffer = buffer;
		this.width = width;
		this.height = height;
	}
	
	protected int getWidth(){
		return width;
	}
	
	protected int getHeight(){
		return height;
	}
	
	protected ByteBuffer getBuffer(){
		return buffer;
	}

}
