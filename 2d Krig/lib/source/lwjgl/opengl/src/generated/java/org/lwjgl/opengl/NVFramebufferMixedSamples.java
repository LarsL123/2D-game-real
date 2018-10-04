/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import java.nio.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Native bindings to the <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_framebuffer_mixed_samples.txt">NV_framebuffer_mixed_samples</a> extension.
 * 
 * <p>This extension allows multisample rendering with a raster and
 * depth/stencil sample count that is larger than the color sample count.
 * Rasterization and the results of the depth and stencil tests together
 * determine the portion of a pixel that is "covered".  It can be useful to
 * evaluate coverage at a higher frequency than color samples are stored.
 * This coverage is then "reduced" to a collection of covered color samples,
 * each having an opacity value corresponding to the fraction of the color
 * sample covered.  The opacity can optionally be blended into individual
 * color samples.</p>
 */
public class NVFramebufferMixedSamples {

    /** Accepted by the {@code cap} parameter of Enable, Disable, IsEnabled. */
    public static final int
        GL_RASTER_MULTISAMPLE_EXT       = 0x9327,
        GL_COVERAGE_MODULATION_TABLE_NV = 0x9331;

    /** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
    public static final int
        GL_RASTER_SAMPLES_EXT                    = 0x9328,
        GL_MAX_RASTER_SAMPLES_EXT                = 0x9329,
        GL_RASTER_FIXED_SAMPLE_LOCATIONS_EXT     = 0x932A,
        GL_MULTISAMPLE_RASTERIZATION_ALLOWED_EXT = 0x932B,
        GL_EFFECTIVE_RASTER_SAMPLES_EXT          = 0x932C,
        GL_COLOR_SAMPLES_NV                      = 0x8E20,
        GL_DEPTH_SAMPLES_NV                      = 0x932D,
        GL_STENCIL_SAMPLES_NV                    = 0x932E,
        GL_MIXED_DEPTH_SAMPLES_SUPPORTED_NV      = 0x932F,
        GL_MIXED_STENCIL_SAMPLES_SUPPORTED_NV    = 0x9330,
        GL_COVERAGE_MODULATION_NV                = 0x9332,
        GL_COVERAGE_MODULATION_TABLE_SIZE_NV     = 0x9333;

    static { GL.initialize(); }

    protected NVFramebufferMixedSamples() {
        throw new UnsupportedOperationException();
    }

    static boolean isAvailable(GLCapabilities caps) {
        return checkFunctions(
            caps.glRasterSamplesEXT, caps.glCoverageModulationTableNV, caps.glGetCoverageModulationTableNV, caps.glCoverageModulationNV
        );
    }

    // --- [ glRasterSamplesEXT ] ---

    /**
     * Selects the number of samples to be used for rasterization. {@code samples} represents a request for a desired minimum number of samples. Since
     * different implementations may support different sample counts, the actual sample pattern used is implementation-dependent. However, the resulting value
     * for {@link EXTRasterMultisample#GL_RASTER_SAMPLES_EXT RASTER_SAMPLES_EXT} is guaranteed to be greater than or equal to {@code samples} and no more than the next larger sample count supported by the
     * implementation. If {@code fixedsamplelocations} is {@link GL11#GL_TRUE TRUE}, identical sample locations will be used for all pixels. The sample locations chosen are a
     * function of only the parameters to RasterSamplesEXT and not of any other state.
     * 
     * <p>If {@link EXTRasterMultisample#GL_RASTER_MULTISAMPLE_EXT RASTER_MULTISAMPLE_EXT} is enabled, then the sample pattern chosen by RasterSamplesEXT will be used instead of sampling at the center of the pixel.
     * The sample locations can be queried with {@link GL32C#glGetMultisamplefv GetMultisamplefv} with a {@code pname} of {@link GL32#GL_SAMPLE_POSITION SAMPLE_POSITION}, similar to normal multisample sample
     * locations.</p>
     * 
     * <p>The value {@link EXTRasterMultisample#GL_MULTISAMPLE_RASTERIZATION_ALLOWED_EXT MULTISAMPLE_RASTERIZATION_ALLOWED_EXT} is {@link GL11#GL_TRUE TRUE} if {@link GL13#GL_SAMPLE_BUFFERS SAMPLE_BUFFERS} is one or if {@link EXTRasterMultisample#GL_RASTER_MULTISAMPLE_EXT RASTER_MULTISAMPLE_EXT} is enabled. The value
     * {@link EXTRasterMultisample#GL_EFFECTIVE_RASTER_SAMPLES_EXT EFFECTIVE_RASTER_SAMPLES_EXT} is equal to {@link EXTRasterMultisample#GL_RASTER_SAMPLES_EXT RASTER_SAMPLES_EXT} if {@link EXTRasterMultisample#GL_RASTER_MULTISAMPLE_EXT RASTER_MULTISAMPLE_EXT} is enabled, otherwise is equal to {@link GL13#GL_SAMPLES SAMPLES}.</p>
     * 
     * <p>Explicit multisample rasterization can not be used in conjunction with depth, stencil, or depth bounds tests, multisample framebuffers, or if
     * {@link EXTRasterMultisample#GL_RASTER_SAMPLES_EXT RASTER_SAMPLES_EXT} is zero. If {@link EXTRasterMultisample#GL_RASTER_MULTISAMPLE_EXT RASTER_MULTISAMPLE_EXT} is enabled, the error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} will be generated by Draw commands if</p>
     * 
     * <ul>
     * <li>the value of {@link EXTRasterMultisample#GL_RASTER_SAMPLES_EXT RASTER_SAMPLES_EXT} is zero</li>
     * <li>the depth, stencil, or depth bounds test is enabled</li>
     * <li>a multisample draw framebuffer is bound ({@link GL13#GL_SAMPLE_BUFFERS SAMPLE_BUFFERS} is one)</li>
     * </ul>
     * 
     * <h5>Errors</h5>
     * 
     * <p>An {@link GL11#GL_INVALID_VALUE INVALID_VALUE} error is generated if {@code samples} is greater than the value of {@link EXTRasterMultisample#GL_MAX_RASTER_SAMPLES_EXT MAX_RASTER_SAMPLES_EXT} (the implementation-dependent maximum
     * number of samples).</p>
     *
     * @param samples              the number of samples to be used for rasterization
     * @param fixedsamplelocations if {@link GL11#GL_TRUE TRUE}, identical sample locations will be used for all pixels
     */
    public static void glRasterSamplesEXT(@NativeType("GLuint") int samples, @NativeType("GLboolean") boolean fixedsamplelocations) {
        EXTRasterMultisample.glRasterSamplesEXT(samples, fixedsamplelocations);
    }

