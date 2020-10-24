import os 
import statistics
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as stats
import math



somas = []


file = open("estatisticasgerais.txt", "r") 
resultados = file.read()

inteiros = [int(x) for x in resultados]
  

for k in range(0,int(len(resultados)/4)):
    somas.append(sum(inteiros[k:4+k]))



total_complexidade = sum(somas)
media_complexidade = statistics.mean(inteiros)
variancia = statistics.variance(inteiros)
desvio_padrao = statistics.stdev(inteiros)



mu = media_complexidade

sigma = math.sqrt(variancia)

inteiros = np.linspace(mu - 3*sigma, mu + 3*sigma, 50)

plt.plot(inteiros, stats.norm.pdf(inteiros, mu, sigma))

plt.show()


