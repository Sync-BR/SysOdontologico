package com.sync.sysodontologico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "DENTIST")
public class DentistDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDto user;
    @OneToMany(mappedBy = "dentist")
    private List<ExamDto> exams;
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientsDto> patients;

    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicDto clinic;

    @Override
    public String toString() {
        return "DentistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
