package proj.websitesnotification.main.placeholder_service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proj.websitesnotification.main.ApplicationProperties;
import proj.websitesnotification.main.placeholder_service.TablePlaceholderService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TablePlaceholderServiceImplTest {
    private TablePlaceholderService service;

    @BeforeEach
    void init() {
        ApplicationProperties properties = new ApplicationProperties();
        properties.setHtmlPlaceholder("./src/test/java/proj/websitesnotification/main/placeholder_service/impl/test-files");
        properties.setBaseUrl("url");
        service = new TablePlaceholderServiceImpl(properties);
    }

    @Test
    void getTodayVersion() {
        Map<String, String> todayVersion = service.getTodayVersion();
        assertTrue(todayVersion.containsKey("url/test-file.html"));
    }
}