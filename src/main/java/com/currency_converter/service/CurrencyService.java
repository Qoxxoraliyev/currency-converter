package com.currency_converter.service;

import com.currency_converter.model.Currency;
import com.currency_converter.model.ConversionCurrency;
import com.currency_converter.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {

        List<Currency> currencyList = currencyRepository.findAll();

        currencyList.sort(Comparator.comparing(Currency::getName));

        return currencyList;
    }

    public Optional<Double> convert(ConversionCurrency conversionCurrency) {

        Optional<Currency> toOptional =
                currencyRepository.findById(
                        conversionCurrency.getTo().toUpperCase()
                );

        Optional<Currency> fromOptional =
                currencyRepository.findById(
                        conversionCurrency.getFrom().toUpperCase()
                );

        if (toOptional.isEmpty() || fromOptional.isEmpty()) {
            return Optional.empty();
        }

        Currency to = toOptional.get();
        Currency from = fromOptional.get();

        Double result =
                to.getValueInUsd()
                        * conversionCurrency.getValue()
                        / from.getValueInUsd();

        return Optional.of(result);
    }


}