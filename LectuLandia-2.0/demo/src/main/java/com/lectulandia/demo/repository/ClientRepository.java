package com.lectulandia.demo.repository;

import com.lectulandia.demo.entity.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientData, Long> {
}
