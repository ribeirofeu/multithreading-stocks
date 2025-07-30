package com.software.mtstocks.models.dto;

import com.software.mtstocks.models.enums.StatusEnum;

import java.math.BigDecimal;

public record StockResponse(String name, BigDecimal price, StatusEnum status) {}
