package com.kelompok2sibkm.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelompok2sibkm.serverapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
