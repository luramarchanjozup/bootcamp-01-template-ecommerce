package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Opiniao;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//1 PCI
public class SiteOpiniaoResponse {

    private Set<Opiniao> opinioes;

    public SiteOpiniaoResponse(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;

    }
    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaMapeadora) {
        return this.opinioes.stream().map(funcaMapeadora)
                .collect(Collectors.toSet());
    }
    public double media() {
        Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }
    public int total() {
        return this.opinioes.size();
    }
}
