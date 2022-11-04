package com.example.task_manager.controller;

import com.example.task_manager.dto.CurrencyRateDto;

import com.example.task_manager.dto.ConverterRequestDto;
import com.example.task_manager.dto.ConverterResponseDto;
import com.example.task_manager.model.CurrencyRate;
import com.example.task_manager.model.ExchangeRate;
import com.example.task_manager.service.CurrencyConversionService;
import com.example.task_manager.service.RestTemplateService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/CurrencyRate")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class CurrencyController {

    private final RestTemplateService restTemplateService;

    @Autowired
    public CurrencyController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CurrencyRateDto[]>
    allCurrencyOfDate(@RequestParam(value = "date") String date) {

        return restTemplateService.currencyRateOfDate(date);
    }

    @GetMapping("/ThisDayCBU")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CurrencyRateDto[]> allCurrencyOfThatDay() {
        return restTemplateService.currencyOfThisDay();
    }

    @GetMapping("/BuySaleNBU")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ExchangeRate[] buySaleNBU() {
        return restTemplateService.currencyRateBuySaleRateNBU();
    }

    @RequestMapping(value = "/{currencyName}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CurrencyRate CurrencyOfChoice(@PathVariable String currencyName) {
        return RestTemplateService.currencyRateOfChoice(currencyName);
    }


    @GetMapping("/ConversionOfCBU")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> conversion(@RequestBody ConverterRequestDto converterRequestDto) {
        ConverterResponseDto response = CurrencyConversionService.transfer(converterRequestDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/InformByForex")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> ForexCurrencyInformation(String symbol) {
        return restTemplateService.informationOfCurrency(symbol);
    }

    /* Указывать один и тот же Url(основа до конечной точки) ненужно
          так как возникает конфликт Spring незнает что именно использовать в данном коде string date или
         string currencyName т.е сопастовления неоднозначное поэтому делаем разные url в конченой точке
            api/ExchangeRate/currencyName на выбор валюта и api/ExchangeRate/? date= 2022-01-01 на выбор дата */


}
