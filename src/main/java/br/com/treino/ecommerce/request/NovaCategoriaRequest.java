package br.com.treino.ecommerce.request;

import br.com.treino.ecommerce.model.Categoria;
import br.com.treino.ecommerce.shared.validations.ExisteValor;
import br.com.treino.ecommerce.shared.validations.ValorUnico;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @ValorUnico(nomeCampo = "nome", nomeClasse = Categoria.class,
            message = "Essa categoria já existe") //1
    private @NotBlank String nome;
    @ExisteValor(nomeCampo = "id", nomeClasse = Categoria.class,
            message = "Essa categoria mãe ainda não foi cadastrada") //2
    private Long idCategoriaMae;

    public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toCategoria(EntityManager entityManager){

        Categoria categoria = new Categoria(this.nome); //1

        Optional<Long> possivelid = Optional.ofNullable(idCategoriaMae);

        if(possivelid.isPresent()){
            Categoria categoriaMae = entityManager.find(Categoria.class, possivelid.get());
            categoria.setCategoriaMae(categoriaMae);
        }

        return categoria;
    }
}
