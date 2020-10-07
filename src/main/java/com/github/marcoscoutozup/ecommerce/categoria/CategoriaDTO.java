package com.github.marcoscoutozup.ecommerce.categoria;

import com.github.marcoscoutozup.ecommerce.validator.existeid.ExisteId;
import com.github.marcoscoutozup.ecommerce.validator.valorunico.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class CategoriaDTO {

    @NotBlank
    @ValorUnico(campo = "nome", classe = Categoria.class)
    private String nome;

    @ExisteId(classe = Categoria.class)
    private UUID categoria;

            //1
    public Categoria toModel(EntityManager entityManager){
        Categoria categoria = new Categoria(nome);

        //2
        if(this.categoria != null){
            Categoria superCategoria = entityManager.find(Categoria.class, this.categoria);
            categoria.setCategoria(superCategoria);
        }

        return categoria;
    }

    public UUID getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(UUID categoria) {
        this.categoria = categoria;
    }
}
