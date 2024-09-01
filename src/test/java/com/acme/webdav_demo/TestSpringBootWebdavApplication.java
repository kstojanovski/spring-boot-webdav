package com.acme.webdav_demo;

import org.springframework.boot.SpringApplication;

public class TestSpringBootWebdavApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootWebdavApplication::main).with(WebDavContainerConfiguration.class).run(args);
	}

}
