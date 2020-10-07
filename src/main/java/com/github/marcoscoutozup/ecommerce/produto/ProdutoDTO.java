package com.github.marcoscoutozup.ecommerce.produto;

import com.github.marcoscoutozup.ecommerce.caracteristica.Caracteristica;
import com.github.marcoscoutozup.ecommerce.caracteristica.CaracteristicaDTO;
import com.github.marcoscoutozup.ecommerce.categoria.Categoria;
import com.github.marcoscoutozup.ecommerce.usuario.Usuario;
import com.github.marcoscoutozup.ecommerce.validator.existeid.ExisteId;
import com.github.marcoscoutozup.ecommerce.validator.minimodeelementos.MinimoDeElementos;

import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProdutoDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ElementCollection
    @MinimoDeElementos(minimo = 3)
    @Valid          //1
    private List<CaracteristicaDTO> caracteristicas;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExisteId(classe = Categoria.class)
    private UUID categoria;

            //2
    public Produto toModel(EntityManager entityManager, String emailDoUsuario){
        //3
        Usuario usuario = entityManager.createNamedQuery("findUsuarioByEmail", Usuario.class)
                .setParameter("email", emailDoUsuario)
                .getSingleResult();
        //4
        Categoria categoria = entityManager.find(Categoria.class, this.categoria);

       return new Produto(nome, quantidade, converterListaDeCaracteristicas(), descricao, categoria, usuario);
    }
                    //5
    public List<Caracteristica> converterListaDeCaracteristicas(){
        //6
        return caracteristicas.stream().map(CaracteristicaDTO::toModel).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getCategoria() {
        return categoria;
    }

    public void setCategoria(UUID categoria) {
        this.categoria = categoria;
    }

}
