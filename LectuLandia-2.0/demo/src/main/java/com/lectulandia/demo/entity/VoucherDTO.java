package com.lectulandia.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class VoucherDTO {

    private Long id;
    private String voucherName;
    private String description;
    private Integer quantity;
    private String section;
    private Date creationDate;
    private Date lastUpdateDate;
    private BigDecimal totalPrice;

    private ClientData client;

    private EmployeeData employee;

    private Product product;

    private Set<Line> lines;

    public VoucherDTO() {
    }

}
