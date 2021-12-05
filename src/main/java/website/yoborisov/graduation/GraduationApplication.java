package website.yoborisov.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GraduationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GraduationApplication.class, args);
    }

}
