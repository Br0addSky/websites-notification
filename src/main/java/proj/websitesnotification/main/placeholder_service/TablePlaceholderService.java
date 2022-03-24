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
     * Перезапись сегодняшней версии в вчерашнюю
     *
     * @param todayVersion версия страниц на сегодня
     */
    void updateYesterdayVersion(Map<String, String> todayVersion);
}
