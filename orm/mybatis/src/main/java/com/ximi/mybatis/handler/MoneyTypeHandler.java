package com.ximi.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BIGINT 和  Money 相互转化
 *
 * @author Ximi
 * @since 2020/08/13
 */
@MappedTypes({Money.class})
@MappedJdbcTypes(JdbcType.BIGINT)
public class MoneyTypeHandler extends BaseTypeHandler<Money> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, money.getAmountMajorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseMoney(resultSet.getLong(s));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseMoney(resultSet.getLong(i));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseMoney(callableStatement.getLong(i));
    }

    private Money parseMoney(Long amount) {
        return Money.of(CurrencyUnit.of("CNY"), amount / 100);
}
}
