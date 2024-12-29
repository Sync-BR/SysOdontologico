package com.sync.sysodontologico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "PATIENTS")
public class PatientsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cpf;
    private String gender;
    private LocalDate dateOfBirth;
    private String telephone;
    private String email;
    private String address;
    private String cep;
    private int houseNumber;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicDto clinic;
    @JsonIgnore
    @OneToMany(mappedBy = "patients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamDto> exams;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "id")
    private DentistDto dentist;

    public PatientsDto() {
    }

    @Override
    public String toString() {
        return "PatientsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", cep='" + cep + '\'' +
                ", houseNumber=" + houseNumber +
                ", clinic=" + clinic +
                '}';
    }
}
