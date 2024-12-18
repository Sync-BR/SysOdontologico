package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CLIENTS")
public class ClientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String cep;
    private String address;
    private int houseNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDto user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicDto clinic;

    public ClientDto() {
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cep='" + cep + '\'' +
                ", address='" + address + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
