package com.sync.sysodontologico.enums;

import lombok.Getter;

@Getter
public enum SubscriptionsType {
    bronze("Bronze"),
    basic("Basic"),
    premium("Premium"),;

    private final String name;
    private final int value;

    SubscriptionsType(String name) {
        this.name = name;
        this.value = ordinal();
    }
}
