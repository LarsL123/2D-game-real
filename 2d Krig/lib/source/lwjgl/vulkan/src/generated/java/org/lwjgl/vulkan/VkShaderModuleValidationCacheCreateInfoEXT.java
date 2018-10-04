/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

import javax.annotation.*;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.system.*;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

/**
 * Specify validation cache to use during shader module creation.
 * 
 * <h5>Valid Usage (Implicit)</h5>
 * 
 * <ul>
 * <li>{@code sType} <b>must</b> be {@link EXTValidationCache#VK_STRUCTURE_TYPE_SHADER_MODULE_VALIDATION_CACHE_CREATE_INFO_EXT STRUCTURE_TYPE_SHADER_MODULE_VALIDATION_CACHE_CREATE_INFO_EXT}</li>
 * <li>{@code validationCache} <b>must</b> be a valid {@code VkValidationCacheEXT} handle</li>
 * </ul>
 * 
 * <h3>Member documentation</h3>
 * 
 * <ul>
 * <li>{@code sType} &ndash; the type of this structure.</li>
 * <li>{@code pNext} &ndash; {@code NULL} or a pointer to an extension-specific structure.</li>
 * <li>{@code validationCache} &ndash; the validation cache object from which the results of prior validation attempts will be written, and to which new validation results for this {@code VkShaderModule} will be written (if not already present).</li>
 * </ul>
 * 
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct VkShaderModuleValidationCacheCreateInfoEXT {
 *     VkStructureType sType;
 *     void const * pNext;
 *     VkValidationCacheEXT validationCache;
 * }</code></pre>
 */
