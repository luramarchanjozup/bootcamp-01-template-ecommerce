package com.zup.mercadolivre.model.enums;


import com.zup.mercadolivre.model.Purchase;

import org.springframework.web.util.UriComponentsBuilder;

public enum PaymentGateway {
    pagseguro {
        @Override
        public String createReturnUrl(Purchase purchase, UriComponentsBuilder builder) {
            String url = builder.path("/payment/pagseguro-response/{id}")
                .buildAndExpand(purchase.getId()).toString();

            return "pagseguro.com/" + purchase.getId() + "?redirectUrl=" + url;
        }
    }, paypal {
        @Override
        public String createReturnUrl(Purchase purchase, UriComponentsBuilder builder) {
            String url = builder.path("/payment/paypal-response/{id}")
                .buildAndExpand(purchase.getId()).toString();

            return "paypal.com/?returnId=" + purchase.getId() + "&redirectUrl=" + url;
        }
    };

    public abstract String createReturnUrl(Purchase purchase, UriComponentsBuilder builder);
}
