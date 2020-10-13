package io.github.evertoncnsouza.rest.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    void send(@NotBlank String body, @NotBlank String subject,
              @NotBlank String nameFrom, @NotBlank @Email String from,
              @NotBlank @Email String to);
}

//body = corpo do email, subject = assunto,
//name = quem está enviando o email,
// from = Precisa ser liberado relativo ao seu domínio, para não haver fraudes.
//to = para quem vai o email,