package com.sync.sysodontologico.enums;

public enum SubscriptionsType {
    bronze("Bronze"),
    basic("Basic");

    private final String name;
    private final int value;

    SubscriptionsType(String name) {
        this.name = name;
        this.value = ordinal();
    }
}
