# https://stackoverflow.com/questions/3847327/how-to-calculate-n-log-n-c
import math

def nlogn(c):
    lower = 0.0
    upper = 10e10
    while True:
        middle = (lower+upper)/2
        if lower == middle or middle == upper:
            return middle
        if middle*math.log(middle, 2) > c:
            upper = middle
        else:
            lower = middle

input = 20 * 100
print(nlogn(input))