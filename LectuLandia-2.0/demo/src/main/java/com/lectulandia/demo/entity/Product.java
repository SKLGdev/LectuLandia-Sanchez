package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "product_description")
    private String productDescription;
    private Integer stock;
    @Column(name = "product_price")
    private BigDecimal productPrice;

    @ManyToOne
    private ClientData client;

    @ManyToOne
    private SupplierData supplier;

    @OneToMany(mappedBy = "product")
    private Set<Line> line;

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getStock() == product.getStock() && Objects.equals(getId(), product.getId()) && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getAuthorName(), product.getAuthorName()) && Objects.equals(getProductDescription(), product.getProductDescription()) && Objects.equals(getProductPrice(), product.getProductPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductName(), getAuthorName(), getProductDescription(), getStock(), getProductPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productAuthorName='" + authorName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", client=" + client +
                ", supplier=" + supplier +
                '}';
    }

}
