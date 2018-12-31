package tech.overpass.conferwebforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferApp {

    public static final int POST_TITLE_MAX_LENGTH = 299;

    public static void main(String[] args) {
        SpringApplication.run(ConferApp.class, args);
    }
}
