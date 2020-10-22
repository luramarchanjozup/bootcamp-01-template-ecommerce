package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.entities.produto.Produto;
import br.com.zup.ecommerce.entities.produto.imagem.ImagensNovoRequest;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProduto;
import br.com.zup.ecommerce.entities.produto.opiniao.OpiniaoProdutoNovoRequest;
import br.com.zup.ecommerce.entities.produto.pergunta.PerguntaProduto;
import br.com.zup.ecommerce.entities.produto.pergunta.PerguntaProdutoNovoRequest;
import br.com.zup.ecommerce.service.produto.AtualizacaoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

/**
 * Contagem de carga intrínseca da classe: 7
 */

@RestController
@RequestMapping("/produtos/{id}")
public class ProdutoAtualizacaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    //1
    private AtualizacaoProduto atualizacaoProduto;

    @PostMapping("/imagens")
    @Transactional
    //1
    public ResponseEntity<String> envioImagens(@PathVariable("id") Long id, @Valid ImagensNovoRequest novasImagens, @AuthenticationPrincipal UserDetails user) {

        //1
        Produto produto = atualizacaoProduto.getProduto(id,manager);

        //Lança uma ResponseStatusException se usuário for inválido
        atualizacaoProduto.validaDonoProduto(manager, produto, user);

        //Envia as imagens para um repositorio e retorna a lista dos links dessas imagens
        Set<String> links = atualizacaoProduto.enviaImagem(novasImagens);
        produto.incluirImagens(links);

        manager.merge(produto);

        return ResponseEntity.ok(links.toString());
    }

    @PostMapping("/opinioes")
    @Transactional
    //1
    public ResponseEntity<String> envioOpnioes(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoProdutoNovoRequest novaOpiniao, @AuthenticationPrincipal UserDetails user) {

        Produto produto = atualizacaoProduto.getProduto(id,manager);
        //1
        OpiniaoProduto opiniao = novaOpiniao.toModelSemProduto(atualizacaoProduto.getUsuarioLogado(user));

        produto.incluirOpinioes(opiniao);
        manager.merge(produto);

        Long opiniaoId = produto.getOpinioes().get(produto.getOpinioes().size() - 1).getId();
        return ResponseEntity.ok("Opinião " + opiniaoId  +" cadastrada.");
    }

    @PostMapping("/perguntas")
    @Transactional
    //1
    public ResponseEntity<String> envioPerguntas(@PathVariable("id") Long id, @RequestBody @Valid PerguntaProdutoNovoRequest novasPerguntas, @AuthenticationPrincipal UserDetails user) {
        Produto produto = atualizacaoProduto.getProduto(id,manager);

        //1
        PerguntaProduto pergunta = novasPerguntas.toModelSemProduto(atualizacaoProduto.getUsuarioLogado(user));
        produto.incluirPerguntas(pergunta);

        manager.merge(produto);

        //Envia e-mail da pergunta para o dono do produto
        atualizacaoProduto.enviaEmail(produto, pergunta.getTitulo());

        //Definir tipo application/json
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok().headers(httpHeaders).body(produto.getPerguntas().toString());
    }

}
