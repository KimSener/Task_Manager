package com.example.task_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
@Data
public class CurrencyRateDto implements Serializable {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("CcyNm_EN")
    private String ccyNm_En;
    @JsonProperty("Rate")
    private String rate;
    @JsonProperty("Diff")
    private String diff;
    @JsonProperty("Date")
    private String data;
}
