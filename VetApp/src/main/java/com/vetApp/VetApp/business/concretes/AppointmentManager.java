package com.vetApp.VetApp.business.concretes;

import com.vetApp.VetApp.business.abstracts.AppointmentService;
import com.vetApp.VetApp.core.exception.ConflictException;
import com.vetApp.VetApp.core.exception.MinuteException;
import com.vetApp.VetApp.core.exception.NotFoundException;
import com.vetApp.VetApp.core.utilities.Msg;
import com.vetApp.VetApp.dao.AppointmentRepo;
import com.vetApp.VetApp.dao.AvailableDateRepo;
import com.vetApp.VetApp.dao.DoctorRepo;
import com.vetApp.VetApp.entities.Appointment;
import com.vetApp.VetApp.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final AvailableDateRepo availableDateRepo;


    @Override
    public Appointment save(Appointment appointment) {
        Optional<Appointment> appointmentExist = this.appointmentRepo.findByAvailableDateAndDoctorIdAndAnimalId(appointment.getAvailableDate(), appointment.getDoctor().getId(), appointment.getAnimal().getId());
        if (appointmentExist.isPresent()){
            throw new ConflictException(Msg.ALREADY_SAVED_APPOINTMENT);
        }

        Optional<AvailableDate> doctorDayAvailability = this.availableDateRepo.findByDoctorIdAndAvailableDate(appointment.getDoctor().getId(), appointment.getAvailableDate().toLocalDate());
        if (doctorDayAvailability.isEmpty()){
            throw new NotFoundException(Msg.DOCTOR_DAYOFF);
        }
        Optional<Appointment> doctorAppointmentCheck = this.appointmentRepo.findByAvailableDateAndDoctorId(appointment.getAvailableDate(), appointment.getDoctor().getId());
        if (doctorAppointmentCheck.isPresent()){
            throw new ConflictException(Msg.DOCTOR_HAS_APPOINTMENT);
        }
        if (appointment.getAvailableDate().getMinute() != 0){
            throw new MinuteException(Msg.MINUTE_NOT_VALID);
        }
        return this.appointmentRepo.save(appointment);
    }

    //bir gun eksik fazla hatasi almamk icin icerde cast donuyor
    public List<Appointment> findByDateAndAnimal (Long animalId, LocalDate start, LocalDate finish){
        List<Appointment> appointmentList;
        if (animalId == null){
            appointmentList = appointmentRepo.findByAvailableDateBetween(start.atStartOfDay(), finish.atTime(LocalTime.MAX));
        }else {
            appointmentList = appointmentRepo.findByAnimalIdAndAvailableDateBetween(animalId, start.atStartOfDay(), finish.atTime(LocalTime.MAX));
        }
        if (appointmentList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return appointmentList;
    }
    public List<Appointment> findByDateAndDoctor (Long doctorId, LocalDate start, LocalDate finish) {
        List<Appointment> appointmentList;
        if (doctorId == null) {
            appointmentList = appointmentRepo.findByAvailableDateBetween(start.atStartOfDay(), finish.atTime(LocalTime.MAX));
        }else {
            appointmentList = appointmentRepo.findByDoctorIdAndAvailableDateBetween(doctorId, start.atStartOfDay(), finish.atTime(LocalTime.MAX));
        }
        if (appointmentList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return appointmentList;
    }


    @Override
    public Appointment get(long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepo.findAll();
    }

    @Override
    public Appointment update(Appointment appointment) {
        Optional<Appointment> updateAppointment = appointmentRepo.findById(appointment.getId());
        if (updateAppointment.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.save(appointment);    }

    @Override
    public boolean delete(long id) {
        Optional<Appointment> appointmentToDelete = appointmentRepo.findById(id);

        if (appointmentToDelete.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }else {
            appointmentRepo.delete(appointmentToDelete.get());
            return true;
        }
    }
}
