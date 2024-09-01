package com.acme.webdav_demo.sardine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SardineServiceIntegrationTest {
    // other port leads to test container creation timeouts ¯\_(ツ)_/¯
    public static final int ORIGINAL_PORT = 80;

    static Map<String, String> env = new HashMap<>();
    static {
        env.put("AUTH_TYPE", "Digest");
        env.put("USERNAME", "alice");
        env.put("PASSWORD", "bob");
    }

    @Container
    private static final GenericContainer<?> webdavContainer =
            new GenericContainer<>(DockerImageName.parse("bytemark/webdav"))
                    .withExposedPorts(ORIGINAL_PORT)
                    .withEnv(env);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("webdav.port", () -> webdavContainer.getMappedPort(ORIGINAL_PORT));
    }

    @Autowired
    SardineService sardineService;

    @Test
    void testSardineServiceByPutAndGetContent() {
        // Arrange
        String filename = "test.txt";
        String fileContent = "This is Sparta!";

        // Act
        sardineService.putFileWithContent(filename, fileContent);
        String savedFileContent = sardineService.getFileContent(filename);

        // Assert
        assertEquals(fileContent, savedFileContent);
    }

}
