def contar_pontos_listados(lista_palavras_classe, indicadores_de_complexidade, pontos_extras):
        
        pontos_complexidade_intrinseca = 0

        pontos_complexidade_intrinseca = pontos_complexidade_intrinseca + pontos_extras

        for k in range(0, len(lista_palavras_classe)):
            for p in range(0, len(indicadores_de_complexidade)):
                if lista_palavras_classe[k] == indicadores_de_complexidade[p]: 
                    pontos_complexidade_intrinseca += 1


        print(f"\nPontos de complexidade na classe: {pontos_complexidade_intrinseca}\n")
        print(f"Os indicadores de complexidade encontrados foram: {set(indicadores_de_complexidade) & set(lista_palavras_classe)}\n")