public class VkShaderModuleValidationCacheCreateInfoEXT extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        STYPE,
        PNEXT,
        VALIDATIONCACHE;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(8)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        STYPE = layout.offsetof(0);
        PNEXT = layout.offsetof(1);
        VALIDATIONCACHE = layout.offsetof(2);
    }

    VkShaderModuleValidationCacheCreateInfoEXT(long address, @Nullable ByteBuffer container) {
        super(address, container);
    }

    /**
     * Creates a {@link VkShaderModuleValidationCacheCreateInfoEXT} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public VkShaderModuleValidationCacheCreateInfoEXT(ByteBuffer container) {
        this(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** Returns the value of the {@code sType} field. */
    @NativeType("VkStructureType")
    public int sType() { return nsType(address()); }
    /** Returns the value of the {@code pNext} field. */
    @NativeType("void const *")
    public long pNext() { return npNext(address()); }
    /** Returns the value of the {@code validationCache} field. */
    @NativeType("VkValidationCacheEXT")
    public long validationCache() { return nvalidationCache(address()); }

    /** Sets the specified value to the {@code sType} field. */
    public VkShaderModuleValidationCacheCreateInfoEXT sType(@NativeType("VkStructureType") int value) { nsType(address(), value); return this; }
    /** Sets the specified value to the {@code pNext} field. */
    public VkShaderModuleValidationCacheCreateInfoEXT pNext(@NativeType("void const *") long value) { npNext(address(), value); return this; }
    /** Sets the specified value to the {@code validationCache} field. */
    public VkShaderModuleValidationCacheCreateInfoEXT validationCache(@NativeType("VkValidationCacheEXT") long value) { nvalidationCache(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public VkShaderModuleValidationCacheCreateInfoEXT set(
        int sType,
        long pNext,
        long validationCache
    ) {
        sType(sType);
        pNext(pNext);
        validationCache(validationCache);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public VkShaderModuleValidationCacheCreateInfoEXT set(VkShaderModuleValidationCacheCreateInfoEXT src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static VkShaderModuleValidationCacheCreateInfoEXT malloc() {
        return create(nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static VkShaderModuleValidationCacheCreateInfoEXT calloc() {
        return create(nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated with {@link BufferUtils}. */
    public static VkShaderModuleValidationCacheCreateInfoEXT create() {
        return new VkShaderModuleValidationCacheCreateInfoEXT(BufferUtils.createByteBuffer(SIZEOF));
    }

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance for the specified memory address. */
    public static VkShaderModuleValidationCacheCreateInfoEXT create(long address) {
        return new VkShaderModuleValidationCacheCreateInfoEXT(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkShaderModuleValidationCacheCreateInfoEXT createSafe(long address) {
        return address == NULL ? null : create(address);
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer malloc(int capacity) {
        return create(__malloc(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer calloc(int capacity) {
        return create(nmemCallocChecked(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated with {@link BufferUtils}.
     *
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer create(int capacity) {
        return new Buffer(__create(capacity, SIZEOF));
    }

    /**
     * Create a {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer create(long address, int capacity) {
        return new Buffer(address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : create(address, capacity);
    }

    // -----------------------------------

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated on the thread-local {@link MemoryStack}. */
    public static VkShaderModuleValidationCacheCreateInfoEXT mallocStack() {
        return mallocStack(stackGet());
    }

    /** Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero. */
    public static VkShaderModuleValidationCacheCreateInfoEXT callocStack() {
        return callocStack(stackGet());
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT mallocStack(MemoryStack stack) {
        return create(stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT callocStack(MemoryStack stack) {
        return create(stack.ncalloc(ALIGNOF, 1, SIZEOF));
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated on the thread-local {@link MemoryStack}.
     *
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer mallocStack(int capacity) {
        return mallocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer callocStack(int capacity) {
        return callocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer mallocStack(int capacity, MemoryStack stack) {
        return create(stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkShaderModuleValidationCacheCreateInfoEXT.Buffer callocStack(int capacity, MemoryStack stack) {
        return create(stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #sType}. */
    public static int nsType(long struct) { return memGetInt(struct + VkShaderModuleValidationCacheCreateInfoEXT.STYPE); }
    /** Unsafe version of {@link #pNext}. */
    public static long npNext(long struct) { return memGetAddress(struct + VkShaderModuleValidationCacheCreateInfoEXT.PNEXT); }
    /** Unsafe version of {@link #validationCache}. */
    public static long nvalidationCache(long struct) { return memGetLong(struct + VkShaderModuleValidationCacheCreateInfoEXT.VALIDATIONCACHE); }

    /** Unsafe version of {@link #sType(int) sType}. */
    public static void nsType(long struct, int value) { memPutInt(struct + VkShaderModuleValidationCacheCreateInfoEXT.STYPE, value); }
    /** Unsafe version of {@link #pNext(long) pNext}. */
    public static void npNext(long struct, long value) { memPutAddress(struct + VkShaderModuleValidationCacheCreateInfoEXT.PNEXT, value); }
    /** Unsafe version of {@link #validationCache(long) validationCache}. */
    public static void nvalidationCache(long struct, long value) { memPutLong(struct + VkShaderModuleValidationCacheCreateInfoEXT.VALIDATIONCACHE, value); }

    // -----------------------------------

    /** An array of {@link VkShaderModuleValidationCacheCreateInfoEXT} structs. */
    public static class Buffer extends StructBuffer<VkShaderModuleValidationCacheCreateInfoEXT, Buffer> implements NativeResource {

        /**
         * Creates a new {@link VkShaderModuleValidationCacheCreateInfoEXT.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link VkShaderModuleValidationCacheCreateInfoEXT#SIZEOF}, and its mark will be undefined.
         *
         * <p>The created buffer instance holds a strong reference to the container object.</p>
         */
        public Buffer(ByteBuffer container) {
            super(container, container.remaining() / SIZEOF);
        }

        public Buffer(long address, int cap) {
            super(address, null, -1, 0, cap, cap);
        }

        Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
            super(address, container, mark, pos, lim, cap);
        }

        @Override
        protected Buffer self() {
            return this;
        }

        @Override
        protected Buffer newBufferInstance(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
            return new Buffer(address, container, mark, pos, lim, cap);
        }

        @Override
        protected VkShaderModuleValidationCacheCreateInfoEXT newInstance(long address) {
            return new VkShaderModuleValidationCacheCreateInfoEXT(address, container);
        }

        @Override
        public int sizeof() {
            return SIZEOF;
        }

        /** Returns the value of the {@code sType} field. */
        @NativeType("VkStructureType")
        public int sType() { return VkShaderModuleValidationCacheCreateInfoEXT.nsType(address()); }
        /** Returns the value of the {@code pNext} field. */
        @NativeType("void const *")
        public long pNext() { return VkShaderModuleValidationCacheCreateInfoEXT.npNext(address()); }
        /** Returns the value of the {@code validationCache} field. */
        @NativeType("VkValidationCacheEXT")
        public long validationCache() { return VkShaderModuleValidationCacheCreateInfoEXT.nvalidationCache(address()); }

        /** Sets the specified value to the {@code sType} field. */
        public VkShaderModuleValidationCacheCreateInfoEXT.Buffer sType(@NativeType("VkStructureType") int value) { VkShaderModuleValidationCacheCreateInfoEXT.nsType(address(), value); return this; }
        /** Sets the specified value to the {@code pNext} field. */
        public VkShaderModuleValidationCacheCreateInfoEXT.Buffer pNext(@NativeType("void const *") long value) { VkShaderModuleValidationCacheCreateInfoEXT.npNext(address(), value); return this; }
        /** Sets the specified value to the {@code validationCache} field. */
        public VkShaderModuleValidationCacheCreateInfoEXT.Buffer validationCache(@NativeType("VkValidationCacheEXT") long value) { VkShaderModuleValidationCacheCreateInfoEXT.nvalidationCache(address(), value); return this; }

    }

}