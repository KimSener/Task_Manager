package com.example.task_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.io.Serializable;


@Data
public class CurrencyRate implements Serializable{
    @JsonProperty("id")
    private int id;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Ccy")
    private String ccy;
    @JsonProperty("CcyNm_RU")
    private String ccyNm_Ru;
    @JsonProperty("CcyNm_UZ")
    private String ccyNm_Uz;
    @JsonProperty("CcyNm_UZC")
    private String ccyNm_Uzc;
    @JsonProperty("CcyNm_EN")
    private String ccyNm_En;
    @JsonProperty("Nominal")
    private String nominal;
    @JsonProperty("Rate")
    private String rate;
    @JsonProperty("Diff")
    private String diff;
    @JsonProperty("Date")
    private String data;

    /* Чтобы не идти против конвенций положенной называть переменные согласно CamelCase так как ответ Json возвращает
    значение ввиде snakeCase, указываем аннотация @JsonProperty с названием согласно CamelCase
     */

}
