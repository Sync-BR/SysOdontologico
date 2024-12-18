package com.sync.sysodontologico.model;

import lombok.Getter;

@Getter
public class ApplicationModel {
    private String name;
    private Long version;

    public ApplicationModel() {
        this.name = "Sistema de clinica Odontológico";
        this.version = 1L;
    }
}
