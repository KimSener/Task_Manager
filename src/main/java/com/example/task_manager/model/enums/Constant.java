package com.example.task_manager.model.enums;

public enum Constant {
    USD(1),RUB(2),EUR(3),KRW(4),UZS(5),
    GBP(6),JPY(7),CHF(8),CNY(9),CAD(10);
    private final int id;

    Constant(int id) {
        this.id = id;

    }
}
