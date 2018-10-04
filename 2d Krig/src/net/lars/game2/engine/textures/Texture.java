package net.lars.game2.engine.textures;

import net.lars.game2.utils.fileUtils.MyFile;

public class Texture {

	private int textureID;
	
	private boolean isLoaded = false;
	
	protected Texture(int textureID) {
		this.textureID = textureID;
		this.isLoaded = true;
	}
	
	protected Texture() {
		
	}

	protected void setTextureiD(int textureId) {
		this.textureID = textureId;
		this.isLoaded = true;
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public int getTextureID() {
		return textureID;
	}
	
	public void deleteTexture() {
		isLoaded = false;
		TextureMaster.deleteTexure(textureID);
	}
	
	public static TextureTemplate createNewTexture(MyFile file) {
		return new TextureTemplate(file);
	}
}
