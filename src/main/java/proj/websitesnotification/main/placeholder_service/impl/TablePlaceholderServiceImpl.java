package proj.websitesnotification.main.placeholder_service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import proj.websitesnotification.main.ApplicationProperties;
import proj.websitesnotification.main.placeholder_service.TablePlaceholderService;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor
public class TablePlaceholderServiceImpl implements TablePlaceholderService {
    private final ApplicationProperties properties;

    private Map<String, File> yesterdayVersion;
    private Map<String, File> todayVersion;

    @Override
    public void updateVersions() {
        this.yesterdayVersion = this.todayVersion;
        File dir = new File(properties.getHtmlPlaceholder());
        List<File> lst = Arrays.asList(dir.listFiles());
        this.todayVersion = lst.stream().collect(Collectors.toMap(file ->
                        String.format("%s/%s", properties.getBaseUrl(), file.getName()),
                file -> file));
    }
}
