package br.com.ecommerce.finalizacompra;

/**
 * Todo evento relacioando ao sucesso de uma nova compra deve implementar
 * essa interface
 * @author albertoluizsouza
 *
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);

}
