package proj.websitesnotification.main.comparator_service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import proj.websitesnotification.main.comparator_service.HashTableComparatorService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class HashTableComparatorServiceImpl implements HashTableComparatorService {

    @Override
    public List<String> getUrlsHtmlChanged(Map<String, String> yesterdayVersion, Map<String, String> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return yesterdayUrls.stream()
                .filter(url -> todayUrls.contains(url) &&
                        !yesterdayVersion.get(url).equals(todayVersion.get(url)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<String> getUrlsHtmlNew(Map<String, String> yesterdayVersion, Map<String, String> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return todayUrls.stream().filter(url -> !yesterdayUrls.contains(url)).collect(Collectors.toList());
    }

    @Override
    public List<String> getUrlsHtmlDeleted(Map<String, String> yesterdayVersion, Map<String, String> todayVersion) {
        Set<String> yesterdayUrls = yesterdayVersion.keySet();
        Set<String> todayUrls = todayVersion.keySet();
        return yesterdayUrls.stream().filter(url -> !todayUrls.contains(url)).collect(Collectors.toList());
    }
}
