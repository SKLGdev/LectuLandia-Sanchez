package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "employees_data")
public class EmployeeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String city;
    @Column(name = "discharge_date")
    private Date dischargeDate;

    private int year;
    private int month;
    private int day;

    private double salary;
    @Column(name = "incentive_percentage")
    private double incentivePercentage;

    @OneToMany(mappedBy = "employee")
    private List<VoucherDetail> voucherList;

    @OneToOne(mappedBy = "employee")
    private SupplierData supplierData;

    @ManyToOne
    private UserAdmin admin;

    public EmployeeData(){
    }

    public Date getContractRegistrationDate() {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);

        return dischargeDate = calendar.getTime();
    }

    public double getSalaryIncrease() {
        double increase = salary * incentivePercentage/100;

        return salary += increase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeData that)) return false;
        return Double.compare(getSalary(), that.getSalary()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getVoucherList(), that.getVoucherList()) && Objects.equals(getSupplierData(), that.getSupplierData()) && Objects.equals(getAdmin(), that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail(), getPhone(), getAddress(), getCity(), getSalary(), getVoucherList(), getSupplierData(), getAdmin());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", saleList=" + voucherList +
                ", supplier=" + supplierData +
                '}';
    }

}
