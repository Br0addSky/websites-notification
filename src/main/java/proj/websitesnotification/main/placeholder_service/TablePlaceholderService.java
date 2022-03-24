package proj.websitesnotification.main.placeholder_service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public interface TablePlaceholderService {
    Map<String, File> getYesterdayVersion();

    Map<String, File> getTodayVersion();

    void updateVersions();
}
