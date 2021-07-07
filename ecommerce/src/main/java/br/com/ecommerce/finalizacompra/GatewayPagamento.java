package br.com.ecommerce.finalizacompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    paypal {

        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {

            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }

    },
    pagseguro {

        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {

            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }

    };

    abstract String criaUrlRetorno(Compra compra,
                                   UriComponentsBuilder uriComponentsBuilder);

}
