package br.com.treino.ecommerce.shared.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fakeMailer")
public class FakeMailer implements Mailer{

    @Override
    public void enviar(String emailInteressado, String emailProvedor, String emailDono, String mensagem) {
        System.out.println("<html>");
        System.out.println("Provedor: ");
        System.out.println(emailProvedor);
        System.out.println("De: ");
        System.out.println(emailInteressado);
        System.out.println("Para: ");
        System.out.println(emailDono);
        System.out.println("Mensagem: ");
        System.out.println(mensagem);
        System.out.println("</html>");
    }

}
