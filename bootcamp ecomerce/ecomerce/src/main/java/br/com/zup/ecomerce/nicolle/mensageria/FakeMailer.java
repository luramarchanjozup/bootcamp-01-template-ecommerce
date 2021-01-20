package br.com.zup.ecomerce.nicolle.mensageria;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zup.ecomerce.nicolle.model.Usuario;

@Component
@Primary
public class FakeMailer implements Mailer {

	@Override
	public void send(String body, String subject, String nameFrom, String from, String to) {
		System.out.println(body);
		System.out.println(subject);
		System.out.println(nameFrom);
		System.out.println(from);
		System.out.println(to);
		
	}

}
