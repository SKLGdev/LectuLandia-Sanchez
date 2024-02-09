package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users_admins")
public class UserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_admin_name")
    private String userAdminName;
    @Column(name = "user_admin_password")
    private String userAdminPassword;

    @OneToMany(mappedBy = "admin")
    private List<EmployeeData> employee;

    public UserAdmin() {
    }

    public UserAdmin(Long id, String userAdminName, String userAdminPassword, List<EmployeeData> userAdmin) {
        this.id = id;
        this.userAdminName = userAdminName;
        this.userAdminPassword = userAdminPassword;
        this.employee = userAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAdmin userAdmin)) return false;
        return Objects.equals(getId(), userAdmin.getId()) && Objects.equals(getUserAdminName(), userAdmin.getUserAdminName()) && Objects.equals(getUserAdminPassword(), userAdmin.getUserAdminPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserAdminName(), getUserAdminPassword());
    }

    @Override
    public String toString() {
        return "UserAdmin{" +
                "id=" + id +
                ", userAdminName='" + userAdminName + '\'' +
                ", userAdminPassword='" + userAdminPassword + '\'' +
                ", userAdmin=" + employee +
                '}';
    }

}
