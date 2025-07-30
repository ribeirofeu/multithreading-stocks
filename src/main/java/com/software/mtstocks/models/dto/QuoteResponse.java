package com.software.mtstocks.models.dto;

import java.math.BigDecimal;

public record QuoteResponse(
        BigDecimal close,
        String date,
        BigDecimal dividends,
        BigDecimal high,
        BigDecimal low,
        BigDecimal open,
        BigDecimal stock_splits,
        long volume
) {}