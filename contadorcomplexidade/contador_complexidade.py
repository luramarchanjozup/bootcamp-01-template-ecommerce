import conta_indicadores_complexidade 
import receber_argumentos
import listagem_classes
import conta_funcoes_como_parametro


# lista as classes criadas no projeto analizado


lista_classes_projeto = listagem_classes.lista_classes_projeto


# identificadores fáceis de serem localizados (contador 1)


indicadores_de_complexidade = [ 
    "if", "else", "try", "?", ":", "switch", "case", "forEach" 
]


# recebendo os argumentos para iniciar o programa 
# ( --classe = CompraController --pasta = finalizacompra )



classe_analisada = receber_argumentos.recebe_argumentos()



# funções passadas como parâmetro (contador 2)


n_funcoes_como_parametro = conta_funcoes_como_parametro.funcoes_como_parametro(classe_analisada)


# limpando a string -> remove ';' '}' '{' ')' '(' '@' '-' '.' ',' '\n'


for char in ';}{)(@-.,\n': classe_analisada=classe_analisada.replace(char,' ')


# depois classe ter virado uma lista de palavras, aqui é gerado uma sub-lista
# utilizando apenas as palavras depois da anotação RestController


lista_palavras_classe_inicial = classe_analisada.split()

rest_controller_index = lista_palavras_classe_inicial.index('RestController')

lista_palavras_classe = []

for t in range(0, len(lista_palavras_classe_inicial)):
    if t >= rest_controller_index: lista_palavras_classe.append(lista_palavras_classe_inicial[t])


# pontos de classes criadas no projetos
# aqui é comparadas as listas de todas as classes do projeto com a lista de palavra das classes


pontos_classes_criadas_especificamente_no_projeto = set(lista_classes_projeto) & set(lista_palavras_classe)

pontos_extras = len(pontos_classes_criadas_especificamente_no_projeto) + n_funcoes_como_parametro


# conta os pontos mais fáceis de serem identificados, soma tudo e printa


conta_indicadores_complexidade.contar_pontos_listados(lista_palavras_classe, indicadores_de_complexidade, pontos_extras)

print(f"As classes criadas especificamente nesse projeto, gerando complexidade, foram: {pontos_classes_criadas_especificamente_no_projeto}\n")