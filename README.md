# Desafio Ecommerce!

## Pontos de complexidade

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

- O programa também contou 8 pontos. Com a seguinte saída:

> python3 contador_complexidade.py --classe=CompraController --pasta=finalizacompra

```

Pontos de complexidade na classe: 8

Os indicadores de complexidade encontrados foram: {'if'}

As classes criadas especificamente nesse projeto, gerando complexidade, foram: {'Usuario', 'Compra', 'Produto', 'BuscaEmailDoUsuarioPeloToken', 'CompraRequest', 'UsuarioRepository', 'CompraController'}

Número de acoplamentos: 3
    

```

- Um gráfico de variação de complexidade por critério também é gerada ao fim do programa(utilizei o critério de branches, classes específicas ao projeto gerando complexidade, acoplamentos e funções como parâmetro). Outro gráfico mostra a distribuição percentual dos pontos. Aqui abro uma observação sobre a geração de gráficos. 

### gráfico 1

![](/readme-images/variacao_pontos.jpg)


### gráfico 2


![](/readme-images/grafico_pizza.jpg)


- Obviamente o contador ainda precisa de diversas melhorias, ainda não tem uma precisão alta garantida. Mas já consegue identificar boa parte dos pontos em algumas classes.



## Sobre os testes

- Utilizei o Pitest para ver a cobertura dos meus testes. Tendo em vista que iniciei a implementação há pouco tempo, a cobertura ainda está muito baixa. Mas o direcionamento do Pitest está colaborando bastante para saber melhor o que preciso testar. A única observação é que encontrei problemas para gerar o JWT com o rest assured. Então coloquei o token nas classes de teste por meio de uma anotação ligando ao application properties. Para rodar os testes, tem que gerar o token no Insomnia/Postman e definir o ecommerce.jwt.testes = colocar_token_aqui.

![](/readme-images/coberturatestes.jpg)



## Sobre a segurança

- Utilizei a ferramenta OpenSource Horusec (https://github.com/ZupIT/horusec) para tentar identificar falhas na API.

- Esse foi o resultado geral: 

![](/readme-images/segurancageral.jpg)


- Essa foi uma das falhas mais importantes identificada, cabe ver alguma alternativa, tendo em vista que a hash não parece possuir grau alto de segurança.

![](/readme-images/possivelfalhaseguranca1.jpg)


## Documentação da API - Swagger