package com.zhaops.mlchecksvc;

import com.zhaops.mlchecksvc.user.common.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author SoYuan
 */
@SpringBootApplication
public class MlchecksvcApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MlchecksvcApplication.class, args);

        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }

}
