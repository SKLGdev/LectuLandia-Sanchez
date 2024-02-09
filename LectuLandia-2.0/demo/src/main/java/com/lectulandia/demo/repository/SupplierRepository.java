package com.lectulandia.demo.repository;

import com.lectulandia.demo.entity.SupplierData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<SupplierData, Long> {
}
