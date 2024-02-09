package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "voucher")
public class VoucherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "voucher_name")
    private String voucherName;
    private String description;
    @Column(name = "product_quantity")
    private Integer quantity;
    @Column(name = "product_section")
    private String section;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    private ClientData client;

    @ManyToOne
    private EmployeeData employee;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "voucher", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Line> lines;

    public VoucherDetail() {
    }

    public void addLinea(Line line) {
        getLines().add(line);
        line.setVoucher(this);
    }

    public Line removeLinea(Line line) {
        getLines().remove(line);
        line.setVoucher(null);

        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoucherDetail that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVoucherName(), that.getVoucherName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getQuantity(), that.getQuantity()) && Objects.equals(getSection(), that.getSection()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getLastUpdateDate(), that.getLastUpdateDate()) && Objects.equals(getTotalPrice(), that.getTotalPrice()) && Objects.equals(getClient(), that.getClient()) && Objects.equals(getEmployee(), that.getEmployee()) && Objects.equals(getProduct(), that.getProduct()) && Objects.equals(getLines(), that.getLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVoucherName(), getDescription(), getQuantity(), getSection(), getCreationDate(), getLastUpdateDate(), getTotalPrice(), getClient(), getEmployee(), getProduct(), getLines());
    }

    @Override
    public String toString() {
        return "VoucherDetail{" +
                "id=" + id +
                ", saleName='" + voucherName + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", section='" + section + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", totalPrice=" + totalPrice +
                ", client=" + client +
                ", employee=" + employee +
                ", product=" + product +
                ", lines=" + lines +
                '}';
    }

}
