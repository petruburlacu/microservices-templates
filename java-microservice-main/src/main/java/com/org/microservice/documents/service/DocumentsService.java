package com.org.microservice.documents.service;

import com.org.microservice.documents.model.Document;
import com.org.microservice.documents.repository.DocumentsRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentsService {

  private final DocumentsRepository documentsRepository;
  private final MeterRegistry meterRegistry;

  @SneakyThrows // Lombok annotation to avoid throwing checked exceptions, for demo purposes only
  public Document getDocumentsById(@NonNull final String id) {
    log.info("Getting document by id: {}", id);
    Tag idTag = Tag.of("id", id);
    Timer.Sample timer = Timer.start(meterRegistry);

    if (id.equals("2")) {
     Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
    }

    timer.stop(Timer.builder("service_documents_getById")
        .description("Document searching timer")
        .tags(List.of(idTag))
        .register(meterRegistry));
    final var document = documentsRepository.findById(id);
    log.info("Document found: {} with size {}", document.getId(), document.getSize());
    return document;
  }
}
