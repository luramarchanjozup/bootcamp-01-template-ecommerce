package com.zup.mercadolivre.model.enums;

public enum PagseguroStatus {
    SUCESSO, ERRO;

	public TransactionStatus normalize() {
		if (this.equals(SUCESSO)) {
            return TransactionStatus.SUCCESS;
        } 

        return TransactionStatus.ERROR;
	}
}
