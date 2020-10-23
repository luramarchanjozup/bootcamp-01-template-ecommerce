package br.com.carlos.ecommerce.api.dto;

import br.com.carlos.ecommerce.domain.entity.Pergunta;
import br.com.carlos.ecommerce.domain.entity.Produto;
import br.com.carlos.ecommerce.domain.entity.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class RequestPerguntaDto {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toEntity(@NotNull @Valid Produto produto, @NotNull @Valid Usuario interessada){
        return new Pergunta(titulo, produto, interessada );
    }
}
