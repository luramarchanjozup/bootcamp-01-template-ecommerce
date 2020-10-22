package io.github.evertoncnsouza.domain.enums;

import io.github.evertoncnsouza.domain.entity.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro {
        @Override
        public String criaUrlRetorno(Compra compra,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    paypal {
        @Override
        public String criaUrlRetorno(Compra compra,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();
            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    public abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);

}
