package com.org.microservice.documents.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Document {

  @NotNull(message = "Id is mandatory")
  private final String id;

  @NotBlank(message = "Name is mandatory")
  @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
  private final String name;

  @NotNull(message = "Size is mandatory")
  @Size(min = 1, message = "Size must be greater than 0")
  private final float size;
}
