package com.software.mtstocks.executor;

import com.software.mtstocks.client.Client;
import com.software.mtstocks.models.dto.GlobalQuoteResponse;
import com.software.mtstocks.models.dto.StockResponse;
import com.software.mtstocks.models.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

@Slf4j
public class StockExecutor implements Callable<StockResponse> {

  private final String symbol;
  private final Client client;

  public StockExecutor(String symbol, Client client) {
    this.symbol = symbol;
    this.client = client;
  }

  @Override
  public StockResponse call() {
    try {
      GlobalQuoteResponse stockStats = client.getStockStats(symbol);
      log.debug("Client response {}", stockStats);
      return new StockResponse(stockStats.symbol(), stockStats.quotes().getFirst().close(), StatusEnum.OK);
    } catch (Exception e) {
      log.error("Stock NOT_FOUND {}", symbol);
      return new StockResponse(symbol, BigDecimal.valueOf(0), StatusEnum.NOT_FOUND);
    }
  }
}
