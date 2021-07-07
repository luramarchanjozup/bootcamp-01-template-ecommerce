import os 
import statistics
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as stats
import math
import stats_por_criterio
from matplotlib import cm
from matplotlib.ticker import LinearLocator


file = open("estatisticasgerais.txt", "r") 
lines = file.readlines()


results = []

for line in lines:
    results.append(line.strip())


formatedResult = []
camadaController = []
camadaModel = []
camadaTransferencia = []
camadaServicos = []

for result in results:
    toCharArray = []
    toCharArray[:0] = result
    formatedResult.append(toCharArray)


for k in range(0, len(formatedResult)):
    for p in range(0, 5):
        if 'c' in formatedResult[k]:
            if formatedResult[k][p] != 'c':
                camadaController.append(formatedResult[k][p])
        elif 's' in formatedResult[k]:
            if formatedResult[k][p] != 's':
                camadaServicos.append(formatedResult[k][p])
        elif 'm' in formatedResult[k]:
            if formatedResult[k][p] != 'm':
                camadaModel.append(formatedResult[k][p])
        elif 't' in formatedResult[k]:
            if formatedResult[k][p] != 't':
                camadaTransferencia.append(formatedResult[k][p])



camadaController = [int(x) for x in camadaController]
camadaModel = [int(x) for x in camadaModel]
camadaTransferencia = [int(x) for x in camadaTransferencia]
camadaServicos = [int(x) for x in camadaServicos]


somaController = sum(camadaController)
somaModel = sum(camadaModel)
somaServicos = sum(camadaServicos)
somaTransferencia = sum(camadaTransferencia)


pontosPorCriterio = [0,0,0,0]

pontosPorCriterioNaCamda = []


for t in range(0, len(formatedResult)):
    for p in range(0,4):
        pontosPorCriterio[p] = pontosPorCriterio[p] + int(formatedResult[t][p])


print(pontosPorCriterio)


pontosPorCamada = [somaController,somaModel,somaServicos,somaTransferencia]


#por camada

#1

plt.bar(['controllers', 'models' , 'serviços' , 'transferência'], [somaController,somaModel,somaServicos,somaTransferencia])
plt.title("Variação de pontos por camada")

plt.ylim(0, 50)

plt.grid(True)

plt.savefig("resultadosfinais1.png")



#2

radii = 10 * np.random.rand(20)
colors = plt.cm.viridis(radii / 10.)


plt.subplot(111, projection='polar')
plt.bar(['controllers', 'models' , 'serviços' , 'transferência'], [somaController,somaModel,somaServicos,somaTransferencia], color=colors, alpha=0.8)
plt.title("Variação de pontos por camada")

plt.ylim(0, 50)

plt.grid(True)

plt.savefig("resultadosfinais2.png")


