package ua.ubs.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import ua.ubs.schedule.security.SecurityConfig;

import java.util.TimeZone;

@SpringBootApplication
@Import({SecurityConfig.class})
public class ScheduleApplication {

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

}
