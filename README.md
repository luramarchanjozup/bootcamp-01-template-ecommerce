# Desafio Ecommerce


## Sobre os testes

- Os testes cobriram porcentagem significativa do software. Utilizei o Pitest para direcionar o desenvolvimento de testes. A única observação é que encontrei problemas para gerar o JWT com o rest assured. Então coloquei o token nas classes de teste por meio de uma anotação ligando ao application properties. Para rodar os testes, tem que gerar o token no Insomnia/Postman e definir o ecommerce.jwt.testes = colocar_token_aqui.

![](/readme-images/coberturatestes.jpg)



## Coesão, acoplamento e complexidade (C3 - Cohesion, Coupling and Complexity)

- Utilizei o CodeMR (plugin Intellij) para fazer essas análises.

- Analisando por uma visão global das métricas de coesão, acoplamento e complexidade é possível ver que no geral, o programa ficou com bons resultados, o que é um ponto positivo para o CDD, tendo em vista que o desenvolvimento foi totalmente direcionado ao CDD. É óbvio que teríamos que comparar a mesma situações com desenvolvimentos orientados a outros designs de código, mas, não deixa de ser um ponto positivo. 


![](/readme-images/analise_geral_C3.jpg)



### Pontos gerais do projeto - CDD


- a variação de pontos por camada pode gerar diversas indicações de encaminhamento do que posso buscar melhorar. Teve um rastreamento de complexidade central nas classes de transferência de dados, o que pode indicar que talvez pudesse ter trazido mais métodos e lógica para as Models ou distribuído melhor entre todas as camadas. Não sei exatamente ainda qual seria a melhor interpretação, mas já me geram bons indícios de como melhorar para o próximo desafio. 


![](/readme-images/resultadosfinais2.png)


- Nesse gráfico geral, fica bem visível como a complexidade ficou concentrada em classes criadas no projeto. Nesse caso acho que é uma ótima indicação de preciso buscar aplicar cada vez recursos padrão do Spring, evitando ao máximo criar novas classes para funções X, Y ou Z. Fica aí mais um apontamento do que preciso buscar melhorar para o próximo desafio, sendo necessário buscar alguma estratégia para manejar essa hiperconcentração de complexidade em classes novas criadas no projeto. Obviamente a ideia não é comparar os pontos por critérios com os pontos por camadas, mas comparar a distribuição.


![](/readme-images/analisa_geral1.png)



- É evidente que o contador ainda precisa de diversas melhorias e ainda não oferece a maior precisão do mundo. Mas já possui alguma relevância em termos de precisão. Comparei a contagem automatizada e a manual na camada de Controllers e o resultado foi bem interessante. Então, só reforço que não há, por enquanto, relevância estatística que assegure a eficiência da contagem de pontos do programa, mas já começam a aparecer algumas análises bem sucedidas, o que pode indicar pelo menos que esse passo inicial está tendo um direcionamento interessante.


![](/readme-images/comparacao.jpg)


# Detalhamento dos tópicos iniciados


## Detalhamento C3



- No detalhamento fica transparente que a maior questão foi coesão. Ponto para evoluir para o próximo projeto ou tentar refatorar esse com o objetivo de melhorar essas métricas de coesão.


![](/readme-images/aprofunda_C3.jpg)


- As classes que não são de configuração que mais aumentaram a contagem de falta de coesão foram: Compra, Produto, DetalheProdutoResponse. Trazendo uma correlação, essas mesmas classes com 'Lack of Cohesion', são classes que possuem elevada contagem de pontos de complexidade ao entendimento, inclusive, a classe Produto excedeu a contagem. É certo que as correlações de variáveis existem aos montes e precisamos ter sobriedade antes de concluir qualquer coisa. Apesar disso, fica aqui uma correlação interessante que vai de encontro com o CDD.


![](/readme-images/classes_falta_coesao.jpg)



- Contagem dos pontos -> Compra, Produto, DetalheProdutoResponse

- Compra:

```

obs.: contagem manual -> pc = 6

Pontos de complexidade na classe: 6

Os indicadores de complexidade encontrados foram: set()

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Transacao', 'Produto', 'GatewayPagamento', 'Usuario', 'RetornoGatewayPagamento'}

Número de acoplamentos: 0


```


- Produto:


```

obs.: contagem manual -> pc = 12

Pontos de complexidade na classe: 10

Os indicadores de complexidade encontrados foram: set()

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Usuario', 'Categoria', 'ImagemProduto'}

Número de acoplamentos: 0


```

- DetalheProdutoResponse:


```
obs.: contagem manual -> pc = 6

Pontos de complexidade na classe: 5

Os indicadores de complexidade encontrados foram: set()

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Produto'}

Número de acoplamentos: 0



```



## Sobre a segurança

- Utilizei a ferramenta OpenSource Horusec (https://github.com/ZupIT/horusec) para tentar identificar falhas na API.

- Esse foi o resultado geral: 

![](/readme-images/segurancageral.jpg)


- Essa foi uma das falhas mais importantes identificada, cabe ver alguma alternativa, tendo em vista que a hash não parece possuir grau alto de segurança.

![](/readme-images/possivelfalhaseguranca1.jpg)



## Detalhamento CDD no projeto


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

- O programa contou 7 pontos. Com a seguinte saída:

> python3 start.py --classe=CompraController --pasta=finalizacompra --camada=c



```

Pontos de complexidade na classe: 7

Os indicadores de complexidade encontrados foram: {'if'}

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Produto', 'UsuarioRepository', 'CompraRequest', 'Compra', 'BuscaEmailDoUsuarioPeloToken', 'Usuario'}

Número de acoplamentos: 3
    

```


- Observações importantes:
- (a) Para utilizar o programa, o único ajuste a se fazer é mudar o path do projeto.
- (b) Está no arquivo receber_argumentos.py.
- (c) Feito isso, é só executar o comando acima e o programa irá gerar essa análise abaixo e dois gráficos para a cada classe.
- (d) Para gerar os gráficos gerais, deve executar o seguinte comando:

> python3 gera_stats_finais.py


### Pontos CompraController - gráfico 1

![](/readme-images/polarCompraController.png)



### Pontos CompraController - gráfico 2


![](/readme-images/barraCompraController.png)




## Documentação da API - Swagger