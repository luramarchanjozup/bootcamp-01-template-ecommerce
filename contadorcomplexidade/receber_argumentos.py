import argparse


parser = argparse.ArgumentParser(description='Definir classe a ser analisada')


parser.add_argument('--classe', action="store", dest='classe')

parser.add_argument('--pasta', action="store", dest='pasta')

parser.add_argument('--camada', action="store", dest='camada')


nome_da_classe = parser.parse_args()

classe_java = open(f"/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/ecommerce/src/main/java/br/com/ecommerce/{nome_da_classe.pasta}/{nome_da_classe.classe}.java")

classe_analisada = classe_java.read()





