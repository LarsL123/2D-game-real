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

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

/**
 * Structure specifying sparse image memory bind info.
 * 
 * <h5>Valid Usage</h5>
 * 
 * <ul>
 * <li>The {@code subresource.mipLevel} member of each element of {@code pBinds} <b>must</b> be less than the {@code mipLevels} specified in {@link VkImageCreateInfo} when {@code image} was created</li>
 * <li>The {@code subresource.arrayLayer} member of each element of {@code pBinds} <b>must</b> be less than the {@code arrayLayers} specified in {@link VkImageCreateInfo} when {@code image} was created</li>
 * </ul>
 * 
 * <h5>Valid Usage (Implicit)</h5>
 * 
 * <ul>
 * <li>{@code image} <b>must</b> be a valid {@code VkImage} handle</li>
 * <li>{@code pBinds} <b>must</b> be a valid pointer to an array of {@code bindCount} valid {@link VkSparseImageMemoryBind} structures</li>
 * <li>{@code bindCount} <b>must</b> be greater than 0</li>
 * </ul>
 * 
 * <h5>See Also</h5>
 * 
 * <p>{@link VkBindSparseInfo}, {@link VkSparseImageMemoryBind}</p>
 * 
 * <h3>Member documentation</h3>
 * 
 * <ul>
 * <li>{@code image} &ndash; the {@code VkImage} object to be bound</li>
 * <li>{@code bindCount} &ndash; the number of {@link VkSparseImageMemoryBind} structures in pBinds array</li>
 * <li>{@code pBinds} &ndash; a pointer to array of {@link VkSparseImageMemoryBind} structures</li>
 * </ul>
 * 
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct VkSparseImageMemoryBindInfo {
 *     VkImage image;
 *     uint32_t bindCount;
 *     {@link VkSparseImageMemoryBind VkSparseImageMemoryBind const} * pBinds;
 * }</code></pre>
 */
