package net.lars.game2.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import net.lars.game2.main.GameCamera;

public class Maths {
	
	public static Matrix4f orthoProjectionMatrix(final float left, final float right, final float bottom, final float top, final float near, final float far) {
		final Matrix4f m = new Matrix4f();
		m.setIdentity();

		m.m00 = 2.0f / (right - left);
		m.m30 = -(right + left) / (right - left);

		m.m11 = 2.0f / (top - bottom);
		m.m31 = -(top + bottom) / (top - bottom);

		m.m22 = -2.0f / (far - near);
		m.m32 = -(far + near) / (far - near);
		return m;
	}
	
	//TODO Read more about the ViewMatrix.
	public static Matrix4f createViewMatrix(GameCamera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		//They are there if i want to add rotation or a shaking effect.
//		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix,
//				viewMatrix);
//		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector2f cameraPos = camera.getAsVector();
		Vector2f negativeCameraPos = new Vector2f(-cameraPos.x,-cameraPos.y);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}
	
}
