package br.com.treino.ecommerce.controller;

import br.com.treino.ecommerce.model.Produto;
import br.com.treino.ecommerce.request.NovaImagemRequest;
import br.com.treino.ecommerce.shared.UsuarioLogado;
import br.com.treino.ecommerce.shared.util.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/produtos")
public class ImagemProdutoController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UploaderFake uploaderFake; //1

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity novaImagemProduto(@PathVariable("id") Long id,
                                            @Valid NovaImagemRequest request,
                                            @AuthenticationPrincipal UsuarioLogado usuarioLogado){ //2 //3

        Optional<Produto> possivelProduto =
                Optional.ofNullable(entityManager.find(Produto.class, id)); //4

        if(possivelProduto.isEmpty()){ //5
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (!possivelProduto.get().produtoPertenceUsuario(usuarioLogado.get().getEmail())){ //6
            return new ResponseEntity(HttpStatus.FORBIDDEN);
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.enviar(request.getImagens());

        possivelProduto.get().associarImagem(links);
        entityManager.merge(possivelProduto.get());

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
