package com.yarmarq.task;

import com.yarmarq.deserializable.Gold;

public class GoldPriceTask implements ITask {
    private final Gold gold;

    public GoldPriceTask(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void accomplish() {
        System.out.printf(
                "Gold price in %s was %.4f\n",
                gold.getDate(), gold.getPrice()
        );
    }
}
