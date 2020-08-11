package com.ximi.jpa.repository;

import com.ximi.jpa.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder,Long> {

    public List<CoffeeOrder> findByCustomerOrderById(String Customer);

    public List<CoffeeOrder> findByItems_Name(String name);

}
