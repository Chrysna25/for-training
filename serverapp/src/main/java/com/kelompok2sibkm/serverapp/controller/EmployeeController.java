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

import com.kelompok2sibkm.serverapp.entity.Employee;
import com.kelompok2sibkm.serverapp.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    // Implementasi CRUD yang sudah dibuat service/ menerima dan mengirimkan data

    // control untuk membuat data baru ke entitas employee
    @PostMapping
    public Employee create(@RequestBody Employee employee){ 
        return employeeService.create(employee);
    }
    // control untuk mengambil seluruh data dari entitas employee
    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
    // control untuk mengambil data melalui id
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeService.getById(id);
    }
    // control untuk mengambil data melalui id optional
    @GetMapping("/optional/{id}")
    public Optional<Employee> getByIdOptional(@PathVariable Integer id){
        return employeeService.getByIdOptional(id);
    }
    // control untuk memperbaharui data yang sudah ada(update)
    @PutMapping("{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Integer id){
        return employeeService.update(employee, id);
    }
    // control untuk menghapus data dari Entitas employee
    @DeleteMapping("{id}")
    public Employee delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }
}
