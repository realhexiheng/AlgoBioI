#!/usr/bin/python3
import time
import random
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.pyplot import MultipleLocator


def ss_generator(len):
    ss = ''.join(str(random.choice(range(10))) for _ in range(len))
    return ss


#start = time.time()
#ss_generator(10000000)
#end = time.time()
#print(end - start)

def mss_plot(naive, dp, dc, linear):
    x = np.array([10**3, 10**4, 10**5, 10**6, 10**7, 10**8, 10**9, 10**10])
    naive = np.array(naive).astype(np.double)
    dp = np.array(dp).astype(np.double)
    dc = np.array(dc).astype(np.double)
    linear = np.array(linear).astype(np.double)
    naive_mask = np.isfinite(naive)
    dp_mask = np.isfinite(dp)
    dc_mask = np.isfinite(dc)
    linear_mask = np.isfinite(linear)
    fig, ax = plt.subplots(1, 1)
    ax.plot(x[naive_mask], naive[naive_mask], color='red', linewidth=2, linestyle='-', marker='.')
    ax.plot(x[dp_mask], dp[dp_mask], color='green', linewidth=2, linestyle='-', marker='.')
    ax.plot(x[dc_mask], dc[dc_mask], color='yellow', linewidth=2, linestyle='-', marker='.')
    ax.plot(x[linear_mask], linear[linear_mask], color='blue', linewidth=2, linestyle='-', marker='.')
    y_index=['0.01','0.1','1','10','100','1000','100000','310000']
    _ = plt.yticks(naive, y_index)
    plt.title("MSS Runtime")
    plt.xlabel("import size")
    plt.ylabel("Runtime")
    #ax.set_yscale("log")
    #ax.set_ylim(1e0, 1e1, 1e2, 1e3)
    #plt.yticks(np.arange(8),('0.0','0.1','1','10','100','1000','100000','310000'))
    plt.xticks(x, ['10e3', '10e4', '10e5', '10e6', '10e7', '10e8', '10e9', '10e10'])
    ax.set_xscale("log")
    ax.set_yscale('log')
    plt.legend(['Algorithm.Naive', 'Dynamic', 'Divide&Conquer', 'Linear'], loc='upper right')
    plt.savefig("./MSS_plot.png")
    plt.show()

arr_naive = [0.30, 310.0, 309600.0, None, None, None, None, None]
arr_dp = [0.0, 0.08, 7.73, 773, 75600, None, None, None]
arr_dc = [0.0, 0.0, 0.0, 0.05, 0.52, 5.68, 63.6, None]
arr_linear = [0.0, 0.0, 0.0, 0.0, 0.02, 0.16, 1.55, 15.5]

mss_plot(arr_naive, arr_dp, arr_dc, arr_linear)