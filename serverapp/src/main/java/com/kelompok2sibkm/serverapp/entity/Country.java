package com.kelompok2sibkm.serverapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id // Menunjukan atribut ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // untuk auto incerement
    @Column(name = "country_id") // untuk konfigurasi detail kolom
    private Integer id;
    @Column(unique = true, nullable = false, length = 2, name = "country_code")
    private String code;
    @Column(unique = true, nullable = false, length = 20, name = "country_name") // untuk konfigurasi detail kolom
    private String name;
    // Relasi Many to One dengan Region
    @ManyToOne
    @JoinColumn(name="region_id", nullable=false)
    private Region region; 
}
