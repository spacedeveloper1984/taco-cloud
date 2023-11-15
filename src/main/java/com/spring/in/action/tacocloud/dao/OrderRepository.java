package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
