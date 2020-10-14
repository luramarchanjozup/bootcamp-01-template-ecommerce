package com.github.marcoscoutozup.ecommerce.compra.enums;

import com.github.marcoscoutozup.ecommerce.compra.pagamento.Pagamento;
import com.github.marcoscoutozup.ecommerce.compra.pagamento.Pagseguro;
import com.github.marcoscoutozup.ecommerce.compra.pagamento.Paypal;

import java.util.stream.Stream;

public enum GatewayDePagamento {

                //1
    PAYPAL(new Paypal()),
                    //2
    PAGSEGURO(new Pagseguro());

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
