package xpadro.spring.integration;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import xpadro.spring.integration.filter.LastModifiedFileFilter;
import xpadro.spring.integration.processor.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootApplication
public class FileReadDirectoryApplication {
	private static final String DIRECTORY = "/Users/xpadro/fileread/";

    public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(FileReadDirectoryApplication.class, args);

		createFiles();
	}

	private static void createFiles() throws IOException, InterruptedException {
		createFile("file1.txt", "content");
		createFile("file2.txt", "another file");
		appendFile("file1.txt", " modified");
	}

	@Bean
	public IntegrationFlow processFileFlow() {
		return IntegrationFlows
				.from("fileInputChannel")
				.transform(fileToStringTransformer())
				.handle("fileProcessor", "process").get();
	}

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

	@Bean
	@InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
	public MessageSource<File> fileReadingMessageSource() {
		CompositeFileListFilter<File> filters =new CompositeFileListFilter<>();
		filters.addFilter(new SimplePatternFileListFilter("*.txt"));
		filters.addFilter(new LastModifiedFileFilter());

		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setAutoCreateDirectory(true);
		source.setDirectory(new File(DIRECTORY));
		source.setFilter(filters);

		return source;
	}

	@Bean
	public FileToStringTransformer fileToStringTransformer() {
		return new FileToStringTransformer();
	}

	@Bean
	public FileProcessor fileProcessor() {
		return new FileProcessor();
	}

	private static void createFile(String fileName, String content) throws IOException, InterruptedException {
		writeFile(fileName, content, false);
	}

	private static void appendFile(String fileName, String content) throws IOException, InterruptedException {
		writeFile(fileName, content, true);
	}

	private static void writeFile(String fileName, String content, boolean append) throws IOException, InterruptedException {
		File newFile = new File(DIRECTORY + fileName);
		FileUtils.writeStringToFile(newFile, content, Charset.forName("UTF-8"), append);
		Thread.sleep(1000);
	}
}
