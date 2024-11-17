package com.org.microservice;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MicroserviceApplicationTests {

	private static final String LOCALHOST = "http://localhost:";

	@LocalServerPort
	private int port;

	@LocalManagementPort
	private int managementPort;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				LOCALHOST + this.managementPort + "/actuator", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturn200WhenSendingRequestToPrometheusManagementEndpoint() {
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				LOCALHOST + this.managementPort + "/actuator/prometheus", String.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
