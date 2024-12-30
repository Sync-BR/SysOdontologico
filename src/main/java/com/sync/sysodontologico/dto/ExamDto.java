package com.sync.sysodontologico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sync.sysodontologico.enums.ToothEnum;
import com.sync.sysodontologico.enums.TypeExam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "EXAM")
public class ExamDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500)
    private String description;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @Column(length = 500)
    private TypeExam examType;
    @Column(nullable = true)
    private String mediaPatch;
    private ToothEnum tooth;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "id")
    private DentistDto dentist;
    @ManyToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private PatientsDto patients;
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicDto clinic;




    public ExamDto() {
        date =  LocalDate.now();
    }


    @Override
    public String toString() {
        return "ExamDto{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", examType=" + examType +
                ", mediaPatch='" + mediaPatch + '\'' +
                ", tooth=" + tooth.getDescription() +
                ", dentist=" + dentist +
                ", patients=" + patients +
                ", clinic=" + clinic +
                '}';
    }
}
