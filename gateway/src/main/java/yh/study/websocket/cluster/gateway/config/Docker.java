package yh.study.websocket.cluster.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@ConfigurationProperties(prefix = "websocket.docker")
@Configuration
@Data
public class Docker implements Serializable {

    private static final long serialVersionUID = -2233645916767230952L;

    private String host;

    private String network;

    private Image image;

    public static class Image implements Serializable {

        private static final long serialVersionUID = 648412373970515185L;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
