package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CLINIC")
public class ClinicDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clinicName;
    private String clinicCep;
    private String clinicAddress;
    private String clinicNumber;
    private String clinicPhone;
    @OneToOne(mappedBy = "clinic")
    private ClientDto client;


    @Override
    public String toString() {
        return "ClinicModel{" +
                "clinicName='" + clinicName + '\'' +
                ", clinicCep='" + clinicCep + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                ", clinicNumber='" + clinicNumber + '\'' +
                ", clinicPhone='" + clinicPhone + '\'' +
                '}';
    }
}
