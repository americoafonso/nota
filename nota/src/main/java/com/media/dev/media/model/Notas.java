package com.media.dev.media.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notas {
    private float n1;

    private float n2;

    private float n3;

    private float n4;

    private Float notaExame;

    private float media;

    private Float mediaFinal;

    private String mensagem;
}
