package br.com.zup.ecommerce.service.email;

public interface EnviarEmail {
    void enviarEmail(String emailPara, String assunto, String mensagem);
}
