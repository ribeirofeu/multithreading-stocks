package com.software.mtstocks.service;

import com.software.mtstocks.models.dto.StockResponse;

import java.util.List;

public interface StockService {

    List<StockResponse> getQuote(List<String> symbols);

    List<StockResponse> getQuoteSingle(List<String> symbols);

}
