package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
