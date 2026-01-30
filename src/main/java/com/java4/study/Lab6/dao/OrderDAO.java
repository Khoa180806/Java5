package com.java4.study.Lab6.dao;

import com.java4.study.Lab6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Long> {
}
