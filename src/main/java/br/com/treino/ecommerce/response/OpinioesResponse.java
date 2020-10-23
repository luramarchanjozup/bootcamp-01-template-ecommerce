package br.com.treino.ecommerce.response;

import br.com.treino.ecommerce.model.Opiniao;

import java.util.List;
import java.util.OptionalDouble;

public class OpinioesResponse {

    private List<Opiniao> opinioes; //1

    public OpinioesResponse(List<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public double media(){
        OptionalDouble notas = this.opinioes.stream()
                .map(o -> o.getNota())
                .mapToInt(nota -> nota).average(); //2 //3
        return notas.orElse(0.0);
    }

    public int total(){
        return this.opinioes.size();
    }

}
