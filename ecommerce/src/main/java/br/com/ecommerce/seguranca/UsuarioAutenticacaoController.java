package br.com.ecommerce.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioAutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private GeradorToken geradorToken;

    
    @PostMapping
    public ResponseEntity<TokenResponse> authenticate(@RequestBody UsuarioAutenticacaoRequest loginUsuario) {

        UsernamePasswordAuthenticationToken authenticationToken = loginUsuario.build();

        try {

            Authentication authentication = authManager.authenticate(authenticationToken);

            String JWT = geradorToken.geraToken(authentication);

            return ResponseEntity
                    .ok(new TokenResponse(JWT));

        } catch (AuthenticationException e) {

            return ResponseEntity
                    .badRequest()
                    .build();

        }
    }
}
