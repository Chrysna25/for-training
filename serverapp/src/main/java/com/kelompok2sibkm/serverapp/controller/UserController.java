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

import com.kelompok2sibkm.serverapp.entity.User;
import com.kelompok2sibkm.serverapp.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
     private UserService userService;
    // Implementasi CRUD yang sudah dibuat service/ menerima dan mengirimkan data

    // control untuk membuat data baru ke entitas user
    @PostMapping
    public User create(@RequestBody User user){ 
        return userService.create(user);
    }
    // control untuk mengambil seluruh data dari entitas user
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    // control untuk mengambil data melalui id
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    // control untuk mengambil data melalui id optional
    @GetMapping("/optional/{id}")
    public Optional<User> getByIdOptional(@PathVariable Integer id){
        return userService.getByIdOptional(id);
    }
    // control untuk memperbaharui data yang sudah ada(update)
    @PutMapping("{id}")
    public User update(@RequestBody User user, @PathVariable Integer id){
        return userService.update(user, id);
    }
    // control untuk menghapus data dari Entitas user
    @DeleteMapping("{id}")
    public User delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
