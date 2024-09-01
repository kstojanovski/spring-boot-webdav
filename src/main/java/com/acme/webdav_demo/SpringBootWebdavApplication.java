package com.acme.webdav_demo;

import com.acme.webdav_demo.webdav.WebDavProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WebDavProperties.class)
public class SpringBootWebdavApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebdavApplication.class, args);
	}

}
