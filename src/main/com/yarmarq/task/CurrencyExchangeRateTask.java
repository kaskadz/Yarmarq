package com.yarmarq.task;

import com.yarmarq.deserializable.Rate;

public class CurrencyExchangeRateTask implements ITask {
    private final Rate rate;

    public CurrencyExchangeRateTask(Rate rate) {
        this.rate = rate;
    }

    @Override
    public void accomplish() {
        System.out.printf(
                "Currency %s from %s of code %s had %.4f exchange rate in %s\n",
                rate.getCurrency(),
                rate.getCountry(),
                rate.getCode(),
                rate.getRates()[0].getMid(),
                rate.getRates()[0].getEffectiveDate()
        );
    }
}
