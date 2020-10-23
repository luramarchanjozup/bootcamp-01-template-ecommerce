package com.zup.mercadolivre.produto.opiniao;

import java.util.Collection;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {
    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Collection<T> mapOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toList());
    }

    public double media() {
        Collection<Integer> notas = mapOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }

    public int totalDeOpinioes() {
        return this.opinioes.size();
    }
}
