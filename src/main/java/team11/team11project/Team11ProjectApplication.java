package team11.team11project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Team11ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team11ProjectApplication.class, args);
    }

}
