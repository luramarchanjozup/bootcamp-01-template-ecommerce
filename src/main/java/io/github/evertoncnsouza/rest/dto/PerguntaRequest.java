package io.github.evertoncnsouza.rest.dto;

import io.github.evertoncnsouza.domain.entity.Pergunta;
import io.github.evertoncnsouza.domain.entity.Produto;
import io.github.evertoncnsouza.domain.entity.Usuario;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//3 PCI's
public class PerguntaRequest {

        @NotBlank
        private String titulo;

    public PerguntaRequest(@NotBlank String titulo,
                           @NotNull @Valid Long idProduto) {
        super();
        this.titulo = titulo;
      }

    public Pergunta toModel(Produto produto, Usuario navegador) {
        return new Pergunta(titulo, produto, navegador, LocalDateTime.now());
    }
}
