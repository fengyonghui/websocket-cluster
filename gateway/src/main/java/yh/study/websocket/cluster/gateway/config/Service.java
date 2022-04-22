package yh.study.websocket.cluster.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
@ConfigurationProperties(prefix = "websocket.service")
@Configuration
@Data
public class Service implements Serializable {

    private static final long serialVersionUID = 4470917617306041628L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
