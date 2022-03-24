package proj.websitesnotification.main.placeholder_service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import proj.websitesnotification.main.ApplicationProperties;
import proj.websitesnotification.main.placeholder_service.TablePlaceholderService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TablePlaceholderServiceImpl implements TablePlaceholderService {
    private final ApplicationProperties properties;

    private Map<String, String> yesterdayVersion = new HashMap<>();

    @Override
    public Map<String, String> getYesterdayVersion() {
        return yesterdayVersion;
    }

    @Override
    public Map<String, String> getTodayVersion() {
        File dir = new File(properties.getHtmlPlaceholder());
        List<File> lst = Arrays.asList(dir.listFiles());
        return lst.stream()
                .collect(Collectors.toMap(
                        file -> String.format("%s/%s", properties.getBaseUrl(), file.getName()),
                        file -> {
                            try {
                                return Files.readString(file.toPath());
                            } catch (IOException e) {
                                log.error("file by path {} not exists", file.getAbsolutePath());
                                return "";
                            }
                        }));

    }

    @Override
    public void updateYesterdayVersion(Map<String, String> todayVersion) {
        this.yesterdayVersion = todayVersion;
    }
}
