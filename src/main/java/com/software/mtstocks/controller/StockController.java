package com.software.mtstocks.controller;

import com.software.mtstocks.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@Slf4j
public class StockController {

    @Autowired
    private StockService service;
    
    @GetMapping("/")
    public ResponseEntity<Void> handleQuote(@RequestParam String symbol) {
        log.info("Parameter {}", symbol);
        service.getQuote(symbol);
        
        return ResponseEntity.ok().build();
    }
    
}
