package ortobuf.core;

/**
 * Created with IntelliJ IDEA.
 * User: Artem Korotenko
 * Date: 11.03.13
 * Time: 11:23
 */
public abstract class AbstractMessageBuilder {

    protected CodedOutputStream os;

    public CodedOutputStream getOutputStream() {
        return os;
    }

    public byte[] getBytes() {
        return os.flush();
    }

    protected AbstractMessageBuilder(int size) {
        this.os = new CodedOutputStream(size);
    }
}
