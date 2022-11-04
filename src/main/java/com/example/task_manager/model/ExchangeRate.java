package com.example.task_manager.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class ExchangeRate implements Serializable {

    private String title;
    private String code;
    private String cb_price;
    private String nbu_buy_price;
    private String nbu_cell_price;
    private String date;


}
