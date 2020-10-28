package br.com.mercadolivre.components;

import org.springframework.stereotype.Component;

//Contagem de Pontos - TOTAL:0
@Component
public class GerenciadorEmail {

	/**
	 * 
	 * @param corpo     corpo do email
	 * @param assunto   assunto do email
	 * @param provedor  nome para aparecer no provedor de email
	 * @param de        email de origem
	 * @param para      email de destino
	 */
	
	
	public void enviar(String corpo, String assunto, String provedor, String de, String para) {
		System.out.println(corpo);
		System.out.println(assunto);
		System.out.println(provedor);
		System.out.println(de);
		System.out.println(para);
	}

}
