package br.com.zup.ecommerce.controllers;

import br.com.zup.ecommerce.security.LogingInputDto;
import br.com.zup.ecommerce.security.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

/**
 * Contagem de carga intrínseca da classe: 4
 */

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoUsuarioController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    //1
    private TokenManager tokenManager;

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoUsuarioController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //1
    public ResponseEntity<String> autenticar(@RequestBody LogingInputDto logingInputDto) {
        UsernamePasswordAuthenticationToken authenticationToken = logingInputDto.build();

        //2
        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jwt = tokenManager.generateToken(authentication);

            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            log.error("[Autenticacao] { }", e);
            return ResponseEntity.badRequest().build();
        }
    }




}
