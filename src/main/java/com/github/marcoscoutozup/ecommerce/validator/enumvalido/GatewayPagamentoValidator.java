package com.github.marcoscoutozup.ecommerce.validator.enumvalido;

import com.github.marcoscoutozup.ecommerce.compra.enums.GatewayDePagamento;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class GatewayPagamentoValidator implements ConstraintValidator<GatewayPagamento, String> {

    @Override
    public void initialize(GatewayPagamento constraintAnnotation) {

    }

    @Override
    public boolean isValid(String gatewayDePagamento, ConstraintValidatorContext constraintValidatorContext) {
        return GatewayDePagamento.validateGatewayDePagamento(gatewayDePagamento);
    }
}
