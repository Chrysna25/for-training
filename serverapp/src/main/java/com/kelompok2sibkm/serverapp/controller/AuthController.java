package com.kelompok2sibkm.serverapp.controller;

import com.kelompok2sibkm.serverapp.entity.Employee;
import com.kelompok2sibkm.serverapp.entity.Role;
import com.kelompok2sibkm.serverapp.model.request.LoginRequest;
import com.kelompok2sibkm.serverapp.model.request.RegistrationRequest;
import com.kelompok2sibkm.serverapp.model.response.LoginResponse;
import com.kelompok2sibkm.serverapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public Employee registration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.registration(registrationRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/auth/{idEmployee}/roles")
    public Employee addRole(@PathVariable Integer idEmployee, @RequestBody Role role) {
        return authService.addRole(idEmployee, role);
    }

    @PutMapping("/auth/{idEmployee}/roles/{roleId}")
    public Employee updateRole(@PathVariable Integer idEmployee, @PathVariable Integer roleId, @RequestBody Role updatedRole) {
        return authService.updateRole(idEmployee, roleId, updatedRole);
    }

    @DeleteMapping("/auth/{idEmployee}/roles/{roleId}")
    public void deleteRole(@PathVariable Integer idEmployee, @PathVariable Integer roleId) {
        authService.deleteRole(idEmployee, roleId);
    }
}
