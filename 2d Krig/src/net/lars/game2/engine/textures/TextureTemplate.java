package net.lars.game2.engine.textures;

import net.lars.game2.utils.data.Color;
import net.lars.game2.utils.fileUtils.MyFile;

/**
 * 	
 * @Info
 * 		The data in this class determines how a texture is created.
 * 		When loading up a texture to openGL you need to pass a {@link #TextureTemplate()} object to the loader.
 *
 * @Date 19. aug. 2018
 * @Author LarsL123, Credit: ThinMatrix
 *
 *
 * @TODO
 * 
 * @Suggestions
 *
 */
public class TextureTemplate {
	
	private MyFile file;
	
	private boolean clampEdges = false;
	private boolean mipmap = false;
	private boolean anisotropic = false;
	private boolean nearest = false;
	private boolean clampToBorder = true;
	private Color borderColour = new Color(0,0,0,0);
	
	public TextureTemplate(MyFile file) {
		this.file = file;
	}
	
	public TextureTemplate clampEdges(){
		this.clampEdges = true;
		clampToBorder = false;
		return this;
	}
	
	public TextureTemplate clampToBorder(float r, float g, float b, float a){
		borderColour.setColor(r, g, b, a);
		clampToBorder = true;
		clampEdges = false;
		return this;
	}
	
	public TextureTemplate noMipMap(){
		this.mipmap = false;
		this.anisotropic = false;
		return this;
	}
	
	public TextureTemplate nearestFiltering(){
		this.mipmap = false;
		this.nearest = true;
		return this;
	}
	
	public TextureTemplate noFiltering(){
		this.anisotropic = false;
		return this;
	}
	
	public Texture create() {
		TextureData data=  TextureMaster.decodeTextureFile(file);
		return new Texture(TextureMaster.loadTextureToOpenGL(data, this));
		
	}
	
	protected boolean isClampEdges() {
		return clampEdges;
	}
	
	protected Color getBorderColour(){
		return borderColour;
	}
	
	protected boolean isClampToBorder(){
		return clampToBorder;
	}

	protected boolean isMipmap() {
		return mipmap;
	}

	protected boolean isAnisotropic() {
		return anisotropic;
	}

	protected boolean isNearest() {
		return nearest;
	}

	protected MyFile getFile() {
		return file;
	}
}
