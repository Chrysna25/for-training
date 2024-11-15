package com.kelompok2sibkm.serverapp.controller;

import java.util.List;
import java.util.Map;
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

import com.kelompok2sibkm.serverapp.entity.Country;
import com.kelompok2sibkm.serverapp.model.request.CountryRequest;
import com.kelompok2sibkm.serverapp.model.response.CountryResponse;
import com.kelompok2sibkm.serverapp.service.CountryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;

    // Control untuk membuat data baru ke entitas country
    @PostMapping
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_USER')")
    public Country create(@RequestBody Country country){ 
        return countryService.create(country);
    }

    // Control untuk membuat data baru ke entitas country dengan DTO Manual
    @PostMapping("/dto-manual")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_USER')")
    public Country createDTOManual(@RequestBody CountryRequest countryRequest){
        return countryService.createDTOManual(countryRequest);
    }

    // Control untuk membuat data baru ke entitas country dengan DTO Otomatis
    @PostMapping("/dto-otomatis")
    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_USER')")
    public Country createDTOOtomatis(@RequestBody CountryRequest countryRequest){
        return countryService.createDTOOtomatis(countryRequest);
    }

    // Control untuk mengambil seluruh data dari entitas country
    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Country> getAll(){
        return countryService.getAll();
    }

    // Control untuk mengambil data melalui id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Country getById(@PathVariable Integer id){
        return countryService.getById(id);
    }

    // Control untuk mengambil data melalui id optional
    @GetMapping("/optional/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Optional<Country> getByIdOptional(@PathVariable Integer id){
        return countryService.getByIdOptional(id);
    }

    // Control untuk mengambil data melalui id dengan CustomResponse
    @GetMapping("/res/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public CountryResponse getByIdCustomResponse(@PathVariable Integer id){
        return countryService.getByIdCustomeResponse(id);
    }

    // Control untuk mengambil data melalui id dengan CustomResponseMap 
    @GetMapping("/map/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Map<String, Object> getByIdCustomMap(@PathVariable Integer id){
        return countryService.getByIdCustomeMap(id);
    }

    // Control untuk memperbaharui data yang sudah ada(update)
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_USER')")
    public Country update(@RequestBody Country country, @PathVariable Integer id){
        return countryService.update(country, id);
    }

    // Control untuk menghapus data dari Entitas country
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_USER')")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }

    // Control untuk Native
    @GetMapping("/native")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Country> searchAllNative(@RequestParam(name = "name") String name){
        return countryService.searchAllNameNative(name);
    }

    // Control untuk JPQL
    @GetMapping("/jpql")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Country> searchAllNameJPQL(@RequestParam(name = "name") String name){
        return countryService.searchAllNameJPQL(name);
    }
}