package com.software.mtstocks.models.dto;

import java.util.List;

public record GlobalQuoteResponse(
    List<QuoteResponse> quotes,
    String symbol
) {}