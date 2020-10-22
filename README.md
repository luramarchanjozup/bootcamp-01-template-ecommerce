# Desafio Ecommerce!

## Pontos de complexidade

- Utilizei esse projeto para automatizar a contagem dos pontos de complexidade: [contar pontos de complexidade](https://github.com/MarceloAmorim25/cdd-complexity-pointer)


- Tomemos como exemplos a classe CompraController para ver as funcionalidades do projeto:

```

    @RestController
@RequestMapping("produtos/{produtoId}/compras")
public class CompraController {

    @Autowired
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

- Um gráfico de variação de complexidade por critério também é gerada ao fim do programa(utilizei o critério de branches, classes específicas ao projeto gerando complexidade, acoplamentos e funções como parâmetro). Outro gráfico mostra a distribuição percentual dos pontos.

### gráfico 1

![](/readme-images/grafico_pizza.png)


### gráfico 2


![](/readme-images/variacao_pontos.png)


- Obviamente o contador ainda precisa de diversas melhorias, ainda não tem uma precisão alta garantida. Mas já consegue identificar boa parte dos pontos em algumas classes.


## Documentação da API - Swagger

## Sobre os testes

