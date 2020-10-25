def filtra_classe(classe_analisada):
    #pequeno filtro na lista das palavras da classe
    lista_palavras_classe_inicial = classe_analisada.split()

    rest_controller_index = lista_palavras_classe_inicial.index('public')

    lista_palavras_classe = []

    for t in range(0, len(lista_palavras_classe_inicial)):
        if t >= rest_controller_index: lista_palavras_classe.append(lista_palavras_classe_inicial[t])

    return lista_palavras_classe


def conta_acoplamentos(lista_palavras_classe):

    acoplamentos = 0

    for x in range(0,len(lista_palavras_classe)):
        if lista_palavras_classe[x] == "Autowired":
            acoplamentos += 1

    return acoplamentos