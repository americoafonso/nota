package com.media.dev.media.service.impl;

import com.media.dev.media.dto.NotasDto;
import com.media.dev.media.model.Notas;
import com.media.dev.media.service.NotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotaServiceImpl implements NotaService {
    private static final float PESO_NOTA_1 = 2f;
    private static final float PESO_NOTA_2 = 3f;
    private static final float PESO_NOTA_3 = 4f;
    private static final float PESO_NOTA_4 = 1f;

    @Override
    public Notas avaliarCondicaoAluno(NotasDto notasDto) {
        final var notas = new Notas();

        if (notasDto.getNotaExame() != null) {
            notas.setNotaExame(notasDto.getNotaExame());
        }

        calcularPesoNotas(notas, notasDto);

        notas.setMedia(calcularMedia(notas));

        construirMensagem(notas);

        logNotas(notas);

        return notas;
    }

    private static void calcularPesoNotas(Notas notas, NotasDto notasDto) {
        log.info("Nota 1 o peso é {}", PESO_NOTA_1);
        notas.setN1(notasDto.getN1() * PESO_NOTA_1);

        log.info("Nota 2 o peso é {}", PESO_NOTA_2);
        notas.setN2(notasDto.getN2() * PESO_NOTA_2);

        log.info("Nota 3 o peso é {}", PESO_NOTA_3);
        notas.setN3(notasDto.getN3() * PESO_NOTA_3);

        log.info("Nota 4 o peso é {}", PESO_NOTA_4);
        notas.setN4(notasDto.getN4() * PESO_NOTA_4);
    }

    private static float calcularMedia(Notas notas) {
        var media = notas.getN1() + notas.getN2() + notas.getN3() + notas.getN4();
        media = media / calcularPesos();
        return media;
    }

    private static float calcularPesos() {
        return PESO_NOTA_1 + PESO_NOTA_2 + PESO_NOTA_3 + PESO_NOTA_4;
    }

    private static void construirMensagem(Notas notas) {
        if (notas.getMedia() >= 7.0f) {
            notas.setMensagem(String.format("Média: %.1f. Aluno aprovado.", notas.getMedia()));
        } else if (notas.getMedia() < 5.0f) {
            notas.setMensagem(String.format("Média: %.1f. Aluno reprovado.", notas.getMedia()));
        } else if (notas.getMedia() >= 5.0f && notas.getMedia() <= 6.9f) {
            notas.setMensagem(String.format("Média: %.1f. Aluno em exame.", notas.getMedia()));
            if (notas.getNotaExame() != null) {
                calcularNotaExame(notas);
            }
        } else {
            log.error("Erro ao calcular nota: não foi possível definir a condição do aluno. Média do aluno {}", notas.getMedia());
            throw new IllegalArgumentException("Erro ao calcular nota: não foi possível definir a condição do aluno. Média do aluno " + notas.getMedia());
        }
    }

    private static void calcularNotaExame(Notas notas) {
        final var mediaAtual = notas.getMedia() + notas.getNotaExame();
        notas.setMediaFinal(mediaAtual / 2f);

        resultadoNotaFinal(notas);
    }

    private static void resultadoNotaFinal(Notas notas) {
        var mensagem = String.format("Nota do exame: %.1f. ", notas.getNotaExame());
        if (notas.getMediaFinal() >= 5.0f) {
            mensagem = mensagem + "Aluno aprovado.";
        } else if (notas.getMediaFinal() <= 4.9f) {
            mensagem = mensagem + "Aluno reprovado.";
        }
        mensagem = mensagem + String.format(" Média final: %.1f", notas.getMediaFinal());
        notas.setMensagem(mensagem);
    }

    private static void logNotas(Notas notas) {
        log.info("Nota 1: {}", notas.getN1());
        log.info("Nota 2: {}", notas.getN2());
        log.info("Nota 3: {}", notas.getN3());
        log.info("Nota 4: {}", notas.getN4());
        log.info("Média: {}", notas.getMedia());

        if (notas.getNotaExame() != null) {
            log.info("Nota exame: {}", notas.getNotaExame());
        }
        if (notas.getMediaFinal() != null) {
            log.info("Média final: {}", notas.getMediaFinal());
        }

        log.info("Mensagem: {}", notas.getMensagem());
    }
}
