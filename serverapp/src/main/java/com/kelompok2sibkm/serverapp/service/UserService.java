package com.kelompok2sibkm.serverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kelompok2sibkm.serverapp.entity.User;
import com.kelompok2sibkm.serverapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository; // deklarasi userRepository
    
    // CRUD untuk Entitas user 

    // funsi untuk membuat data baru ke entitas user
    public User create(User user){ // user user itu adalah deklarasi
        return userRepository.save(user);
    }
    // fungsi untuk mengambil seluruh data dari entitas user
    public List<User> getAll(){
        return userRepository.findAll();
    }
    // fungsi untuk mengambil data melalui id
    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "user Not_Found!!!")
        );
    }
    // fungsi untuk mengambil data melalui id optional
    public Optional<User> getByIdOptional(Integer id){
        return userRepository.findById(id); // findById metode bawaan JPA
    }
    // fungsi untuk memperbaharui data yang sudah ada(update)
    public User update(User user, Integer id){
        getById(id); // mengabil data dari user melalui id
        user.setId(id); // meng setId
        return userRepository.save(user);
    }
    // fungsi untuk menghapus data dari Entitas user
    public User delete(Integer id){
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
