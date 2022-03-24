package proj.websitesnotification.main.table_comparator;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.Set;

@Service
public interface HashTableComparatorService {
    Set<String> getUrlsHtmlChanged(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);

    Set<String> getUrlsHtmlNew(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);

    Set<String> getUrlsHtmlDeleted(Map<String, File> yesterdayVersion, Map<String, File> todayVersion);
}
