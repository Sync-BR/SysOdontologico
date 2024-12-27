package com.sync.sysodontologico.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "CONSULT")
public class ConsultDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patientName;
    private String patientCpf;
    private String dentistName;
    private String dentistCpf;
    private int clinicId;
    private LocalDateTime consultDate;

    public ConsultDto() {
    }

    @Override
    public String toString() {
        return "ConsultDto{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", patientCpf='" + patientCpf + '\'' +
                ", dentistName='" + dentistName + '\'' +
                ", dentistCpf='" + dentistCpf + '\'' +
                ", clinicId=" + clinicId +
                ", consultDate=" + consultDate +
                '}';
    }
}
