package com.github.marcoscoutozup.ecommerce.validator.gatewaypagamento;

import com.github.marcoscoutozup.ecommerce.compra.enums.GatewayDePagamento;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GatewayPagamentoValidator implements ConstraintValidator<GatewayPagamento, String> {

    @Override
    public void initialize(GatewayPagamento constraintAnnotation) {

    }

    @Override
    public boolean isValid(String gatewayDePagamento, ConstraintValidatorContext constraintValidatorContext) {
        return GatewayDePagamento.validateGatewayDePagamento(gatewayDePagamento);
    }
}
