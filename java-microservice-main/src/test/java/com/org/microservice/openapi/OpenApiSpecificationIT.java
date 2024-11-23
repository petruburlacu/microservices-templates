package com.org.microservice.openapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenApiSpecificationIT {

  private static final Logger logger = LoggerFactory.getLogger(OpenApiSpecificationIT.class);

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void generateOpenApiSpec() throws IOException {
    String url = "http://localhost:" + port + "/v3/api-docs.yaml";
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      saveToFile("specs/openapi.yaml", response.getBody());
    } else {
      logger.error("Failed to generate OpenAPI specification through swagger /v3/api-docs.yaml: {}", response.getStatusCode());
      throw new RuntimeException("Failed to fetch OpenAPI specification");
    }
  }

  private void saveToFile(String filePath, String content) throws IOException {
    File file = new File(filePath);
    Files.createDirectories(Paths.get(file.getParent()));
    try (FileWriter writer = new FileWriter(file)) {
      writer.write(content);
    }
    logger.info("OpenAPI specification saved to {}", filePath);
  }
}
