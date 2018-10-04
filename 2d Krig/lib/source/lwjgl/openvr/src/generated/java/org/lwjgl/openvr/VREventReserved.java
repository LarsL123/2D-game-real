/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.openvr;

import javax.annotation.*;

import java.nio.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.MemoryUtil.*;

/**
 * Not actually used for any events.
 * 
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct VREvent_Reserved_t {
 *     uint64_t reserved0;
 *     uint64_t reserved1;
 *     uint64_t reserved2;
 *     uint64_t reserved3;
 * }</code></pre>
 */
@NativeType("struct VREvent_Reserved_t")
public class VREventReserved extends Struct {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        RESERVED0,
        RESERVED1,
        RESERVED2,
        RESERVED3;

    static {
        Layout layout = __struct(
            __member(8),
            __member(8),
            __member(8),
            __member(8)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        RESERVED0 = layout.offsetof(0);
        RESERVED1 = layout.offsetof(1);
        RESERVED2 = layout.offsetof(2);
        RESERVED3 = layout.offsetof(3);
    }

    VREventReserved(long address, @Nullable ByteBuffer container) {
        super(address, container);
    }

    /**
     * Creates a {@link VREventReserved} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public VREventReserved(ByteBuffer container) {
        this(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** Returns the value of the {@code reserved0} field. */
    @NativeType("uint64_t")
    public long reserved0() { return nreserved0(address()); }
    /** Returns the value of the {@code reserved1} field. */
    @NativeType("uint64_t")
    public long reserved1() { return nreserved1(address()); }
    /** Returns the value of the {@code reserved2} field. */
    @NativeType("uint64_t")
    public long reserved2() { return nreserved2(address()); }
    /** Returns the value of the {@code reserved3} field. */
    @NativeType("uint64_t")
    public long reserved3() { return nreserved3(address()); }

    // -----------------------------------

    /** Returns a new {@link VREventReserved} instance for the specified memory address. */
    public static VREventReserved create(long address) {
        return new VREventReserved(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VREventReserved createSafe(long address) {
        return address == NULL ? null : create(address);
    }

    /**
     * Create a {@link VREventReserved.Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static VREventReserved.Buffer create(long address, int capacity) {
        return new Buffer(address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VREventReserved.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : create(address, capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #reserved0}. */
    public static long nreserved0(long struct) { return memGetLong(struct + VREventReserved.RESERVED0); }
    /** Unsafe version of {@link #reserved1}. */
    public static long nreserved1(long struct) { return memGetLong(struct + VREventReserved.RESERVED1); }
    /** Unsafe version of {@link #reserved2}. */
    public static long nreserved2(long struct) { return memGetLong(struct + VREventReserved.RESERVED2); }
    /** Unsafe version of {@link #reserved3}. */
    public static long nreserved3(long struct) { return memGetLong(struct + VREventReserved.RESERVED3); }

    // -----------------------------------

    /** An array of {@link VREventReserved} structs. */
    public static class Buffer extends StructBuffer<VREventReserved, Buffer> {

        /**
         * Creates a new {@link VREventReserved.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link VREventReserved#SIZEOF}, and its mark will be undefined.
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
        protected VREventReserved newInstance(long address) {
            return new VREventReserved(address, container);
        }

        @Override
        public int sizeof() {
            return SIZEOF;
        }

        /** Returns the value of the {@code reserved0} field. */
        @NativeType("uint64_t")
        public long reserved0() { return VREventReserved.nreserved0(address()); }
        /** Returns the value of the {@code reserved1} field. */
        @NativeType("uint64_t")
        public long reserved1() { return VREventReserved.nreserved1(address()); }
        /** Returns the value of the {@code reserved2} field. */
        @NativeType("uint64_t")
        public long reserved2() { return VREventReserved.nreserved2(address()); }
        /** Returns the value of the {@code reserved3} field. */
        @NativeType("uint64_t")
        public long reserved3() { return VREventReserved.nreserved3(address()); }

    }

}