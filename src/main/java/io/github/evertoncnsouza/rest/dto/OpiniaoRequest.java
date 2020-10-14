package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Opiniao;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.User;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//3 PCI's
public class OpiniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;


    public OpiniaoRequest(@Min(1) @Max(5) int nota,
                          @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao, Long idProduto) {
        super();
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
       }

    public Opiniao toModel(Produto produto, User navegador){
        return new Opiniao(nota, titulo, descricao ,produto, navegador);
    }
}
