package com.java4.study.Lab6.dao;

import com.java4.study.Lab6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
