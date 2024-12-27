package com.sync.sysodontologico.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ToothEnum {
    UPPER_RIGHT_1(11, "Incisor central direito"),
    UPPER_RIGHT_2(12, "Incisor lateral direito"),
    UPPER_RIGHT_3(13, "Canino direito"),
    UPPER_RIGHT_4(14, "Primeiro pré-molar direito"),
    UPPER_RIGHT_5(15, "Segundo pré-molar direito"),
    UPPER_RIGHT_6(16, "Primeiro molar direito"),
    UPPER_RIGHT_7(17, "Segundo molar direito"),
    UPPER_RIGHT_8(18, "Terceiro molar direito"),

    UPPER_LEFT_1(21, "Incisor central esquerdo"),
    UPPER_LEFT_2(22, "Incisor lateral esquerdo"),
    UPPER_LEFT_3(23, "Canino esquerdo"),
    UPPER_LEFT_4(24, "Primeiro pré-molar esquerdo"),
    UPPER_LEFT_5(25, "Segundo pré-molar esquerdo"),
    UPPER_LEFT_6(26, "Primeiro molar esquerdo"),
    UPPER_LEFT_7(27, "Segundo molar esquerdo"),
    UPPER_LEFT_8(28, "Terceiro molar esquerdo"),

    LOWER_LEFT_1(31, "Incisor central esquerdo"),
    LOWER_LEFT_2(32, "Incisor lateral esquerdo"),
    LOWER_LEFT_3(33, "Canino esquerdo"),
    LOWER_LEFT_4(34, "Primeiro pré-molar esquerdo"),
    LOWER_LEFT_5(35, "Segundo pré-molar esquerdo"),
    LOWER_LEFT_6(36, "Primeiro molar esquerdo"),
    LOWER_LEFT_7(37, "Segundo molar esquerdo"),
    LOWER_LEFT_8(38, "Terceiro molar esquerdo"),

    LOWER_RIGHT_1(41, "Incisor central direito"),
    LOWER_RIGHT_2(42, "Incisor lateral direito"),
    LOWER_RIGHT_3(43, "Canino direito"),
    LOWER_RIGHT_4(44, "Primeiro pré-molar direito"),
    LOWER_RIGHT_5(45, "Segundo pré-molar direito"),
    LOWER_RIGHT_6(46, "Primeiro molar direito"),
    LOWER_RIGHT_7(47, "Segundo molar direito"),
    LOWER_RIGHT_8(48, "Terceiro molar direito");

    private final int number;
    private final String description;

    ToothEnum(int number, String description) {
        this.number = number;
        this.description = description;
    }
}
