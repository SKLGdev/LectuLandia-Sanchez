package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class SupplierData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "supplier_quantity")
    private int productQuantity;
    private double total;

    @OneToOne
    private EmployeeData employee;

    @OneToMany(mappedBy = "supplier")
    private List<Product> productList;

    public SupplierData() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierData supplierData)) return false;
        return Double.compare(getTotal(), supplierData.getTotal()) == 0 && Objects.equals(getId(), supplierData.getId()) && Objects.equals(getSupplierName(), supplierData.getSupplierName()) && Objects.equals(getEmployee(), supplierData.getEmployee()) && Objects.equals(getProductList(), supplierData.getProductList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSupplierName(), getTotal(), getEmployee(), getProductList());
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", total=" + total +
                ", employee=" + employee +
                ", productList=" + productList +
                '}';
    }

}
