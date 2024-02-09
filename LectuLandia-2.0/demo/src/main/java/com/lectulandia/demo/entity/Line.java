package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "lines")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Long lineId;
    private Integer quantity;
    private String description;
    private BigDecimal price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private VoucherDetail voucher;

    public Line() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line line)) return false;
        return getQuantity() == line.getQuantity() && Objects.equals(getLineId(), line.getLineId()) && Objects.equals(getDescription(), line.getDescription()) && Objects.equals(getPrice(), line.getPrice()) && Objects.equals(getProduct(), line.getProduct()) && Objects.equals(getVoucher(), line.getVoucher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineId(), getQuantity(), getDescription(), getPrice(), getProduct(), getVoucher());
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineId=" + lineId +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", product=" + product +
                ", voucher=" + voucher +
                '}';
    }

}