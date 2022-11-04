package com.example.task_manager.service;

import com.example.task_manager.dto.CurrencyRateDto;

import com.example.task_manager.model.CurrencyRate;

import com.example.task_manager.model.ExchangeRate;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class RestTemplateService {

    private final static RestTemplate restTemplate = new RestTemplate();


    private final static String CURRENCY_RATE_CBU_URL = "https://cbu.uz/ru/arkhiv-kursov-valyut/json/";

    private final static String EXCHANGE_RATES_NBU_URL = "https://nbu.uz/en/exchange-rates/json/";

    private final static
    String CURRENCY_INFORMATION_FOREX_URL =
            "https://fcsapi.com/api-v3/forex/profile?symbol={symbol}&access_key=pDYbCurxBrV70acDKDPbA";


    public ResponseEntity<CurrencyRateDto[]> currencyRateOfDate(String date) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                        CURRENCY_RATE_CBU_URL)
                .path("/all/")
                .path("/" + date + "/");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                CurrencyRateDto[].class);

    }

    public ResponseEntity<CurrencyRateDto[]> currencyOfThisDay() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        HttpEntity<CurrencyRateDto[]> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                CURRENCY_RATE_CBU_URL,
                HttpMethod.GET,
                entity,
                CurrencyRateDto[].class);
    }


    public static CurrencyRate currencyRateOfChoice(String currencyName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                        CURRENCY_RATE_CBU_URL)
                .path("/" + currencyName + "/");
        HttpEntity<CurrencyRate> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<CurrencyRate>> type = new ParameterizedTypeReference<List<CurrencyRate>>() {
        };
        List<CurrencyRate> rates = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, type).getBody();
        assert rates != null;
        for (CurrencyRate rate : rates) {
            System.out.println(rate);
        }
        return rates.get(0);
    }

    /*   Другой вариант использования Параметризуемого класс ResponseEntity<List<CurrencyRate>> rateResponse =
                restTemplate.exchange(builder.toUriString(),
                      HttpMethod.GET, entity, new ParameterizedTypeReference<List<CurrencyRate>>() {
                       });
        List<CurrencyRate> rates = rateResponse.getBody();
        assert rates != null;
       return rates.get(0); - нужен для возвращения всех значения из List от 0 index до Max index проще говоря
       в данном апи возвращения всех курсов валют.

       Для извлечения обьектов из Json ответа а это параметризуемый типы (дженерики)
       нам как то нужно определить тип дженерика.Указать класс CurrencyRate.class или же CurrencyRate[].class
        мы не можем так как библиотека Jackson незнает какой тип дженерика.

        Для таких целей в библиотеке Jackson есть фабрика типов которая поможет создать описание типов
        дженерика на основе класса коллекций и вложенного класса.
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, CurrencyRate.class);

        В случай RestTemplate на основе параметризуемого класса ParameterizedTypeReference с помощью которого
        мы можем описать тип параметризуемого обьекта(или дженерика)
        ParameterizedTypeReference<List<CurrencyRate>> type = new ParameterizedTypeReference<List<CurrencyRate>>() {};

        При этом применить данный класс ParameterizedTypeReference можно не во все методы RestTemplate лучше всего
        подходит метод restTemplate.exchange(myUrl, HttpMethod.GET, null, type);
       */

    public ExchangeRate[] currencyRateBuySaleRateNBU() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(EXCHANGE_RATES_NBU_URL,
                HttpMethod.GET,
                entity,
                ExchangeRate[].class).getBody();

    }

    public ResponseEntity<String> informationOfCurrency(String symbol) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                        CURRENCY_INFORMATION_FOREX_URL)
                .queryParam("symbol",symbol);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

    }
}