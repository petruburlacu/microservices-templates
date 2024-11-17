package com.org.microservice.documents.repository;

import com.org.microservice.documents.model.Document;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentsRepository {

  public Document findById(@NonNull final String id) {
    return new Document(id, "Document", 0.0f);
  }

}
