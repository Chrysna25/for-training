package com.kelompok2sibkm.serverapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    // untuk id itukan sudah auto increment jadi tidak dimasukan
    private String code;
    private String name;
    private Integer regionId;
}
