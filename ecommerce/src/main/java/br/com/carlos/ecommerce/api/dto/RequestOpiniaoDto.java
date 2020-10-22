package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.Opiniao;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.entity.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestOpiniaoDto {

    @Min(1) @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    public RequestOpiniaoDto(@NotBlank @Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toEntity(Produto produto, Usuario comprador){
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, comprador);
    }
}
