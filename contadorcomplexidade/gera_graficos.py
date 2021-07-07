import matplotlib.pyplot as plt
import numpy as np
import os


def gerar_graficos(lista_pontos, receber_argumentos):
    # gráfico 1 barra
    #retirado da documentação do MatPlotLib


    plt.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [lista_pontos[0], lista_pontos[1], lista_pontos[2], lista_pontos[3]])
    plt.title("Variação de pontos por critério")

    plt.ylim(0, 6)

    plt.grid(True)

    plt.savefig(f"./{receber_argumentos.nome_da_classe.pasta}/barra{receber_argumentos.nome_da_classe.classe}.png")



    # gráfico 2 polar
    #retirado da documentação do MatPlotLib

    radii = 10 * np.random.rand(20)
    colors = plt.cm.viridis(radii / 10.)


    ax = plt.subplot(111, projection='polar')
    ax.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [lista_pontos[0], lista_pontos[1], lista_pontos[2], lista_pontos[3]], color=colors, alpha=0.8)
    plt.title("Variação de pontos por critério")

    plt.ylim(0, 5)

    plt.grid(True)

    plt.savefig(f"./{receber_argumentos.nome_da_classe.pasta}/polar{receber_argumentos.nome_da_classe.classe}.png")