package com.kelompok2sibkm.serverapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kelompok2sibkm.serverapp.entity.Country;
import com.kelompok2sibkm.serverapp.entity.Region;
import com.kelompok2sibkm.serverapp.model.request.CountryRequest;
import com.kelompok2sibkm.serverapp.model.response.CountryResponse;
import com.kelompok2sibkm.serverapp.repository.CountryRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository; // deklarasi countryRepository
    private RegionService regionService; // deklarasi regionService

    // CRUD untuk Entitas country 

    // funsi untuk membuat data baru ke entitas country
    public Country create(Country country){ // country country itu adalah deklarasi
        if (
            !countryRepository
            .findByNameAndRegionName(
                country.getName(),
                country.getRegion().getName()
            )
            .isEmpty()
        ) {
            throw new ResponseStatusException(
            HttpStatus.CONFLICT,
            "Country with this name already exists in the same region!"
            );
        }
        Region region = regionService.getById(country.getRegion().getId());
        country.setRegion(region);
        return countryRepository.save(country);
    }
    // fungsi untuk membuat data baru ke entitas country dengan DTO Manual
    public Country createDTOManual(CountryRequest countryRequest){
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());
        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);
        // atau gini juga bisa 
        // country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    } 
    // fungsi untuk membuat data baru ke entitas country dengan DTO Otomatis
    public Country createDTOOtomatis(CountryRequest countryRequest){
        Country country = new Country();
        BeanUtils.copyProperties(countryRequest, country); // Menyalin semua properti dari objek `countryRequest` ke objek `country`.
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }
    // fungsi untuk mengambil seluruh data dari entitas country
    public List<Country> getAll(){
        return countryRepository.findAll();
    }
    // fungsi untuk mengambil data melalui id
    public Country getById(Integer id){
        return countryRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "country Not_Found!!!")
        );
    }
    // fungsi untuk mengambil data melalui id optional
    public Optional<Country> getByIdOptional(Integer id){
        return countryRepository.findById(id); // findById metode bawaan JPA
    }
    // fungsi untuk mengambil data melalui id dengan CustomeResponse
    public CountryResponse getByIdCustomeResponse(Integer id){
        Country country = countryRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"spontan country not found!!!")
        );
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCountryId(country.getId());
        countryResponse.setCountryCode(country.getCode());
        countryResponse.setCountryName(country.getName());
        countryResponse.setRegionId(country.getRegion().getId());
        countryResponse.setRegionName(country.getRegion().getName());
        return countryResponse;
    }
    // fungsi untuk mengambil data melalui id dengan customeResponseMap
    public Map<String,Object> getByIdCustomeMap(Integer id){ 
        Country country = countryRepository.findById(id).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"spontan country not found!!!")
        );
        Map<String, Object> result = new HashMap<>();
        result.put("cId", country.getId());
        result.put("cCode", country.getCode());
        result.put("cName", country.getName());
        result.put("rId", country.getRegion().getId());
        result.put("rName", country.getRegion().getName());
        return result;
    }
    // fungsi untuk memperbaharui data yang sudah ada(update)
    public Country update(Country country, Integer id){
        getById(id); // mengabil data dari country melalui id
        country.setId(id); // meng setId
        // ada setName ga ???
        return countryRepository.save(country);
    }
    // fungsi untuk menghapus data dari Entitas country
    public Country delete(Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }
    // Native Query
    public List<Country> searchAllNameNative(String name){
        String nameFormat = "%" + name + "%";
        log.info("Logging: {}", nameFormat); // ini masuk ke @Slf4j
        return countryRepository.searchAllNameNative(nameFormat);
    }
    // JPQL
    public List<Country> searchAllNameJPQL(String name) {
        // String nameFormat = "%" + name + "%";
        String nameFormat = String.format("%%%s%%", name);
        log.info("Logging: {}", nameFormat);
        return countryRepository.searchAllNameJPQL(nameFormat);
        }
}
