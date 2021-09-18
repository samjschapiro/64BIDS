import numpy as np # linear algebra
import matplotlib.pyplot as plt
from math import sqrt
import random

get_com = lambda lst: (sum([x for x, y in lst])/float(len(lst)), sum([y for x, y in lst])/float(len(lst)))
distance = lambda point_a, point_b: sqrt((point_a[0] - point_b[0])**2+(point_a[1] - point_b[1])**2)

def getMaxVector(points, com):
    max = (0, 0)
    for point in points:
        if (distance(point, com) > distance(max, (0,0))):
            max = (point[0] - com[0], point[1] - com[1])
    return max

def findGroups(inp, out):
    # PLOT POINTS
    plt.scatter(*zip(*inp), color="red")
    plt.scatter(*zip(*out), color="blue")

    # Find center of mass of input and output
    inp_com = get_com(inp)
    print(inp_com)
    out_com = get_com(out)
    print(out_com)

    # PLOT COM
    plt.plot(*inp_com, color="red", marker="+")
    plt.plot(*out_com, color="blue", marker="+")

    # Choose N (5) longest vectors and sum them for input and output to get momentum vector
    max_inp_vector = getMaxVector(inp, inp_com)
    max_out_vector = getMaxVector(out, out_com)

    # PLOT MOMENTUM VECTORS
    plt.arrow(inp_com[0], inp_com[1], max_inp_vector[0], max_inp_vector[1], head_width=4, head_length=4, color="red")
    plt.arrow(out_com[0], out_com[1], max_out_vector[0], max_out_vector[1], head_width=4, head_length=4, color="blue")
    print(max_inp_vector)
    print(max_out_vector)
    plt.show()

    # Perform SVD on the two vectors to get transformation matrix
    # Transform all point using the transformation vector
    # PLOT TRANSFORMED POINTS  

n = 20
random_int = lambda: random.randint(0, 100)

inp_points = [(random_int(), random_int()) for _ in range(n)]

scalar = random.randint(1, 2)
t_x, t_y = random.randint(0, 10), random.randint(0, 10)
out_points = [(x*scalar + t_x, y*scalar + t_y) for x, y in inp_points]

# findGroups(inp_points, out_points)

# firstPointSet = [(1,1), (1,3), (2,1)]
# secondPointSet = [(1,1), (5,1), (5,3)]

firstPointSet = [(16,55), (4,15), (3,22), (4,11), (7,11), (2,28), (71,14), (7,15)]
secondPointSet = [(45,50), (13,37), (50,50), (75,50), (59,38), (17,28), (3,22), (66,62)]

findGroups(firstPointSet, secondPointSet)


