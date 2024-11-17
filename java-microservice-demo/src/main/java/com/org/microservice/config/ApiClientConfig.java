package com.org.microservice.config;

import com.org.ApiClient;
import com.org.api.DocumentsApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiClientConfig {
  private final String baseUrl;

  public ApiClientConfig(@Value("${microservices.main.baseUrl}") final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Bean
  public ApiClient apiClient(RestClient.Builder builder) {
    var restClient = builder
        .requestInterceptor(new BasicAuthenticationInterceptor("admin", "admin"))
        .build();
    var apiClient = new ApiClient(restClient);
    apiClient.setBasePath(baseUrl);
    return apiClient;
  }

  @Bean
  public DocumentsApi documentsApi(ApiClient apiClient) {
    return new DocumentsApi(apiClient);
  }

}
