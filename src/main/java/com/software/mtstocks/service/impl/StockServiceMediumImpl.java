package com.software.mtstocks.service.impl;

import com.software.mtstocks.client.AlphaVantageClient;
import com.software.mtstocks.models.GlobalQuoteResponse;
import com.software.mtstocks.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceMediumImpl implements StockService {

    @Autowired
    private AlphaVantageClient alphaClient;

    @Override
    public String getQuote(String symbol) {

        String function = "GLOBAL_QUOTE";
        GlobalQuoteResponse globalQuote = alphaClient.getStockStats(symbol, function);

        log.info("Stock quote {}", globalQuote);
        return "";
    }
}
