package proj.websitesnotification.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsitesNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsitesNotificationApplication.class, args);
	}

}
