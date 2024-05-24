package com.vetApp.VetApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVaccineResponse {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;

}
