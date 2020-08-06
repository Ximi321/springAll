package com.ximi.druid;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyFilter extends FilterEventAdapter {

    @Override
    protected void statementExecuteBefore(StatementProxy statement, String sql) {
        log.info("statementExecuteBefore sql {}" + sql);
    }

    @Override
    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
        log.info("statementExecuteQueryBefore sql {}" + sql);
    }
}
