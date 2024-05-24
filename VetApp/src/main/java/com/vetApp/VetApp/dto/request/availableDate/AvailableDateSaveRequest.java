package com.vetApp.VetApp.dto.request.availableDate;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveRequest {

    private LocalDate availableDate;
    @Positive
    private long doctorId;
}
