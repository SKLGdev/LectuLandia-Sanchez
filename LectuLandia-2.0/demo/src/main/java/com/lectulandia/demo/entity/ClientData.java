package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clients_data")
public class ClientData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String city;

    @OneToMany(mappedBy = "client")
    private List<Product> productList;

    @OneToMany(mappedBy = "client")
    private List<VoucherDetail> voucherList;

    @ManyToOne
    private User user;

    public ClientData() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientData that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getProductList(), that.getProductList()) && Objects.equals(getVoucherList(), that.getVoucherList()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail(), getPhone(), getAddress(), getCity(), getProductList(), getVoucherList(), getUser());
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", productList=" + productList +
                ", voucherList=" + voucherList +
                ", user=" + user +
                '}';
    }

}
