package com.vetApp.VetApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponseWithNoAnimal {
    private long id;
    private String name;
    private String code;

}
