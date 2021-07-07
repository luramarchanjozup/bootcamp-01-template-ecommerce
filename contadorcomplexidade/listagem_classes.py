import os

pastas_classes = []
nomes_classes_com_extensao = []
lista_classes_projeto = []

with os.scandir('/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/ecommerce/src/main/java/br/com/ecommerce') as diretorios:
    for diretorio in diretorios:
        pastas_classes.append(diretorio)



for k in range(0,len(pastas_classes)):
    if pastas_classes[k].name.count('.java') == 0: 
        pasta = pastas_classes[k]
        with os.scandir(f'/home/marceloamorim/Documentos/bootcamp-01-template-ecommerce/ecommerce/src/main/java/br/com/ecommerce/{pasta.name}') as classes:
            for classe in classes:
                nomes_classes_com_extensao.append(classe.name)



for p in range(0,len(nomes_classes_com_extensao)):
    index_extensao = nomes_classes_com_extensao[p].find('.')
    lista_classes_projeto.append(nomes_classes_com_extensao[p][:index_extensao])



