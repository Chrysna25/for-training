package com.kelompok2sibkm.serverapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kelompok2sibkm.serverapp.entity.Region;
import com.kelompok2sibkm.serverapp.repository.RegionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@AllArgsConstructor
@Slf4j
public class RegionService {
    private RegionRepository regionRepository; // deklarasi RegionRepository
    
    // CRUD untuk Entitas Region 

    // funsi untuk membuat data baru ke entitas Region
    public Region create(Region region){ // Region region itu adalah deklarasi
        return regionRepository.save(region);
    }
    // fungsi untuk mengambil seluruh data dari entitas Region
    public List<Region> getAll(){
        return regionRepository.findAll();
    }
    // fungsi untuk mengambil data melalui id
    public Region getById(Integer id){
        return regionRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Region Not_Found!!!")
        );
    }
    // fungsi untuk mengambil data melalui id optional
    public Optional<Region> getByIdOptional(Integer id){
        return regionRepository.findById(id); // findById metode bawaan JPA
    }
    // fungsi untuk memperbaharui data yang sudah ada(update)
    public Region update(Region region, Integer id){
        getById(id); // mengabil data dari Region melalui id
        region.setId(id); // meng setId
        return regionRepository.save(region);
    }
    // fungsi untuk menghapus data dari Entitas Region
    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }
    // Native Query
    public List<Region> searchAllNameNative(String name){
        String nameFormat = "%" + name + "%";
        log.info("Logging: {}", nameFormat); // ini masuk ke @Slf4j
        return regionRepository.searchAllNameNative(nameFormat);
    }
    // JPQL
    public List<Region> searchAllNameJPQL(String name) {
    // String nameFormat = "%" + name + "%";
    String nameFormat = String.format("%%%s%%", name);
    log.info("Logging: {}", nameFormat);
    return regionRepository.searchAllNameJPQL(nameFormat);
    }
}
