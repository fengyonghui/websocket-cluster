package yh.study.websocket.cluster.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Properties;

/**
 * @author lawrence
 * @since 2021/3/21
 */
@ConfigurationProperties(prefix = "websocket")
@Configuration
@Data
public class WebSocketProperties extends Properties {

    private static final long serialVersionUID = -7568982050310381466L;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServerAddress;

    @Value("${spring.cloud.nacos.discovery.namespace}")
    private String nacosNamespace;

    public String getNacosServerAddress() {
        return nacosServerAddress;
    }

    public void setNacosServerAddress(String nacosServerAddress) {
        this.nacosServerAddress = nacosServerAddress;
    }

    public String getNacosNamespace() {
        return nacosNamespace;
    }

    public void setNacosNamespace(String nacosNamespace) {
        this.nacosNamespace = nacosNamespace;
    }

    @Autowired
    private Service service;
    @Autowired
    private Docker docker;

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WebSocketProperties that = (WebSocketProperties) o;
        return Objects.equals(nacosServerAddress, that.nacosServerAddress) && Objects.equals(nacosNamespace, that.nacosNamespace) && Objects.equals(service, that.service) && Objects.equals(docker, that.docker);
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(super.hashCode(), nacosServerAddress, nacosNamespace, service, docker);
    }
}
