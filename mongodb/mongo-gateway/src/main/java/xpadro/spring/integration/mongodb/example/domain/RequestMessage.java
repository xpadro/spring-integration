package xpadro.spring.integration.mongodb.example.domain;

/**
 * @author Xavier Padró
 */
public class RequestMessage {
    private String data;

    public RequestMessage() { }

    public RequestMessage(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
