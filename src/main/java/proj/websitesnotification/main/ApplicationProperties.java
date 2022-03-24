package proj.websitesnotification.main;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "website")
public class ApplicationProperties {

    private String baseUrl;

    private String htmlPlaceholder;

    private String fullName;

    private String emailRecipient;
}
