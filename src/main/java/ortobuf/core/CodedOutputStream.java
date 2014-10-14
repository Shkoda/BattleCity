package ortobuf.core;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Artem Korotenko
 * Date: 04.03.13
 * Time: 12:16
 */
public final class CodedOutputStream {

    private byte[] buffer;
    public int pointer;
    private int bufferSize;

    public CodedOutputStream(int initialBufferSize) {
        this.buffer = new byte[initialBufferSize];
        bufferSize = initialBufferSize;
    }

    public void reset() {
        pointer = 0;
    }


    public void writeBytes(byte[] bytes) {
        if (pointer + bytes.length > buffer.length) {
            refreshBuffer(Math.max(pointer + bytes.length, buffer.length * 2));
        }
        System.arraycopy(bytes, 0, buffer, pointer, bytes.length);
        pointer += bytes.length;
    }

    public void writeBoolean(boolean b) {
        writeRawByte((byte) (b ? 1 : 0));
    }


    public void writeFloat(float value) {
        writeRawLittleEndian32(Float.floatToRawIntBits(value));
    }

    /**
     * Write a single byte.
     */
    public void writeRawByte(final byte value) {
        if (pointer == bufferSize) {
            refreshBuffer(buffer.length * 2);
        }
        buffer[pointer++] = value;
    }

    /**
     * enlarge your buffer without SMS and registration
     */
    private void refreshBuffer(int newSize) {
        buffer = Arrays.copyOf(buffer, newSize);
        bufferSize = buffer.length;
    }


    public byte[] flush() {
        return Arrays.copyOf(buffer, pointer);
    }

    /**
     * Write an {@code int32} field to the stream.
     */
    public void writeInt32NoTag(final int value) {
        if (value >= 0) {
            writeRawVarint32(value);
        } else {
            // Must sign-extend.
            writeRawVarint64(value);
        }
    }

    /**
     * Write a little-endian 32-bit integer.
     */
    public void writeRawLittleEndian32(final int value) {
        writeRawByte((byte) ((value) & 0xFF));
        writeRawByte((byte) ((value >> 8) & 0xFF));
        writeRawByte((byte) ((value >> 16) & 0xFF));
        writeRawByte((byte) ((value >> 24) & 0xFF));
    }

    public static final int LITTLE_ENDIAN_32_SIZE = 4;

    /**
     * Write a little-endian 64-bit integer.
     */
    public void writeRawLittleEndian64(final long value) {
        writeRawByte((byte) ((int) (value) & 0xFF));
        writeRawByte((byte) ((int) (value >> 8) & 0xFF));
        writeRawByte((byte) ((int) (value >> 16) & 0xFF));
        writeRawByte((byte) ((int) (value >> 24) & 0xFF));
        writeRawByte((byte) ((int) (value >> 32) & 0xFF));
        writeRawByte((byte) ((int) (value >> 40) & 0xFF));
        writeRawByte((byte) ((int) (value >> 48) & 0xFF));
        writeRawByte((byte) ((int) (value >> 56) & 0xFF));
    }

    public void writeRawVarint32(int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                writeRawByte((byte) value);
                return;
            } else {
                writeRawByte((byte) ((value & 0x7F) | 0x80));
                value >>>= 7;
            }
        }
    }

    /**
     * Encode and write a varint.
     */
    public void writeRawVarint64(long value) {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                writeRawByte((byte) value);
                return;
            } else {
                writeRawByte((byte) (((byte) value & 0x7F) | 0x80));
                value >>>= 7;
            }
        }
    }


    public void writeString(String string) {
        try {
            byte[] utf8 = string.getBytes("UTF-8");
            int codedLength = utf8.length;
            writeInt32NoTag(codedLength);
            if (pointer + codedLength > buffer.length) {
                refreshBuffer(Math.max(pointer + utf8.length, buffer.length * 2));
            }
            System.arraycopy(utf8, 0, buffer, pointer, codedLength);
            pointer += utf8.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void merge(CodedOutputStream os) {
        if (pointer + os.pointer > buffer.length) {
            refreshBuffer(Math.max(pointer + os.pointer, buffer.length * 2));
        }
        System.arraycopy(os.buffer, 0, buffer, pointer, os.pointer);
        pointer += os.pointer;
    }
}
