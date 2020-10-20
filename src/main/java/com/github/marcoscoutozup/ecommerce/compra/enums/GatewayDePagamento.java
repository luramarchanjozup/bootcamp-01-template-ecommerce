package com.github.marcoscoutozup.ecommerce.compra.enums;

import com.github.marcoscoutozup.ecommerce.compra.pagamento.Pagamento;

import java.util.stream.Stream;

public enum GatewayDePagamento {

                //1
    PAYPAL((id, urlBase) -> "paypal.com/" + id + "?redirectUrl=" + urlBase + "/pagamento/paypal/" + id),
                    //2
    PAGSEGURO((id, urlBase) -> "pagseguro.com/?returnId=" + id + "&redirectUrl=" + urlBase + "/pagamento/pagseguro/" + id);

            //3
    public Pagamento instanciaDoGatewayDePagamento;

    GatewayDePagamento(Pagamento pagamento) {
        instanciaDoGatewayDePagamento = pagamento;
    }

    public static GatewayDePagamento converterStringParaGatewayDePagamento(String valor){
        return Enum.valueOf(GatewayDePagamento.class, valor);
    }

    public static boolean validateGatewayDePagamento(String gatewayDePagamento){ //4
        return Stream.of(GatewayDePagamento.class.getFields()).anyMatch(field -> field.getName().equals(gatewayDePagamento));
    }
}
