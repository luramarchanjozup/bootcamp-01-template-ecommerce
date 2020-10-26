package br.com.carlos.ecommerce.api.handler;

import java.util.Collection;

public class ErroPadronizado {
    private final Collection<String> mensagens;

    public ErroPadronizado(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
