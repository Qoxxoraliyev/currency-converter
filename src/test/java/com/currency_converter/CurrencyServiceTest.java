package com.currency_converter;
import com.currency_converter.model.Currency;
import com.currency_converter.repository.CurrencyRepository;
import com.currency_converter.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService subject;

    @BeforeEach
    void setup() {
    }

    @Test
    void getAllCurrencies_shouldReturnSortedList() {

        Currency usd = new Currency("USD", 1.0);
        Currency eur = new Currency("EUR", 0.9);

        when(currencyRepository.findAll())
                .thenReturn(Arrays.asList(usd, eur));

        List<Currency> result = subject.getAllCurrencies();

        assertEquals(2, result.size());
        assertEquals("EUR", result.get(0).getName());
        assertEquals("USD", result.get(1).getName());

        verify(currencyRepository, times(1)).findAll();
    }


    @Test
    void convert_shouldReturnConvertedValue() {

        Currency usd = new Currency("USD", 1.0);
        Currency uzs = new Currency("UZS", 12000.0);
        when(currencyRepository.findById("USD"))
                .thenReturn(java.util.Optional.of(usd));
        when(currencyRepository.findById("UZS"))
                .thenReturn(java.util.Optional.of(uzs));
        var conversion = new com.currency_converter.model.ConversionCurrency(
                "UZS",
                "USD",
                100
        );

        var result = subject.convert(conversion);
        assertTrue(result.isPresent());
        assertEquals(1200000.0, result.get());
        verify(currencyRepository, times(2)).findById(anyString());
    }


}