package com.org.microservice.documents.service;

import com.org.microservice.documents.client.MainClient;
import com.org.model.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentsService {

  private final MainClient mainClient;

  public Document getDocumentsById(@NonNull final String id) {
    log.info("Getting document by id: {}", id);
    final var document = mainClient.fetchById(id);
    log.info("Document found: {} with size {}", document.getId(), document.getSize());
    return document;
  }
}
