package xpadro.spring.integration.processor;

import org.springframework.messaging.Message;

public class FileProcessor {
    private static final String HEADER_FILE_NAME = "file_name";
    private static final String MSG = "%s received. Content: %s";

    public void process(Message<String> msg) {
        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        String content = msg.getPayload();

        System.out.println(String.format(MSG, fileName, content));
    }
}
