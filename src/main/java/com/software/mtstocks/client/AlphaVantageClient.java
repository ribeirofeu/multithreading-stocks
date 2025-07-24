package com.software.mtstocks.client;

import com.software.mtstocks.models.GlobalQuoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AlphaVantageClient {

  // https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=BUY07EK7UYI9SX9B

  @Value("${alphavantage.url}")
  private String url;

  @Value("${alphavantage.api-key}")
  private String apiKey;

  @Autowired private WebClient webClient;

  public GlobalQuoteResponse getStockStats(String symbol, String function) {

    log.info("Get Stock {} information - function {}", symbol, function);
    log.info(apiKey);

    Map<String, String> variables = new HashMap<>();
    variables.put("symbol",symbol);
    variables.put("function",function);
    variables.put("apikey",apiKey);


    try {
      return webClient
          .get()
          .uri(url, variables)
          .retrieve()
          .bodyToMono(GlobalQuoteResponse.class)
          .block();
    } catch (WebClientException e) {
      log.error("Error", e);
      throw e;
    }
  }
}
