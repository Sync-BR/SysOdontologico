package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name = "HISTORY")
public class HistoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idExam;
    private int idPatient;
    private int idDoctor;
    private int idClinic;
    private String exam;
    private LocalDate examDate;

    public HistoryDto() {
        examDate = LocalDate.now();
     }

    public HistoryDto(int idExam, int idPatient, int idDoctor, String exam, int idClinic) {
        this.idExam = idExam;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.exam = exam;
        this.idClinic = idClinic;
        examDate = LocalDate.now();
    }
}
