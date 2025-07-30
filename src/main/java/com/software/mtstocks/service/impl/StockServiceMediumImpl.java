package com.software.mtstocks.service.impl;

import com.software.mtstocks.client.Client;
import com.software.mtstocks.executor.StockExecutor;
import com.software.mtstocks.models.dto.StockResponse;
import com.software.mtstocks.models.enums.StatusEnum;
import com.software.mtstocks.service.StockService;
import java.util.List;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceMediumImpl implements StockService {

  @Autowired private Client client;

  private static final int MAX_THREADS = 120;

  @Override
  public List<StockResponse> getQuote(List<String> symbols) {

    try (ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS)) {

      List<StockExecutor> executors =
          symbols.stream().map(symbol -> new StockExecutor(symbol, client)).toList();
      List<Future<StockResponse>> futures = executorService.invokeAll(executors);

      return futures.stream().map(StockServiceMediumImpl::getResponse).toList();

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<StockResponse> getQuoteSingle(List<String> symbols) {
    log.info("Starting Get stocks single thread ");
    return symbols.stream()
        .map(symbol -> client.getStockStats(symbol))
        .map(
            result ->
                new StockResponse(result.symbol(), result.quotes().getFirst().close(), StatusEnum.OK))
        .toList();
  }

  private static StockResponse getResponse(Future<StockResponse> future) {
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}
