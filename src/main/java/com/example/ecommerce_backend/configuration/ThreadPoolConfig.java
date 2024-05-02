package com.example.ecommerce_backend.configuration;

import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class ThreadPoolConfig {
  @Bean
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setMaxPoolSize(20);
    executor.setCorePoolSize(5);
    executor.setThreadNamePrefix("CUSTOM-");

    // Initialize the executor
    executor.initialize();

    return executor;
  }
}
