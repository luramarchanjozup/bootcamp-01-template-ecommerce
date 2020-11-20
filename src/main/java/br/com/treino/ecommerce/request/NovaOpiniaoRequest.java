package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Opiniao;
import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaOpiniaoRequest {

    private @Min(1) @Max(5) int nota;
    private @NotBlank String titulo;
    private @NotBlank @Length(max=500) String descricao;

    public NovaOpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
                              @NotBlank @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toOpiniao(@NotNull Produto produto, @NotNull Usuario consumidor){ //1 //2

        return new Opiniao(this.nota, this.descricao,
                this.titulo,produto, consumidor);
    }
}
