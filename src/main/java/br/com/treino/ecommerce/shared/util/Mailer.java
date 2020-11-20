package br.com.treino.ecommerce.shared.util;

import org.springframework.stereotype.Component;

@Component
public interface Mailer {

    void enviar(String emailInteressado, String emailProvedor, String emailDono, String mensagem);

}
