package com.kelompok2sibkm.serverapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id // Menunjukan atribut ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // untuk auto incerement
    @Column(name = "region_id") // untuk konfigurasi detail kolom
    private Integer id;
    @Column(unique = true, nullable = false, length = 20, name = "region_name") // untuk konfigurasi detail kolom
    private String name;
    // Relasi One to Many dengan Country
    @OneToMany(mappedBy="region")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Country> countries;
}
