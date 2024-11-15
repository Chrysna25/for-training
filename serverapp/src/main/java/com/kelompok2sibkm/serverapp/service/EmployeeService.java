package com.kelompok2sibkm.serverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kelompok2sibkm.serverapp.entity.Employee;
import com.kelompok2sibkm.serverapp.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository empolyeeRepository; // deklarasi empolyeeRepository
    
    // CRUD untuk Entitas empolyee 

    // funsi untuk membuat data baru ke entitas empolyee
    public Employee create(Employee empolyee){ // empolyee empolyee itu adalah deklarasi
        return empolyeeRepository.save(empolyee);
    }
    // fungsi untuk mengambil seluruh data dari entitas empolyee
    public List<Employee> getAll(){
        return empolyeeRepository.findAll();
    }
    // fungsi untuk mengambil data melalui id
    public Employee getById(Integer id){
        return empolyeeRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "empolyee Not_Found!!!")
        );
    }
    // fungsi untuk mengambil data melalui id optional
    public Optional<Employee> getByIdOptional(Integer id){
        return empolyeeRepository.findById(id); // findById metode bawaan JPA
    }
    // fungsi untuk memperbaharui data yang sudah ada(update)
    public Employee update(Employee empolyee, Integer id){
        getById(id); // mengabil data dari empolyee melalui id
        empolyee.setId(id); // meng setId
        return empolyeeRepository.save(empolyee);
    }
    // fungsi untuk menghapus data dari Entitas empolyee
    public Employee delete(Integer id){
        Employee empolyee = getById(id);
        empolyeeRepository.delete(empolyee);
        return empolyee;
    }
}
