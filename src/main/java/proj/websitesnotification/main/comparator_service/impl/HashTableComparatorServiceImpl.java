package proj.websitesnotification.main.comparator_service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import proj.websitesnotification.main.ApplicationProperties;
import proj.websitesnotification.main.comparator_service.HashTableComparatorService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class HashTableComparatorServiceImpl implements HashTableComparatorService {
    private final ApplicationProperties properties;

    @Override
    public List<String> getUrlsHtmlChanged(Map<String, File> yesterdayVersion, Map<String, File> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return yesterdayUrls.stream()
                .filter(url -> todayUrls.contains(url) &&
                        hasFilesChanged(yesterdayVersion.get(url), todayVersion.get(url)))
                .collect(Collectors.toList());
    }

    private boolean hasFilesChanged(File yesterdayFile, File todayFile) {
        try {
            String yesterdayFileText = FileUtils.readFileToString(yesterdayFile, properties.getHtmlEncodingType());
            String todayFileText = FileUtils.readFileToString(todayFile, properties.getHtmlEncodingType());
            return yesterdayFileText.hashCode() == todayFileText.hashCode();
        } catch (IOException e) {
            log.error("File by path {} not exists", yesterdayFile.getAbsolutePath());
            return false;
        }
    }

    @Override
    public List<String> getUrlsHtmlNew(Map<String, File> yesterdayVersion, Map<String, File> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return todayUrls.stream().filter(url -> !yesterdayUrls.contains(url)).collect(Collectors.toList());
    }

    @Override
    public List<String> getUrlsHtmlDeleted(Map<String, File> yesterdayVersion, Map<String, File> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return yesterdayUrls.stream().filter(url -> !todayUrls.contains(url)).collect(Collectors.toList());
    }
}
