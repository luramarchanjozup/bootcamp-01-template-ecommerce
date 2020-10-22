def contar_pontos_listados(lista_palavras_classe, indicadores_de_complexidade, pontos_extras):
        
        lista_retornada = []
        pontos_complexidade_intrinseca = 0        

        for k in range(0, len(lista_palavras_classe)):
            for p in range(0, len(indicadores_de_complexidade)):
                if lista_palavras_classe[k] == indicadores_de_complexidade[p]: 
                    pontos_complexidade_intrinseca += 1

        pontos_indicadores = pontos_complexidade_intrinseca
        pontos_complexidade_intrinseca = pontos_complexidade_intrinseca + pontos_extras

        lista_retornada.append(pontos_indicadores)
        lista_retornada.append(pontos_complexidade_intrinseca)
        
        return lista_retornada