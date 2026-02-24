package com.currency_converter.controller;

import com.currency_converter.model.Currency;
import com.currency_converter.model.ConversionCurrency;
import com.currency_converter.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {

    private final CurrencyService currencyService;

    public CurrencyConverterController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/currency-converter")
    public ResponseEntity<Double> convertCurrencies(
            @RequestBody ConversionCurrency conversionCurrency) {

        Optional<Double> resultOptional =
                currencyService.convert(conversionCurrency);

        return resultOptional
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }


    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies() {

        return new ResponseEntity<>(
                currencyService.getAllCurrencies(),
                HttpStatus.OK
        );
    }


}