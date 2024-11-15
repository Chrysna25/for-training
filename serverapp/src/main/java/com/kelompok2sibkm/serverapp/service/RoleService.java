package com.kelompok2sibkm.serverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kelompok2sibkm.serverapp.entity.Role;
import com.kelompok2sibkm.serverapp.repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
     private RoleRepository roleRepository; // deklarasi roleRepository
    
    // CRUD untuk Entitas role 

    // funsi untuk membuat data baru ke entitas role
    public Role create(Role role){ // role role itu adalah deklarasi
        return roleRepository.save(role);
    }
    // fungsi untuk mengambil seluruh data dari entitas role
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
    // fungsi untuk mengambil data melalui id
    public Role getById(Integer id){
        return roleRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "role Not_Found!!!")
        );
    }
    // fungsi untuk mengambil data melalui id optional
    public Optional<Role> getByIdOptional(Integer id){
        return roleRepository.findById(id); // findById metode bawaan JPA
    }
    // fungsi untuk memperbaharui data yang sudah ada(update)
    public Role update(Role role, Integer id){
        getById(id); // mengabil data dari role melalui id
        role.setId(id); // meng setId
        return roleRepository.save(role);
    }
    // fungsi untuk menghapus data dari Entitas role
    public Role delete(Integer id){
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
