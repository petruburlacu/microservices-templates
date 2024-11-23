package com.org.microservice.config;

import com.org.user_service_v2.ApiClient;
import com.org.user_service_v2.api.DefaultApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class UserServiceClientConfig {
  private final String baseUrl;

  public UserServiceClientConfig(@Value("${microservices.users.baseUrl}") final String baseUrl) {
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
  public DefaultApi usersApi(ApiClient apiClient) {
    return new DefaultApi(apiClient);
  }

}
