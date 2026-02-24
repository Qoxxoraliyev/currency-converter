package com.currency_converter;
import com.currency_converter.model.ConversionCurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {



    private RestTemplate restTemplate;

    private String basePath="http://localhost:8080";

    @BeforeEach
    public void setup(){
        restTemplate=new RestTemplate();
    }

    @Test
    public void ConvertShouldBeSuccessful(){
        String url = basePath + "/api/currency-converter";
        ConversionCurrency request =
                new ConversionCurrency("EUR","USD",100);
        Double response = restTemplate.postForObject(
                url,
                request,
                Double.class
        );
        assertNotNull(response);
    }



}
