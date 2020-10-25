import conta_indicadores_complexidade 
import receber_argumentos
import listagem_classes
import conta_funcoes_como_parametro
import gera_graficos
import conta_acoplamentos
import matplotlib.pyplot as plt
import numpy as np
import os

lista_classes_projeto = listagem_classes.lista_classes_projeto
nome_da_classe = receber_argumentos.nome_da_classe.classe


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



classe_analisada = receber_argumentos.classe_analisada


n_funcoes_como_parametro = conta_funcoes_como_parametro.funcoes_como_parametro(classe_analisada)



for char in ';}{)(@-.,\n': classe_analisada=classe_analisada.replace(char,' ')



lista_classe_analisada_reduzida = conta_acoplamentos.filtra_classe(classe_analisada)

pontos_acoplamento = conta_acoplamentos.conta_acoplamentos(lista_classe_analisada_reduzida)



pontos_classes_criadas_especificamente_no_projeto = set(lista_classes_projeto) & set(lista_classe_analisada_reduzida)
pontos_classes_criadas_especificamente_no_projeto.remove(nome_da_classe)


pontos_extras = len(pontos_classes_criadas_especificamente_no_projeto) + n_funcoes_como_parametro

pontos_complexidade_intrinseca = conta_indicadores_complexidade.contar_pontos_listados(lista_classe_analisada_reduzida, indicadores_de_complexidade, pontos_extras)


resultado = f"""\nPontos de complexidade na classe: {pontos_complexidade_intrinseca[1]}\n
Os indicadores de complexidade encontrados foram: {set(indicadores_de_complexidade) & set(lista_classe_analisada_reduzida)}\n
As classes criadas especificamente nesse projeto, gerando complexidade, foram: {pontos_classes_criadas_especificamente_no_projeto}\n
Número de acoplamentos: {pontos_acoplamento}\n"""


if(not os.path.isdir("./analise_por_classe")):
    os.mkdir("./analise_por_classe")


file = open(f"./analise_por_classe/estatisticas{receber_argumentos.nome_da_classe.classe}.txt", "a") 
file.write(resultado) 
file.close()


# gera gráfico de distribuição por critério de complexidade

lista_pontos = [n_funcoes_como_parametro, len(pontos_classes_criadas_especificamente_no_projeto) - pontos_acoplamento, pontos_complexidade_intrinseca[0], pontos_acoplamento]

lista_string = [str(x) for x in lista_pontos]
lista_string.append(receber_argumentos.nome_da_classe.camada)


string_adicionar = ''.join(lista_string)

file = open("estatisticasgerais.txt", "a") 
file.write(f"{string_adicionar}\n") 
file.close()


if(not os.path.isdir(f"./{receber_argumentos.nome_da_classe.pasta}")):
    os.mkdir(f"./{receber_argumentos.nome_da_classe.pasta}")



gera_graficos.gerar_graficos(lista_pontos, receber_argumentos)