public class VkSparseImageMemoryBindInfo extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        IMAGE,
        BINDCOUNT,
        PBINDS;

    static {
        Layout layout = __struct(
            __member(8),
            __member(4),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        IMAGE = layout.offsetof(0);
        BINDCOUNT = layout.offsetof(1);
        PBINDS = layout.offsetof(2);
    }

    VkSparseImageMemoryBindInfo(long address, @Nullable ByteBuffer container) {
        super(address, container);
    }

    /**
     * Creates a {@link VkSparseImageMemoryBindInfo} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public VkSparseImageMemoryBindInfo(ByteBuffer container) {
        this(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** Returns the value of the {@code image} field. */
    @NativeType("VkImage")
    public long image() { return nimage(address()); }
    /** Returns the value of the {@code bindCount} field. */
    @NativeType("uint32_t")
    public int bindCount() { return nbindCount(address()); }
    /** Returns a {@link VkSparseImageMemoryBind.Buffer} view of the struct array pointed to by the {@code pBinds} field. */
    @NativeType("VkSparseImageMemoryBind const *")
    public VkSparseImageMemoryBind.Buffer pBinds() { return npBinds(address()); }

    /** Sets the specified value to the {@code image} field. */
    public VkSparseImageMemoryBindInfo image(@NativeType("VkImage") long value) { nimage(address(), value); return this; }
    /** Sets the address of the specified {@link VkSparseImageMemoryBind.Buffer} to the {@code pBinds} field. */
    public VkSparseImageMemoryBindInfo pBinds(@NativeType("VkSparseImageMemoryBind const *") VkSparseImageMemoryBind.Buffer value) { npBinds(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public VkSparseImageMemoryBindInfo set(
        long image,
        VkSparseImageMemoryBind.Buffer pBinds
    ) {
        image(image);
        pBinds(pBinds);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public VkSparseImageMemoryBindInfo set(VkSparseImageMemoryBindInfo src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static VkSparseImageMemoryBindInfo malloc() {
        return create(nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static VkSparseImageMemoryBindInfo calloc() {
        return create(nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated with {@link BufferUtils}. */
    public static VkSparseImageMemoryBindInfo create() {
        return new VkSparseImageMemoryBindInfo(BufferUtils.createByteBuffer(SIZEOF));
    }

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance for the specified memory address. */
    public static VkSparseImageMemoryBindInfo create(long address) {
        return new VkSparseImageMemoryBindInfo(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkSparseImageMemoryBindInfo createSafe(long address) {
        return address == NULL ? null : create(address);
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer malloc(int capacity) {
        return create(__malloc(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer calloc(int capacity) {
        return create(nmemCallocChecked(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated with {@link BufferUtils}.
     *
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer create(int capacity) {
        return new Buffer(__create(capacity, SIZEOF));
    }

    /**
     * Create a {@link VkSparseImageMemoryBindInfo.Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer create(long address, int capacity) {
        return new Buffer(address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkSparseImageMemoryBindInfo.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : create(address, capacity);
    }

    // -----------------------------------

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated on the thread-local {@link MemoryStack}. */
    public static VkSparseImageMemoryBindInfo mallocStack() {
        return mallocStack(stackGet());
    }

    /** Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero. */
    public static VkSparseImageMemoryBindInfo callocStack() {
        return callocStack(stackGet());
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static VkSparseImageMemoryBindInfo mallocStack(MemoryStack stack) {
        return create(stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static VkSparseImageMemoryBindInfo callocStack(MemoryStack stack) {
        return create(stack.ncalloc(ALIGNOF, 1, SIZEOF));
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated on the thread-local {@link MemoryStack}.
     *
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer mallocStack(int capacity) {
        return mallocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer callocStack(int capacity) {
        return callocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer mallocStack(int capacity, MemoryStack stack) {
        return create(stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkSparseImageMemoryBindInfo.Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkSparseImageMemoryBindInfo.Buffer callocStack(int capacity, MemoryStack stack) {
        return create(stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #image}. */
    public static long nimage(long struct) { return memGetLong(struct + VkSparseImageMemoryBindInfo.IMAGE); }
    /** Unsafe version of {@link #bindCount}. */
    public static int nbindCount(long struct) { return memGetInt(struct + VkSparseImageMemoryBindInfo.BINDCOUNT); }
    /** Unsafe version of {@link #pBinds}. */
    public static VkSparseImageMemoryBind.Buffer npBinds(long struct) { return VkSparseImageMemoryBind.create(memGetAddress(struct + VkSparseImageMemoryBindInfo.PBINDS), nbindCount(struct)); }

    /** Unsafe version of {@link #image(long) image}. */
    public static void nimage(long struct, long value) { memPutLong(struct + VkSparseImageMemoryBindInfo.IMAGE, value); }
    /** Sets the specified value to the {@code bindCount} field of the specified {@code struct}. */
    public static void nbindCount(long struct, int value) { memPutInt(struct + VkSparseImageMemoryBindInfo.BINDCOUNT, value); }
    /** Unsafe version of {@link #pBinds(VkSparseImageMemoryBind.Buffer) pBinds}. */
    public static void npBinds(long struct, VkSparseImageMemoryBind.Buffer value) { memPutAddress(struct + VkSparseImageMemoryBindInfo.PBINDS, value.address()); nbindCount(struct, value.remaining()); }

    /**
     * Validates pointer members that should not be {@code NULL}.
     *
     * @param struct the struct to validate
     */
    public static void validate(long struct) {
        check(memGetAddress(struct + VkSparseImageMemoryBindInfo.PBINDS));
    }

    /**
     * Calls {@link #validate(long)} for each struct contained in the specified struct array.
     *
     * @param array the struct array to validate
     * @param count the number of structs in {@code array}
     */
    public static void validate(long array, int count) {
        for (int i = 0; i < count; i++) {
            validate(array + i * SIZEOF);
        }
    }

    // -----------------------------------

    /** An array of {@link VkSparseImageMemoryBindInfo} structs. */
    public static class Buffer extends StructBuffer<VkSparseImageMemoryBindInfo, Buffer> implements NativeResource {

        /**
         * Creates a new {@link VkSparseImageMemoryBindInfo.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link VkSparseImageMemoryBindInfo#SIZEOF}, and its mark will be undefined.
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
        protected VkSparseImageMemoryBindInfo newInstance(long address) {
            return new VkSparseImageMemoryBindInfo(address, container);
        }

        @Override
        public int sizeof() {
            return SIZEOF;
        }

        /** Returns the value of the {@code image} field. */
        @NativeType("VkImage")
        public long image() { return VkSparseImageMemoryBindInfo.nimage(address()); }
        /** Returns the value of the {@code bindCount} field. */
        @NativeType("uint32_t")
        public int bindCount() { return VkSparseImageMemoryBindInfo.nbindCount(address()); }
        /** Returns a {@link VkSparseImageMemoryBind.Buffer} view of the struct array pointed to by the {@code pBinds} field. */
        @NativeType("VkSparseImageMemoryBind const *")
        public VkSparseImageMemoryBind.Buffer pBinds() { return VkSparseImageMemoryBindInfo.npBinds(address()); }

        /** Sets the specified value to the {@code image} field. */
        public VkSparseImageMemoryBindInfo.Buffer image(@NativeType("VkImage") long value) { VkSparseImageMemoryBindInfo.nimage(address(), value); return this; }
        /** Sets the address of the specified {@link VkSparseImageMemoryBind.Buffer} to the {@code pBinds} field. */
        public VkSparseImageMemoryBindInfo.Buffer pBinds(@NativeType("VkSparseImageMemoryBind const *") VkSparseImageMemoryBind.Buffer value) { VkSparseImageMemoryBindInfo.npBinds(address(), value); return this; }

    }

}