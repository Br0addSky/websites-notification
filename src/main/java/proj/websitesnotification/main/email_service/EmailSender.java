package proj.websitesnotification.main.email_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import proj.websitesnotification.main.ApplicationProperties;
import proj.websitesnotification.main.comparator_service.HashTableComparatorService;
import proj.websitesnotification.main.placeholder_service.TablePlaceholderService;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSender {
    private final HashTableComparatorService comparatorService;
    private final ApplicationProperties properties;
    private final TablePlaceholderService placeholderService;
    private final JavaMailSender mailSender;

    private static final String SUBJECT = "Сегодняшние изменения";

    @Scheduled(cron = "${send-email-cron}")
    public void sendEmailMessage() {
        Map<String, String> todayVersion = placeholderService.getTodayVersion();
        Map<String, String> yesterdayVersion = placeholderService.getYesterdayVersion();
        String text = buildMessage(comparatorService.getUrlsHtmlChanged(yesterdayVersion, todayVersion),
                comparatorService.getUrlsHtmlNew(yesterdayVersion, todayVersion), comparatorService.getUrlsHtmlDeleted(yesterdayVersion, todayVersion));
        sendMessage(new String[]{properties.getEmailRecipient()}, SUBJECT, text);
        placeholderService.updateYesterdayVersion(todayVersion);
    }

    private String buildMessage(List<String> changed, List<String> added, List<String> deleted) {
        String greetingMessage = String.format("Здравствуйте, дорогая %s", properties.getFullName());
        String bodyMessage = String.format("За последние сутки во вверенных Вам сайтах произошли следующие изменения:%nИсчезли следующие страницы: %s%nПоявились следующие новые страницы: %s%nИзменились следующие страницы: %s",
                deleted, added, changed);

        String lastWords = "С уважением, \n автоматизированная система \n мониторинга.";
        return String.format("%s%n%s%n%s", greetingMessage, bodyMessage, lastWords);
    }

    private void sendMessage(String[] to, String subject, String text) throws MailSendException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
