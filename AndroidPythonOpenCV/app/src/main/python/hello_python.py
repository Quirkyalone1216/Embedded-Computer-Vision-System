import numpy as np
def Python_say_Hello():
    print("Hello Python")


#使用numpy计算两个矩阵的乘积
def matrix_multiply():
    a = np.array([[1, 2], [3, 4]])
    b = np.array([[5, 6], [7, 8]])
    c = np.matmul(a, b)
    return c