    // --- [ glCoverageModulationTableNV ] ---

    /**
     * Unsafe version of: {@link #glCoverageModulationTableNV CoverageModulationTableNV}
     *
     * @param n The size of the coverage modulation table. Must be equal to the value of COVERAGE_MODULATION_TABLE_SIZE_NV.
     */
    public static native void nglCoverageModulationTableNV(int n, long v);

    public static void glCoverageModulationTableNV(@NativeType("GLfloat const *") FloatBuffer v) {
        nglCoverageModulationTableNV(v.remaining(), memAddress(v));
    }

    // --- [ glGetCoverageModulationTableNV ] ---

    public static native void nglGetCoverageModulationTableNV(int bufsize, long v);

    public static void glGetCoverageModulationTableNV(@NativeType("GLfloat *") FloatBuffer v) {
        nglGetCoverageModulationTableNV(v.remaining(), memAddress(v));
    }

    // --- [ glCoverageModulationNV ] ---

    public static native void glCoverageModulationNV(@NativeType("GLenum") int components);

    /** Array version of: {@link #glCoverageModulationTableNV CoverageModulationTableNV} */
    public static void glCoverageModulationTableNV(@NativeType("GLfloat const *") float[] v) {
        long __functionAddress = GL.getICD().glCoverageModulationTableNV;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, v.length, v);
    }

    /** Array version of: {@link #glGetCoverageModulationTableNV GetCoverageModulationTableNV} */
    public static void glGetCoverageModulationTableNV(@NativeType("GLfloat *") float[] v) {
        long __functionAddress = GL.getICD().glGetCoverageModulationTableNV;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, v.length, v);
    }

}