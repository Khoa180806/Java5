package com.java4.study.Lab6.dao;

import com.java4.study.Lab6.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, String> {
}
