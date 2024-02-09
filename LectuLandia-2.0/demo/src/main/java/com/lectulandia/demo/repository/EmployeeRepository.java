package com.lectulandia.demo.repository;

import com.lectulandia.demo.entity.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeData, Long> {
}
