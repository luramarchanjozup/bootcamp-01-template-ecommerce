import conta_indicadores_complexidade 
import receber_argumentos
import listagem_classes
import conta_funcoes_como_parametro
import matplotlib.pyplot as plt
import numpy as np
import os


# lista as classes criadas no projeto analizado


lista_classes_projeto = listagem_classes.lista_classes_projeto


# identificadores fáceis de serem localizados (critério 1)


indicadores_de_complexidade = [ 
    "if", 
    "else", 
    "try", 
    "?", 
    ":", 
    "switch", 
    "case", 
    "forEach" 
]


# recebendo os argumentos para iniciar o programa 
# ( --classe = CompraController --pasta = finalizacompra )


classe_analisada = receber_argumentos.classe_analisada


# funções passadas como parâmetro (critério 2)


n_funcoes_como_parametro = conta_funcoes_como_parametro.funcoes_como_parametro(classe_analisada)


# limpando a string -> remove ';' '}' '{' ')' '(' '@' '-' '.' ',' '\n'


for char in ';}{)(@-.,\n': classe_analisada=classe_analisada.replace(char,' ')


# depois classe ter virado uma lista de palavras, aqui é gerado uma sub-lista
# utilizando apenas as palavras depois da anotação RestController


lista_palavras_classe_inicial = classe_analisada.split()

rest_controller_index = lista_palavras_classe_inicial.index('public')

lista_palavras_classe = []

for t in range(0, len(lista_palavras_classe_inicial)):
    if t >= rest_controller_index: lista_palavras_classe.append(lista_palavras_classe_inicial[t])


# contar acoplamentos

acoplamentos = 0

for x in range(0,len(lista_palavras_classe_inicial)):
    if lista_palavras_classe_inicial[x] == "Autowired":
        acoplamentos += 1


# pontos de classes criadas no projetos
# aqui é comparadas as listas de todas as classes do projeto com a lista de palavra das classes



pontos_classes_criadas_especificamente_no_projeto = set(lista_classes_projeto) & set(lista_palavras_classe)

pontos_extras = len(pontos_classes_criadas_especificamente_no_projeto) + n_funcoes_como_parametro


# conta os pontos mais fáceis de serem identificados, soma tudo e printa


pontos_complexidade_intrinseca = conta_indicadores_complexidade.contar_pontos_listados(lista_palavras_classe, indicadores_de_complexidade, pontos_extras)

print(f"\nPontos de complexidade na classe: {pontos_complexidade_intrinseca[1]}\n")
print(f"Os indicadores de complexidade encontrados foram: {set(indicadores_de_complexidade) & set(lista_palavras_classe)}\n")
print(f"As classes criadas especificamente nesse projeto, gerando complexidade, foram: {pontos_classes_criadas_especificamente_no_projeto}\n")
print(f"Número de acoplamentos: {acoplamentos}\n")


# gera gráfico de distribuição por critério de complexidade

lista_pontos = [n_funcoes_como_parametro, len(pontos_classes_criadas_especificamente_no_projeto) - acoplamentos, pontos_complexidade_intrinseca[0], acoplamentos]


stringPontos = ''.join(str(x) for x in lista_pontos)


file = open("estatisticasgerais.txt", "a") 
file.write(stringPontos) 
file.close()


if(not os.path.isdir(f"./{receber_argumentos.nome_da_classe.pasta}")):
    os.mkdir(f"./{receber_argumentos.nome_da_classe.pasta}")



# gráfico 1 barra
#retirado da documentação do MatPlotLib


plt.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [lista_pontos[0], lista_pontos[1], lista_pontos[2], lista_pontos[3]])
plt.title("Variação de pontos por critério")

plt.ylim(0, 6)

plt.grid(True)

plt.savefig(f"./{receber_argumentos.nome_da_classe.pasta}/barra{receber_argumentos.nome_da_classe.classe}.png")



# gráfico 3 pizza

labels = 'f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'
sizes = [lista_pontos[0], lista_pontos[1], lista_pontos[2], lista_pontos[3]]
explode = (0.12, 0.12, 0.12, 0.12)  

fig1, ax1 = plt.subplots()
ax1.pie(sizes, explode=explode, labels=labels, autopct='%1.1f%%',shadow=True, startangle=90)
ax1.axis('equal')  

plt.savefig(f"./{receber_argumentos.nome_da_classe.pasta}/grafico_pizza{receber_argumentos.nome_da_classe.classe}.png")


# gráfico 3 polar
#retirado da documentação do MatPlotLib

radii = 10 * np.random.rand(20)
colors = plt.cm.viridis(radii / 10.)


ax = plt.subplot(111, projection='polar')
ax.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [lista_pontos[0], lista_pontos[1], lista_pontos[2], lista_pontos[3]], color=colors, alpha=0.8)
plt.title("Variação de pontos por critério")

plt.ylim(0, 5)

plt.grid(True)

plt.savefig(f"./{receber_argumentos.nome_da_classe.pasta}/polar{receber_argumentos.nome_da_classe.classe}.png")
