package com.software.mtstocks.client;

import com.software.mtstocks.models.dto.GlobalQuoteResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Component
@Slf4j
public class Client {

  @Value("${alphavantage.url}")
  private String url;

  @Autowired private WebClient webClient;

  public GlobalQuoteResponse getStockStats(String symbol) {

    log.info("Get Stock {} information", symbol);

    Map<String, String> variables = new HashMap<>();
    variables.put("symbol",symbol);
    try {
      return webClient
          .get()
          .uri(url, variables)
          .retrieve()
          .bodyToMono(GlobalQuoteResponse.class)
          .block();

    } catch (WebClientException e) {
      log.error("Error call stock client", e);
      throw e;
    }
  }
}
