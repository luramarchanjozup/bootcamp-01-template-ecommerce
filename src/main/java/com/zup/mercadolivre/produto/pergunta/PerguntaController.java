package com.zup.mercadolivre.produto.pergunta;

import com.zup.mercadolivre.email.Emails;
import com.zup.mercadolivre.produto.Produto;
import com.zup.mercadolivre.usuario.Usuario;
import com.zup.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public String cadastrarPergunta(@Valid @RequestBody NovaPerguntaRequest request, @PathVariable Long id,
                                  Authentication authentication) {
        Usuario perguntante = usuarioRepository.findByEmail(authentication.getName());
        Produto produto = manager.find(Produto.class, id);
        Pergunta novaPergunta = request.toModel(perguntante, produto);
        manager.persist(novaPergunta);

        emails.novaPergunta(novaPergunta);

        return novaPergunta.toString();
    }
}
