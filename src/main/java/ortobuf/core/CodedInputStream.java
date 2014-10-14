package ortobuf.core;


import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Artem Korotenko
 * Date: 04.03.13
 * Time: 10:52
 */
public final class CodedInputStream {

    private byte[] buffer;
    private int bufferSize;
    private int pointer;
    private int lastTag;
    public int limit = -1;

    public CodedInputStream(byte[] buffer) {
        this.buffer = buffer;
        this.bufferSize = buffer.length;
    }

    public String readString() throws IOException {
        int size = readRawVarint32();
        try {
            String result = new String(buffer, pointer, size);
            pointer += size;
            return result;
        } catch (Exception e) {
            throw InvalidProtocolBufferException.stringError();
        }
    }

    public byte[] readBytes() throws IOException {
        int size = readRawVarint32();
        try {
            byte[] result = new byte[size];
            System.arraycopy(buffer, pointer, result, 0, size);
            pointer += size;

            return result;
        } catch (Exception e) {
            throw InvalidProtocolBufferException.stringError();
        }
    }

    public boolean readBoolean() throws IOException {
        return readRawVarint32() != 0;
    }

    public float readFoat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public void readLimit() throws IOException {
        limit = readRawVarint32() + pointer;
    }


    public byte readRawByte() throws IOException {
        if (pointer == bufferSize) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return buffer[pointer++];
    }

    public int readTag() throws IOException {
        if (pointer == bufferSize) return 0;
        if (pointer == limit) {
            limit = -1;
            return 0;
        }
        lastTag = readRawVarint32();
//        if (WireFormat.getTagFieldNumber(lastTag) == 0) {
//            // If we actually read zero (or any tag number corresponding to field
//            // number zero), that's not a valid tag.
//            throw InvalidProtocolBufferException.invalidTag();
//        }
        return lastTag;
    }


    /**
     * Read a raw Varint32 from the stream.
     */
    public int readRawVarint32() throws IOException {
        byte tmp = readRawByte();
        if (tmp >= 0) {
            return tmp;
        }
        int result = tmp & 0x7f;
        if ((tmp = readRawByte()) >= 0) {
            result |= tmp << 7;
        } else {
            result |= (tmp & 0x7f) << 7;
            if ((tmp = readRawByte()) >= 0) {
                result |= tmp << 14;
            } else {
                result |= (tmp & 0x7f) << 14;
                if ((tmp = readRawByte()) >= 0) {
                    result |= tmp << 21;
                } else {
                    result |= (tmp & 0x7f) << 21;
                    result |= (tmp = readRawByte()) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (readRawByte() >= 0) {
                                return result;
                            }
                        }
                        throw InvalidProtocolBufferException.malformedVarint();
                    }
                }
            }
        }
        return result;
    }

    /**
     * Read a raw Varint64 from the stream.
     */
    public long readRawVarint64() throws IOException {
        int shift = 0;
        long result = 0;
        while (shift < 64) {
            final byte b = readRawByte();
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
            shift += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    /**
     * Read a 32-bit little-endian integer from the stream.
     */
    public int readRawLittleEndian32() throws IOException {
        final byte b1 = readRawByte();
        final byte b2 = readRawByte();
        final byte b3 = readRawByte();
        final byte b4 = readRawByte();
        return (((int) b1 & 0xff)) |
                (((int) b2 & 0xff) << 8) |
                (((int) b3 & 0xff) << 16) |
                (((int) b4 & 0xff) << 24);
    }

    /**
     * Read a 64-bit little-endian integer from the stream.
     */
    public long readRawLittleEndian64() throws IOException {
        final byte b1 = readRawByte();
        final byte b2 = readRawByte();
        final byte b3 = readRawByte();
        final byte b4 = readRawByte();
        final byte b5 = readRawByte();
        final byte b6 = readRawByte();
        final byte b7 = readRawByte();
        final byte b8 = readRawByte();
        return (((long) b1 & 0xff)) |
                (((long) b2 & 0xff) << 8) |
                (((long) b3 & 0xff) << 16) |
                (((long) b4 & 0xff) << 24) |
                (((long) b5 & 0xff) << 32) |
                (((long) b6 & 0xff) << 40) |
                (((long) b7 & 0xff) << 48) |
                (((long) b8 & 0xff) << 56);
    }
}
