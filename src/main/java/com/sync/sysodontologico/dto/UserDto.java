package com.sync.sysodontologico.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "LOGIN")
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @OneToOne(mappedBy = "user")
    private ClientDto client;
    @OneToOne(mappedBy = "user")
    private DentistDto dentist;

    public UserDto() {
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clientId=" + (client != null ? client.getId() : "N/A") +
                ", dentisId=" + (dentist != null ? dentist.getId() : "N/A") +
                '}';
    }

}
