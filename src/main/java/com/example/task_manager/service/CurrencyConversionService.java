package com.example.task_manager.service;

import com.example.task_manager.dto.ConverterRequestDto;
import com.example.task_manager.dto.ConverterResponseDto;
import com.example.task_manager.model.enums.Constant;
import org.springframework.stereotype.Service;



@Service
public class CurrencyConversionService {

    public static double getConversionRatio(Constant from, Constant to, Integer amount) {

        double result;
        if (from.equals(Constant.UZS)) {
            double toRate = Double.parseDouble(RestTemplateService.currencyRateOfChoice(String.valueOf(to)).getRate());
            result = 1 / toRate;
        } else if (to.equals(Constant.UZS)) {
            result = Double.parseDouble(RestTemplateService.currencyRateOfChoice(String.valueOf(from)).getRate());

        } else {
            double fromRate = Double.parseDouble(RestTemplateService.currencyRateOfChoice(String.valueOf(from)).getRate());
            double toRate = Double.parseDouble(RestTemplateService.currencyRateOfChoice(String.valueOf(to)).getRate());
            result = fromRate / toRate;
        }
        return result * amount;

    }

    public static ConverterResponseDto transfer(ConverterRequestDto converterRequestDto) {
        String from = converterRequestDto.getFrom();
        String to = converterRequestDto.getTo();
        Integer amount = converterRequestDto.getAmount();
        double result = getConversionRatio(Constant.valueOf(from), Constant.valueOf(to), amount);

        return ConverterResponseDto.builder().message("Convert for " + to + " completed")
                .success(true).amount((int) result).build();
    }
}
