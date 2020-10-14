package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Pergunta;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//1 PCI
public class SitePerguntaResponse {

    private Set<Pergunta> perguntas;

    public SitePerguntaResponse(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;    }

    public <T> Set<T> mapeiaPerguntas(
            Function<Pergunta, T> funcaMapeadora) {
        return this.perguntas.stream().map(funcaMapeadora)
                .collect(Collectors.toSet());
    }
}
