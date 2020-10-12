package br.com.ecommerce.cadastrousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest){

        Usuario usuario = cadastroUsuarioRequest.converterParaTipoUsuario();

        usuarioRepository.save(usuario);

        return ResponseEntity
                .ok()
                .build();

    }
}
