package com.acme.webdav_demo;

import com.acme.webdav_demo.webdav.WebDavProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

@TestConfiguration(proxyBeanMethods = false)
public class WebDavContainerConfiguration {

    @Autowired
    WebDavProperties webDavProperties;

    @Bean
    Container<?> webdavContainer() {
        Map<String, String> env = new HashMap<>();
        env.put("AUTH_TYPE", webDavProperties.getAuthType());
        env.put("USERNAME", webDavProperties.getUsername());
        env.put("PASSWORD", webDavProperties.getPassword());

        return new GenericContainer<>(DockerImageName.parse("bytemark/webdav"))
                .withExposedPorts(90)
                .withEnv(env);

    }
}
