package com.kelompok2sibkm.serverapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2sibkm.serverapp.entity.Region;
import com.kelompok2sibkm.serverapp.service.RegionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/region")
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    // Control untuk membuat data baru ke entitas Region
    @PostMapping
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_USER')")
    public Region create(@RequestBody Region region){ 
        return regionService.create(region);
    }

    // Control untuk mengambil seluruh data dari entitas Region
    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Region> getAll(){
        return regionService.getAll();
    }

    // Control untuk mengambil data melalui id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Region getById(@PathVariable Integer id){
        return regionService.getById(id);
    }

    // Control untuk mengambil data melalui id optional
    @GetMapping("/optional/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Optional<Region> getByIdOptional(@PathVariable Integer id){
        return regionService.getByIdOptional(id);
    }

    // Control untuk memperbaharui data yang sudah ada(update)
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_USER')")
    public Region update(@RequestBody Region region, @PathVariable Integer id){
        return regionService.update(region, id);
    }

    // Control untuk menghapus data dari Entitas Region
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_USER')")
    public Region delete(@PathVariable Integer id){
        return regionService.delete(id);
    }

    // Control untuk Native
    @GetMapping("/native")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Region> searchAllNative(@RequestParam(name = "name") String name){
        return regionService.searchAllNameNative(name);
    }

    // Control untuk JPQL
    @GetMapping("/jpql")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Region> searchAllNameJPQL(@RequestParam(name = "name") String name){
        return regionService.searchAllNameJPQL(name);
    }
}