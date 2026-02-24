package com.currency_converter.repository;

import com.currency_converter.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

    @Override
    List<Currency> findAll();

}