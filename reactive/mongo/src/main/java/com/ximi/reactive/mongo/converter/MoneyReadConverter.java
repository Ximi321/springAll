package com.ximi.reactive.mongo.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

public class MoneyReadConverter implements Converter<Long, Money> {

    @Override
    public Money convert(Long source) {
        return Money.ofMajor(CurrencyUnit.of("CYN"),source);
    }
}
