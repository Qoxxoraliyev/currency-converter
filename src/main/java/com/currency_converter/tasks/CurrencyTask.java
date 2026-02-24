package com.currency_converter.tasks;

import com.currency_converter.dto.CurrencyDTO;
import com.currency_converter.model.Currency;
import com.currency_converter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyTask {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Value("${fixer.api.url}")
    private String fixedToApiKey;

    @Scheduled(fixedRate = 5*1000*60*60)
    private void getRatesTask(){
        try {
            RestTemplate restTemplate=new RestTemplate();
            CurrencyDTO forObject=restTemplate.getForObject(fixedToApiKey,CurrencyDTO.class);
            forObject.getRates().forEach((key,value)->{
                Currency currency=new Currency(key,value);
                this.currencyRepository.save(currency);
            });
        } catch (RestClientException ex){
            System.out.println(ex.getMessage());
        }
    }


}
