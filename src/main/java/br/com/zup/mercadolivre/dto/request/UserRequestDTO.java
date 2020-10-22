package br.com.zup.mercadolivre.dto.request;

import br.com.zup.mercadolivre.annotations.UniqueValue;
import br.com.zup.mercadolivre.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequestDTO {

    @NotBlank(message = "is required") @Email(message = "Invalid email address")
    @UniqueValue(domainClass = User.class, fieldName = "login", message = "already registered")
    private String login;

    @NotBlank (message = "is required") @Length(min = 6, message = "minimum size 6")
    private String password;


    @Deprecated
    public UserRequestDTO() {
    }

    public UserRequestDTO(@NotBlank(message = "is required") @Email(message = "Invalid email address") String login, @NotBlank(message = "is required") @Length(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel() {
        return new User(this.login, this.password);
    }
}
