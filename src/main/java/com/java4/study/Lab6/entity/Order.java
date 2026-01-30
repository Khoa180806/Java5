package com.java4.study.Lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private Account account;

    @Column(name = "Phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "Address", length = 100, nullable = false)
    private String address;

    @Column(name = "Note", length = 200)
    private String note;

    @Column(name = "Status")
    private Integer status = 0;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
