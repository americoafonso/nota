package com.media.dev.media.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class NotasDto {
    @NonNull
    private float n1;

    @NonNull
    private float n2;

    @NonNull
    private float n3;

    @NonNull
    private float n4;

    private Float notaExame;
}
