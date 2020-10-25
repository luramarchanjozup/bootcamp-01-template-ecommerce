package br.com.ecommerce.finalizacompraTestes;

import br.com.ecommerce.cadastrocategoria.Categoria;
import br.com.ecommerce.cadastroproduto.Caracteristica;
import br.com.ecommerce.cadastroproduto.CaracteristicaRequest;
import br.com.ecommerce.cadastroproduto.Produto;
import br.com.ecommerce.cadastrousuario.SenhaLimpa;
import br.com.ecommerce.cadastrousuario.Usuario;
import br.com.ecommerce.finalizacompra.Compra;
import br.com.ecommerce.finalizacompra.GatewayPagamento;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class CompraModelTestes {


    private Produto produto;

    private Categoria categoria;

    private Usuario usuario;

    private List<CaracteristicaRequest> caracteristicas;

    private Compra compra;



    @Before
    public void SetUp(){


        categoria = new Categoria("Testando");


        usuario = new Usuario("usuariotesteproduto@email.com", new SenhaLimpa("testando"));


        caracteristicas = Arrays.asList(
                new CaracteristicaRequest("teste 1", "120"),
                new CaracteristicaRequest("teste 2", "120"),
                new CaracteristicaRequest("teste 3", "120")
        );


        produto = new Produto("Produto Teste", new BigDecimal(90), Long.parseLong("120"),
                caracteristicas, " ", categoria, usuario);

    }


    @Test
    public void UsuarioDaCompraNaoDeveSerNulo(){

        compra = new Compra(Long.parseLong("12"), GatewayPagamento.paypal, produto, usuario);

        Assert.assertTrue(compra.getUsuario() != null);

    }


    @Test
    public void ProdutoDaCompraNaoDeveSerNulo(){

        compra = new Compra(Long.parseLong("12"), GatewayPagamento.paypal, produto, usuario);

        Assert.assertTrue(compra.getProduto() != null);

    }

}
