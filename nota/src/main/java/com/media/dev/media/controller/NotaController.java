package com.media.dev.media.controller;

import com.media.dev.media.dto.NotasDto;
import com.media.dev.media.model.Notas;
import com.media.dev.media.service.NotaService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping("/condicao-aluno")
    @ResponseStatus(HttpStatus.OK)
    public Notas avaliarCondicaoAluno(@NonNull @RequestBody NotasDto notasDto) {
        return notaService.avaliarCondicaoAluno(notasDto);
    }
}
