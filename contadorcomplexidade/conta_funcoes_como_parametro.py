# identificando funções passadas como parâmetro

# a identificação é feita pelo padrão seguido pelos parênteses e pontos 
# um '.()' significa uma função. Um '.(.())' significa uma função passado como parâmetro.

import re

def funcoes_como_parametro(classe_analisada):
        regex1 = r'[)(.]' 

        padrao_parenteses = re.findall(regex1, classe_analisada)

        padrao_parenteses_string = ''.join(padrao_parenteses)

        n_funcoes_como_parametro = padrao_parenteses_string.count('.(.())')

        return n_funcoes_como_parametro