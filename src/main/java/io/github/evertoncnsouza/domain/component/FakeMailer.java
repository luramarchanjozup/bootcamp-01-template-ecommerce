package io.github.evertoncnsouza.domain.component;

import io.github.evertoncnsouza.domain.repository.Mailer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//1 PCI;
@Component
@Primary
public class FakeMailer implements Mailer {

    @Override
    public void send(@NotBlank String body,
                     @NotBlank String subject,
                     @NotBlank String nameFrom,
                     @NotBlank @Email String from,
                     @NotBlank @Email String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }
}
