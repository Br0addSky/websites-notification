package proj.websitesnotification.main.comparator_service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public interface HashTableComparatorService {
    List<String> getUrlsHtmlChanged(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);

    List<String> getUrlsHtmlNew(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);

    List<String> getUrlsHtmlDeleted(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);
}
