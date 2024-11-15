package com.kelompok2sibkm.serverapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kelompok2sibkm.serverapp.entity.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
    // Native
    @Query(value = "SELECT * FROM the_country c WHERE c.country_name LIKE ?1",nativeQuery = true)
    List<Country> searchAllNameNative(String name);
    // JPQL
     @Query("SELECT c FROM Country c WHERE c.name LIKE :name")
    List<Country> searchAllNameJPQL(@Param("name") String name);
    Optional<Country> findByName(String name);
    // Validation
    List<Country> findByNameAndRegionName(String countryName, String regionName);
}
