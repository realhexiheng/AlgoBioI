import numpy as np
import tsplib95
from python_tsp.exact import solve_tsp_dynamic_programming

problem = tsplib95.load('C:/Workspace/AlgoBioI/TSP/ALL_tsp/gr17.tsp')
# print(problem.edge_weights)
print(problem.render())

adj = []
dim = len(problem.edge_weights)
for dis in problem.edge_weights:
    adj.append(dis)
     
# print(adj)
# distance_matrix = np.array(adj)
# permutation, distance = solve_tsp_dynamic_programming(distance_matrix)