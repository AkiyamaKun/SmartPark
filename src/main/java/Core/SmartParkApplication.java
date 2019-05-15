package Core;

import Core.Controller.MVC.MessageController;
import Core.DTO.ResponseDTO;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmartParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartParkApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SmartParkApplication.class);
//    }
}

