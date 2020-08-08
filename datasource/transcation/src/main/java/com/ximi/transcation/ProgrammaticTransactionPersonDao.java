package com.ximi.transcation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * 编码执行事务
 *
 * @author Ximi
 * @since 2020/08/08
 */
@Service
public class ProgrammaticTransactionPersonDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 插入一条数据
     *
     * @param person
     * @throws RollBackException
     */
    public void insert(Person person) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)", person.id, person.name, person.age);
        } catch (Exception ex) {
            platformTransactionManager.rollback(transactionStatus);
            throw ex;
        }
        platformTransactionManager.commit(transactionStatus);
    }

    /**
     * 插入并且回滚
     *
     * @param person
     */
    public void insertThenRollBack(Person person) {
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                Object savePoint = status.createSavepoint();
                jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)", person.id, person.name, person.age);
                status.rollbackToSavepoint(savePoint);
                return null;
            }
        });
    }

    /**
     * 获取数量
     *
     * @return
     */
    public Integer getCount() {
        return jdbcTemplate.queryForObject("select count(1) from person", Integer.class);
    }

}
