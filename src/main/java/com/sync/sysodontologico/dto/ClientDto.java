package com.sync.sysodontologico.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sync.sysodontologico.enums.SubscriptionsType;
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
    private String cpf;
    private String phone;
    private String cep;
    private String address;
    private int houseNumber;
    private SubscriptionsType subscriptionsType;
    private boolean isActive;
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
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", cep='" + cep + '\'' +
                ", address='" + address + '\'' +
                ", houseNumber=" + houseNumber +
                ", subscriptionsType=" + subscriptionsType +
                ", userId=" + (user != null ? user.getId() : "N/A") +
                ", clinicId=" + (clinic != null ? clinic.getId() : "N/A") +
                '}';
    }


}
