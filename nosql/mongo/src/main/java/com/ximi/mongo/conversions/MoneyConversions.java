package com.ximi.mongo.conversions;


import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;


/**
 * 把 mongo document 转化为  Money
 *
 * @author Ximi
 * @since 2020/08/19
 */
public class MoneyConversions implements Converter<Document, Money> {

    @Override
    public Money convert(Document source) {
        Document money = (Document) source.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document) money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency), amount);
    }
}
