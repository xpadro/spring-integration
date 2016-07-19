package xpadro.spring.integration.filter;

import org.springframework.integration.file.filters.AbstractFileListFilter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LastModifiedFileFilter extends AbstractFileListFilter<File> {
    private final Map<String, Long> files = new HashMap<>();
    private final Object monitor = new Object();

    @Override
    protected boolean accept(File file) {
        synchronized (this.monitor) {
            Long previousModifiedTime = files.put(file.getName(), file.lastModified());

            return previousModifiedTime == null || previousModifiedTime != file.lastModified();
        }
    }
}
