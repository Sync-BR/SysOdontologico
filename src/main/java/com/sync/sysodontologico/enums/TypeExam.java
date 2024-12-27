package com.sync.sysodontologico.enums;

import lombok.Getter;

@Getter
public enum TypeExam {
    LIMPEZA_DENTARIA("Limpeza Dentária (Profilaxia)"),
    RESTAURACAO_DENTARIA("Restauração Dentária (Obturação)"),
    RADIOGRAFIA_SIMPLES("Radiografia Simples"),
    RADIOGRAFIA_PANORAMICA("Radiografia Panorâmica (Ortopantomografia)"),
    EXAME_CLINICO_GERAL("Exame Clínico Geral"),
    EXAME_PERIODONTAL("Exame Periodontal"),
    TESTE_DE_VITALIDADE_PULPAR("Teste de Vitalidade Pulpar"),
    EXTRACAO_DENTARIA_SIMPLES("Extração Dentária Simples"),
    EXODONTIA_COMPLEXA("Exodontia Complexa"),
    CLAREAMENTO_DENTAL("Clareamento Dental"),
    RASPAGEM_SUBGENGIVAL("Raspagem Subgengival"),
    SELANTE_DENTAL("Selante Dental"),
    APLICACAO_DE_FLUOR("Aplicação de Flúor"),
    TRATAMENTO_DE_CANAL("Tratamento de Canal (Endodontia)"),
    EXAME_PARA_IMPLANTE_DENTARIO("Exame para Implante Dentário"),
    PLANEJAMENTO_ORTODONTICO("Planejamento Ortodôntico"),
    TESTE_DE_ATM("Teste de ATM (Articulação Temporomandibular)"),
    BIOPSIA_BUCAL("Biópsia Bucal"),
    APLICACAO_DE_ANTISSEPTICOS("Aplicação de Antissépticos"),
    CONSULTA_DE_RETORNO("Consulta de Retorno");

    private final String description;
    private  int value;
    TypeExam(String description) {
        this.description = description;
        value = ordinal();
    }

}
