package com.software.mtstocks.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GlobalQuoteResponse(
    @JsonProperty("Global Quote")
    GlobalQuote globalQuote
) {}