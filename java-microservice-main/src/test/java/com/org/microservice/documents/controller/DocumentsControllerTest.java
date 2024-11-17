package com.org.microservice.documents.controller;

import static org.assertj.core.api.BDDAssertions.then;

import com.org.microservice.documents.model.Document;
import com.org.microservice.documents.service.DocumentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentsControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @MockBean
  private DocumentsService documentsService;

  @Test
  public void shouldReturn200WhenSendingRequestToControllerEndpoint() {
    // Arrange
    String documentId = "1";
    Document mockDocument = new Document("1", "Sample Document", 0);
    given(documentsService.getDocumentsById(documentId)).willReturn(mockDocument);

    String url = "http://localhost:" + this.port + "/api/documents/" + documentId;

    ResponseEntity<Document> entity = this.testRestTemplate.getForEntity(url, Document.class);

    then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    then(entity.getBody()).isNotNull();
    then(entity.getBody().getId()).isEqualTo(documentId);
    then(entity.getBody().getName()).isEqualTo("Sample Document");
  }
}