package proj.websitesnotification.main.comparator_service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HashTableComparatorService {
    /**
     * Проверяет изменились ли страницы
     *
     * @param yesterdayVersion вчерашняя версия
     * @param todayVersion     сегодняшняя версия
     * @return список измененных страниц
     */
    List<String> getUrlsHtmlChanged(Map<String, String> yesterdayVersion, Map<String, String> todayVersion);

    /**
     * Проверяет добавились ли новые страницы
     *
     * @param yesterdayVersion вчерашняя версия
     * @param todayVersion     сегодняшняя версия
     * @return список созданных страниц
     */
    List<String> getUrlsHtmlNew(Map<String, String> yesterdayVersion, Map<String, String> todayVersion);

    /**
     * Проверяет были ли удалены страницы
     *
     * @param yesterdayVersion вчерашняя версия
     * @param todayVersion     сегодняшняя версия
     * @return список удаленных страниц
     */
    List<String> getUrlsHtmlDeleted(Map<String, String> yesterdayVersion, Map<String, String> todayVersion);
}
