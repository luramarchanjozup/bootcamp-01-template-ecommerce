package br.com.zup.mercadolivre.utils;

import java.util.Collection;

public class Error {

    private Collection<String> message;

    public Error(Collection<String> message) {
        this.message = message;
    }

    public Collection<String> getMessage() {
        return message;
    }
}

