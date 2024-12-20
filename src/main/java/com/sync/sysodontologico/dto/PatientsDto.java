package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicDto clinic;

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
