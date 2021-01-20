package br.com.zup.ecomerce.nicolle.mensageria;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.ecomerce.nicolle.model.Usuario;

public interface Mailer {
	//corpo do email, assunto do email, assunto do email, nome para aparecer no provedor e email, email de origem, email de destino
	void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom, @NotBlank @Email String from, @NotBlank @Email String to);

}
