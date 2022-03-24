package proj.websitesnotification.main.comparator_service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proj.websitesnotification.main.comparator_service.HashTableComparatorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTableComparatorServiceImplTest {

    private HashTableComparatorService service;
    private final Map<String, String> todayVersion = new HashMap<>();
    private final Map<String, String> yesterdayVersion = new HashMap<>();


    @BeforeEach
    void init() {
        service = new HashTableComparatorServiceImpl();
        todayVersion.put("url1", "some-tags");
        todayVersion.put("url2", "some-tags");
        todayVersion.put("url4", "some-tags");
        yesterdayVersion.put("url1", "some-tags");
        yesterdayVersion.put("url2", "some-tag");
        yesterdayVersion.put("url3", "some-tags");

    }

    @Test
    void getUrlsHtmlChanged() {
        List<String> mbChanged = service.getUrlsHtmlChanged(yesterdayVersion, todayVersion);
        List<String> realChanged = List.of("url2");
        assertEquals(mbChanged, realChanged);
    }

    @Test
    void getUrlsHtmlNew() {
        List<String> mbChanged = service.getUrlsHtmlNew(yesterdayVersion, todayVersion);
        List<String> realChanged = List.of("url4");
        assertEquals(mbChanged, realChanged);
    }

    @Test
    void getUrlsHtmlDeleted() {
        List<String> mbChanged = service.getUrlsHtmlDeleted(yesterdayVersion, todayVersion);
        List<String> realChanged = List.of("url3");
        assertEquals(mbChanged, realChanged);
    }
}