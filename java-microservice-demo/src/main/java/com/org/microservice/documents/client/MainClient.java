package com.org.microservice.documents.client;

import com.org.api.DocumentsApi;
import com.org.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainClient {

  private final DocumentsApi documentsApi;

  public Document fetchById(@NonNull final String id) {
    return documentsApi.getDocumentById(id);
  }
}
