package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Caracteristica;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovaCaracteristicaRequest {

    private @NotBlank String titulo;
    private @NotBlank String especificacao;

    public NovaCaracteristicaRequest(@NotBlank String titulo,
                                     @NotBlank String especificacao) {
        this.titulo = titulo;
        this.especificacao = especificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Caracteristica toCaracteristica(){
        return new Caracteristica(this.titulo,
                this.especificacao);
    }

}
