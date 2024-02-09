package com.lectulandia.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private List<ClientData> client;

    public User() {
    }

    public User(Long id, String userName, String userPassword, List<ClientData> client) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getUserPassword(), user.getUserPassword()) && Objects.equals(getClient(), user.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getUserPassword(), getClient());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", employeeData=" + client +
                '}';
    }

}
