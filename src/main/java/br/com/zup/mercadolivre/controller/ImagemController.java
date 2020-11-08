package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.dto.request.ImagemRequestDTO;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.model.UsuarioLogado;
import br.com.zup.mercadolivre.utils.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/produto/adicionar_imagens")
public class ImagemController {

    @PersistenceContext
    EntityManager entityManager;

    @PutMapping("/{produtoID}")
    @Transactional
    public ResponseEntity adicionarImagemAoProduto(@RequestBody @Valid ImagemRequestDTO request,
                                                        @PathVariable Long produtoID,
                                                        @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Produto produto = entityManager.find(Produto.class, produtoID);
        Usuario usuario = usuarioLogado.get();

        if(produto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Arrays.asList("Produto não encontrado")));
        }

        if(!produto.verificarSeEOProprietarioDoProduto(usuario.getLogin())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(Arrays.asList("O usuário não tem permissão para alterar esse produto")));
        }

        produto.adicionarListaDeImagensNoProduto(request.getImagens());

        entityManager.merge(produto);

        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

}
