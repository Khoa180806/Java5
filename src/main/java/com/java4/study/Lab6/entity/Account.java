package com.java4.study.Lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    @Column(name = "Fullname", length = 50, nullable = false)
    private String fullname;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Photo", length = 255)
    private String photo;

    @Column(name = "Activated")
    private Boolean activated = false;

    @Column(name = "Role")
    private Boolean role = false;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}
