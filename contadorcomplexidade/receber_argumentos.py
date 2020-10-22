import argparse

def recebe_argumentos():

    parser = argparse.ArgumentParser(description='Definir classe a ser analisada')

    parser.add_argument('--controller', action="store", dest='controller')

    parser.add_argument('--pasta', action="store", dest='pasta')

    nome_da_classe = parser.parse_args()

    classe_java = open(f"/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/ecommerce/src/main/java/br/com/ecommerce/{nome_da_classe.pasta}/{nome_da_classe.controller}.java")

    classe_analisada = classe_java.read()

    return classe_analisada