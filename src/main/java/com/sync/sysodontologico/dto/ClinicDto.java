package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private int clinicSubscription;
    @OneToOne(mappedBy = "clinic")
    private ClientDto client;
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<PatientsDto> patients;
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<DentistDto> dentists;
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<ExamDto> exams;

    @Override
    public String toString() {
        return "ClinicDto{" +
                "id=" + id +
                ", clinicName='" + clinicName + '\'' +
                ", clinicCep='" + clinicCep + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                ", clinicNumber='" + clinicNumber + '\'' +
                ", clinicPhone='" + clinicPhone + '\'' +
                ", clientId=" + (client != null ? client.getId() : "N/A") +
//                ", patientsCount=" + (patients != null ? patients.size() : "N/A") +
//                ", dentists=" + dentists +
                '}';
    }
}
