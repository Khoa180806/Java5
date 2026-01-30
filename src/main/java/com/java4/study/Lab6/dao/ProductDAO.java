package com.java4.study.Lab6.dao;

import com.java4.study.Lab6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
