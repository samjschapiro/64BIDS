import numpy as np # linear algebra
import matplotlib.pyplot as plt
import seaborn as sns
from math import sqrt

get_com = lambda lst: (sum([x for x, y in lst])/len(lst), sum([y for x, y in lst])/len(lst))

def findGroups(inp: list, out: list):
    # PLOT POINTS

    # Find center of mass of input and output
    inp_com = get_com(inp)
    out_com = get_com(out)
    # PLOT COM

    # Choose N (5) longest vectors and sum them for input and output to get momentum vector
    # PLOT MOMENTUM VECTORS

    # Perform SVD on the two vectors to get transformation matrix
    # Transform all point using the transformation vector
    # PLOT TRANSFORMED POINTS 



