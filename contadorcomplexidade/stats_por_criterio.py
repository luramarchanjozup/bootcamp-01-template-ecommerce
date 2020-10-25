import gera_stats_finais



def gerar_stats_criterio(plt,pontosPorCriterio):
    #por critério
    plt.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [pontosPorCriterio[0],pontosPorCriterio[1],pontosPorCriterio[2],pontosPorCriterio[3]])
    plt.title("Variação de pontos por critério")

    plt.ylim(0, 50)

    plt.grid(True)

    plt.savefig("resultadosfinais3.png")



    #2


    plt.subplot(111, projection='polar')
    plt.bar(['f(f(x))', 'classes específicas' , 'indicadores' , 'acoplamento'], [pontosPorCriterio[0],pontosPorCriterio[1],pontosPorCriterio[2],pontosPorCriterio[3]], color=colors, alpha=0.8)
    plt.title("Variação de pontos por critério")

    plt.ylim(0, 70)

    plt.grid(True)

    plt.savefig("resultadosfinais4.png")

