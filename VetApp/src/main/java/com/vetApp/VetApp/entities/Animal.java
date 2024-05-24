package com.vetApp.VetApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private long id;

    @Column(name = "animal_name")
    private String name;

    @Column(name = "animal_species")
    private String species;

    @Column(name = "animal_breed")
    private String breed;

    @Column(name = "animal_gender")
    private String gender;

    @Column(name = "animal_colour")
    private String colour;

    @Column(name = "animal_birthdate")
    private LocalDate dateOfBirth;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "animal",  cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Appointment> appointments;

    public Animal(String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, Customer customer) { //FOR MANUAL MAPPING MODEL MAPPER DID NOT WORKED
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.customer = customer;
    }
}
