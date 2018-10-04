package net.lars.game2.utils.data;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

public class Color {
	
	private Vector3f color = new Vector3f();
	private float alpha = 1;
	
	public Color(int r, int g, int b, int alpha) {
		this.color.set(r, g, b);
		this.alpha = alpha;
	}
	
	public Color(int r, int g, int b) {
		this.color.set(r, g, b);
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	public void setColor(float r, float g, float b,  float alpha) {
		this.color.set(r, g, b);
		this.alpha = alpha;
	}

	public Vector3f getColor() {
		return color;
	}


	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public FloatBuffer getAsFloatBuffer() {
		FloatBuffer b = BufferUtils.createFloatBuffer(4);
		b.put(new float[] {color.getX(), color.getY(), color.getZ(), alpha});
		b.flip();
		return b;
	}
	
	
}
