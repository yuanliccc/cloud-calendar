package group.cc.pcc.server;

public class SimpleMessage<T> {

    private String infoType;

    private T content;

    public String getInfoType() {
        return infoType;
    }

    public SimpleMessage(String infoType, T content) {
        this.infoType = infoType;
        this.content = content;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
