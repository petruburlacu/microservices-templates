package com.org.microservice.documents.controller;

import com.org.microservice.documents.service.DocumentsService;
import com.org.model.Document;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentsController {

  private final DocumentsService documentsService;

  @GetMapping("/{id}")
  public ResponseEntity<Document> getDocumentById(
      @PathVariable @NotNull final String id) {

    final var document = documentsService.getDocumentsById(id);
    return ResponseEntity.ok(document);
  }
}
