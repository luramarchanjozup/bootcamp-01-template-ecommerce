package com.github.marcoscoutozup.ecommerce.compra.tentativadepagamento;

import com.github.marcoscoutozup.ecommerce.compra.Compra;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.ComunicacaoComSistemas;
import com.github.marcoscoutozup.ecommerce.compra.comunicacao.OutrosSistemasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TentativaDePagamentoService {

    @Autowired //1
    private ComunicacaoComSistemas comunicacaoComSistemas;

    @PersistenceContext
    private EntityManager entityManager;

                                                //2
    public void processaTentativaDePagamento(Compra compra,
                                             TentativaDePagamentoInterface tentativaDePagamentoInterface,  //3
                                             String urlBase){

        //4
        TentativaDePagamento tentativaDePagamento = tentativaDePagamentoInterface.toTentativaDePagamento();
        compra.adicionarTentativaDePagamento(tentativaDePagamento);

        //5
        if(!tentativaDePagamento.transacaoFoiUmSucesso()){
            System.out.println(compra.prepararFalhaDeCompraParaEmailDoComprador(urlBase));
            return;
        }

                                                        //6
        comunicacaoComSistemas.enviarNotaFiscal(new OutrosSistemasDTO(compra.getId(), compra.retornarIdComprador()));
        comunicacaoComSistemas.enviarRankingVendedores(new OutrosSistemasDTO(compra.getId(), compra.retornarIdVendedor()));
        System.out.println(compra.prepararDetalhesDeCompraParaEmailDoComprador());
        entityManager.merge(compra);
    }
}
