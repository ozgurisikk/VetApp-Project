package com.vetApp.VetApp.dto.request.appointment;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {
    @Positive
    private long id;
    private LocalDateTime availableDate;
    @Positive
    private long doctorId;
    @Positive
    private long animalId;
}
