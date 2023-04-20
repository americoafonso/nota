package com.media.dev.media.service;

import com.media.dev.media.dto.NotasDto;
import com.media.dev.media.model.Notas;

public interface NotaService {
    Notas avaliarCondicaoAluno(NotasDto notasDto);
}
