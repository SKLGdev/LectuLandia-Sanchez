package com.lectulandia.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LineDTO {

    private Long lineId;
    private Integer quantity;
    private String description;
    private BigDecimal price;

    private Product product;

    private VoucherDetail voucher;

    public LineDTO() {
    }

}
