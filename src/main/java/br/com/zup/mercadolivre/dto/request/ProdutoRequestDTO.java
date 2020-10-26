package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.annotations.ExistsValue;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequestDTO {

    @NotBlank(message = "o nome deve ser preenchido")
    private String nome;

    @Positive
    @NotNull(message = "o valor deve ser preenchido")
    private BigDecimal valor;

    @Positive(message = "a quantidade deve ser positiva")
    private int quantidade;

    @NotEmpty(message = "a descrição deve ser preenchida")
    @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres")
    private String descricao;

    @ExistsValue(domainClass = Categoria.class, fieldName = "id", message = "não foi encontrada essa categoria")
    private Long categoriaId;

    @Size(min = 3, message = "são necessarias pelo menos 3 caracteristicas")
    private List<CaracteristicaProdutoRequestDTO> caracteristicas = new ArrayList<>();

    public ProdutoRequestDTO(@NotBlank(message = "o nome deve ser preenchido") String nome, @Positive @NotNull(message = "o valor deve ser preenchido") BigDecimal valor, @Positive(message = "a quantidade deve ser positiva") int quantidade, @NotEmpty(message = "a descrição deve ser preenchida") @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres") String descricao, Long categoriaId, @Size(min = 3, message = "são necessarias pelo menos 3 caracteristicas") List<CaracteristicaProdutoRequestDTO> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas = caracteristicas;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public Produto toModel (Categoria categoria, Usuario usuario) {
        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria, usuario);
    }
}
