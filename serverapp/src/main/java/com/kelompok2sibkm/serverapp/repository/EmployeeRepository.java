package com.kelompok2sibkm.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelompok2sibkm.serverapp.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
