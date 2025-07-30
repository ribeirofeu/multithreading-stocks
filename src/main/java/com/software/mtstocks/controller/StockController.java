package com.software.mtstocks.controller;

import com.software.mtstocks.models.dto.StockRequest;
import com.software.mtstocks.models.dto.StockResponse;
import com.software.mtstocks.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class StockController {

    @Autowired
    private StockService service;
    
    @PostMapping("/multi")
    public ResponseEntity<List<StockResponse>> getQuotesMulti(@RequestBody StockRequest request) {
        log.info("Request Body - MultiThreading {}", request);
        return ResponseEntity.ok(service.getQuote(request.symbols()));
    }

    @PostMapping("/single")
    public ResponseEntity<List<StockResponse>> getQuotesSingle(@RequestBody StockRequest request) {
        log.info("Request Body - Single Threading {}", request);
        return ResponseEntity.ok(service.getQuoteSingle(request.symbols()));
    }
    
}
