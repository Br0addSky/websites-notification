package proj.websitesnotification.main.placeholder_service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TablePlaceholderService {
    /**
     * @return вчерашняя версия страниц
     */
    Map<String, String> getYesterdayVersion();

    /**
     * @return сегодняшняя версия страниц
     */
    Map<String, String> getTodayVersion();

    /**
     * Обновление таблиц
     */
    void updateVersions();
}
