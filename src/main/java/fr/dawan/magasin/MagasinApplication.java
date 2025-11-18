package fr.dawan.magasin;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@SpringBootApplication
public class MagasinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagasinApplication.class, args);
//        SpringApplication app = new SpringApplication(MagasinApplication.class);
//        app.setBannerMode(Mode.OFF);
//        app.run(args);


    }
}
