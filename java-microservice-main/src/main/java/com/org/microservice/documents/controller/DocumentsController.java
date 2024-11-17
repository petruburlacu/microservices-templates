package com.org.microservice.documents.controller;

import com.org.microservice.documents.model.Document;
import com.org.microservice.documents.service.DocumentsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentsController {

  private final MeterRegistry meterRegistry;
  private final DocumentsService documentsService;


  /**
   * GET /documents/{id} : This operation allows to retrieve a document by id
   * This allows a user to retrieve a document for a specific id
   *
   * @param id The particular id to search on (required)
   * @return The document with the given id (status code 200)
   *         or This means that the id passed was invalid (status code 400)
   *         or This means that no document exists for the id passed (status code 404)
   *         or Unknown error (status code 500)
   */
  @Operation(
      operationId = "getDocumentById",
      summary = "This operation allows to retrieve a document by id",
      description = "This allows a user to retrieve a document for a specific id",
      tags = { "query", "documents" },
      responses = {
          @ApiResponse(responseCode = "200", description = "The document with the given id", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))
          }),
          @ApiResponse(responseCode = "400", description = "This means that the id passed was invalid", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
          }),
          @ApiResponse(responseCode = "404", description = "This means that no document exists for the id passed", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
          }),
          @ApiResponse(responseCode = "default", description = "Unknown error", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
          })
      }
  )
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<Document> getDocumentById(
      @Parameter(name = "id", description = "The particular id to search on", required = true, in = ParameterIn.PATH) @PathVariable("id") @NotNull final String id) {

    Counter counter = Counter.builder("api_documents_getById")
        .tag("id", id)
        .description("The number of times the /api/documents/{id} end-point is called")
        .register(meterRegistry);
    counter.increment();

    final var document = documentsService.getDocumentsById(id);
    return ResponseEntity.ok(document);
  }

  @Hidden // This operation is hidden from the API documentation
  @PutMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<Document> updateDocumentById(
      @Parameter(name = "id", description = "The particular id to search on", required = true, in = ParameterIn.PATH) @PathVariable("id") @NotNull final String id) {

    Counter counter = Counter.builder("api_documents_updateById")
        .tag("id", id)
        .description("The number of times the /api/documents/{id} end-point is called")
        .register(meterRegistry);
    counter.increment();

    final var document = documentsService.getDocumentsById(id);
    return ResponseEntity.ok(document);
  }
}
