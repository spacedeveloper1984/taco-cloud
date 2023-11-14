package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
