# Desafio Ecommerce


## Sobre os testes

- Os testes cobriram porcentagem significativa do software. Utilizei o Pitest para direcionar o desenvolvimento de testes. A única observação é que encontrei problemas para gerar o JWT com o rest assured. Então coloquei o token nas classes de teste por meio de uma anotação ligando ao application properties. Para rodar os testes, tem que gerar o token no Insomnia/Postman e definir o ecommerce.jwt.testes = colocar_token_aqui.

![](/readme-images/coberturatestes.jpg)


### Pontos gerais do projeto - CDD

- a variação de pontos por camada pode gerar diversas indicações de encaminhamento do que posso buscar melhorar. Teve um rastreamento de complexidade central nas classes de transferência de dados, o que pode indicar que talvez pudesse ter trazido mais métodos e lógica para as Models ou distribuído melhor entre todas as camadas. Não sei exatamente ainda qual seria a melhor interpretação, mas já me geram bons indícios de como melhorar para o próximo desafio. 


![](/readme-images/resultadosfinais2.png)


- Nesse gráfico geral, fica bem visível como a complexidade ficou concentrada em classes criadas no projeto. Nesse caso acho que é uma ótima indicação de preciso buscar aplicar cada vez recursos padrão do Spring, evitando ao máximo criar novas classes para funções X, Y ou Z. Fica aí mais um apontamento do que preciso buscar melhorar para o próximo desafio, sendo necessário buscar alguma estratégia para manejar essa hiperconcentração de complexidade em classes novas criadas no projeto. Obviamente a ideia não é comparar os pontos por critérios com os pontos por camadas, mas comparar a distribuição.


![](/readme-images/analisa_geral1.png)



- Obviamente o contador ainda precisa de diversas melhorias e ainda não oferece a maior precisão do mundo. Mas já possui alguma relevância em termos de precisão. Comparei a contagem automatizada e a manual na camada de Controllers e o resultado foi bem interessante.


![](/readme-images/comparacao.jpg)




## Sobre a segurança

- Utilizei a ferramenta OpenSource Horusec (https://github.com/ZupIT/horusec) para tentar identificar falhas na API.

- Esse foi o resultado geral: 

![](/readme-images/segurancageral.jpg)


- Essa foi uma das falhas mais importantes identificada, cabe ver alguma alternativa, tendo em vista que a hash não parece possuir grau alto de segurança.

![](/readme-images/possivelfalhaseguranca1.jpg)



## Exemplo de análise de uma classe com o programa

- Utilizei esse projeto para automatizar a contagem dos pontos de complexidade: [contar pontos de complexidade](https://github.com/MarceloAmorim25/cdd-complexity-counter)


- Tomemos como exemplos a classe CompraController para ver as funcionalidades do projeto:

```

@RestController
@RequestMapping("produtos/{produtoId}/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager entityManager;

    //1
    @Autowired
    private BuscaEmailDoUsuarioPeloToken buscaEmailDoUsuarioPeloToken;

    //1
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional                                                              //1
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, HttpServletRequest request,
                                     @PathVariable Long produtoId, UriComponentsBuilder uriComponentsBuilder){


        Long quantidadeSolicitada = compraRequest.getQuantidade();

                        //1                
        Produto produtoASerComprado = entityManager.find(Produto.class, produtoId);

        //1                                    
        if(produtoASerComprado.verificaDisponibilidadeEAtualiza(quantidadeSolicitada)){

                                            //1
            String emailComprador = buscaEmailDoUsuarioPeloToken.buscaEmailDoUsuario(request);

                    //1
            Usuario comprador = usuarioRepository.findByLogin(emailComprador);

                    //1
            Compra compra = compraRequest.toModel(entityManager, comprador);

            entityManager.persist(compra);
               
            String urlRedirecionamento = compra.urlRedirecionamento(uriComponentsBuilder);

            return ResponseEntity
                    .ok(urlRedirecionamento);


        }

        return ResponseEntity.badRequest().build();

    }
}

```

- Minha contagem manual deu um total de 8 pontos.

- O programa também contou 7 pontos. Com a seguinte saída:

> python3 start.py --classe=CompraController --pasta=finalizacompra --camada=c

```

Pontos de complexidade na classe: 7

Os indicadores de complexidade encontrados foram: {'if'}

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Produto', 'UsuarioRepository', 'CompraRequest', 'Compra', 'BuscaEmailDoUsuarioPeloToken', 'Usuario'}

Número de acoplamentos: 3
    

```

- Tentei analisar os dados gerados pela métrica do CDD para conseguir balizar algumas escolhas de desenvolvimento. O programa está gerando alguns gráficos. Ainda são muito básicos e com rigor matemático ainda insuficiente. Mas servem como ponto de partida para analisar meu projeto.


### Pontos CompraController - gráfico 1

![](/readme-images/polarCompraController.png)



### Pontos CompraController - gráfico 2


![](/readme-images/barraCompraController.png)




## Documentação da API - Swagger