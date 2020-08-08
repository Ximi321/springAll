package com.ximi.transcation;

/**
 *
 */
public interface PersonService {

    /**
     * 插入一条数据
     *
     * @param person
     */
    public void insert(Person person);

    /**
     * 插入一条数据后然后回滚
     *
     * @param person
     * @throws RollBackException 回滚的异常
     */
    public void insertThenRollback(Person person) throws RollBackException;

    /**
     * 内部调用 insertThenRollback 方法
     *
     * @param person
     * @throws RollBackException
     */
    public void invokeInsertThenRollback(Person person) throws RollBackException;

    /**
     * 获取数量
     *
     * @return
     */
    public Integer getCount();

}
