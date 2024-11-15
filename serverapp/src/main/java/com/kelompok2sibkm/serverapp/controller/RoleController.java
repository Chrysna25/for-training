package com.kelompok2sibkm.serverapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2sibkm.serverapp.entity.Role;
import com.kelompok2sibkm.serverapp.service.RoleService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
     private RoleService roleService;
    // Implementasi CRUD yang sudah dibuat service/ menerima dan mengirimkan data

    // control untuk membuat data baru ke entitas role
    @PostMapping
    public Role create(@RequestBody Role role){ 
        return roleService.create(role);
    }
    // control untuk mengambil seluruh data dari entitas role
    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }
    // control untuk mengambil data melalui id
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }
    // control untuk mengambil data melalui id optional
    @GetMapping("/optional/{id}")
    public Optional<Role> getByIdOptional(@PathVariable Integer id){
        return roleService.getByIdOptional(id);
    }
    // control untuk memperbaharui data yang sudah ada(update)
    @PutMapping("{id}")
    public Role update(@RequestBody Role role, @PathVariable Integer id){
        return roleService.update(role, id);
    }
    // control untuk menghapus data dari Entitas role
    @DeleteMapping("{id}")
    public Role delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
}
