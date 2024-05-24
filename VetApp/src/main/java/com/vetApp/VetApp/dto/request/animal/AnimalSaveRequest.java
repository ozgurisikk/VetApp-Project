package com.vetApp.VetApp.dto.request.animal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSaveRequest {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String species;
    @NotNull
    @NotEmpty
    private String breed;
    @NotNull
    @NotEmpty
    private String gender;
    @NotNull
    @NotEmpty
    private String colour;

    private LocalDate dateOfBirth;
    @Positive
    private long customerId;
}
