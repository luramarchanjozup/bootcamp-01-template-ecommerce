package br.com.zup.ecommerce.service.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Contagem de carga intrínseca da classe: 0
 */

@Component
@Primary
public class EnviarEmailFake implements EnviarEmail {
    @Override
    public void enviarEmail(String emailPara, String assunto, String mensagem) {
        System.out.println("**********************************************");
        System.out.println("**********************************************");
        System.out.println("Para: " + emailPara);
        System.out.println("Assunto: "+assunto);
        System.out.println("Mensagem:\n" + mensagem);
        System.out.println("**********************************************");
        System.out.println("**********************************************");
    }
}
