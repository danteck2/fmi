import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import random
import math
import numpy as np
import copy

def normat(v):
    if np.linalg.norm(v) != 0:
        return v /  np.linalg.norm(v)
    return v

n_particule = 10
vecinatate = 530
start = 5

x = [np.array([random.uniform(-start,start), random.uniform(-start,start)]) for i in range(n_particule)]
v = [np.array([random.uniform(0,5), random.uniform(0,5)]) for i in range(n_particule)]


def update_x():
    new_v = copy.deepcopy(v)
    for i, particula in enumerate(x):
        # calculeaza new_v[i]
        r = np.zeros(2)
        m = np.zeros(2)
        nr = 0
        m2 = 0
        for j in range (n_particule):
            if np.linalg.norm(x[i] - x[j]) < vecinatate:
                r+=x[i]-x[j]
                m = m + x[j]
                m2 = m2 + v[j]
                nr = nr + 1
        if (nr > 1):
            m = m / (nr - 1)
            m2 = m2 / (nr - 1)
        new_v[i] = normat(r) + v[i] + normat(m - x[i]) + normat(m2 - v[i])

    for i, particula in enumerate(x):
        x[i] += new_v[i]
        v[i] = new_v[i]


# ANIMATIE

fig, ax = plt.subplots()
xdata, ydata = [], []
ln, = plt.plot([], [], 'bo', animated=True)


def init():
    ax.set_xlim(-100, 500)
    ax.set_ylim(-100, 500)
    return ln,


def update(frame):
    update_x()
    xdata = [e1 for e1, e2 in x]
    ydata = [e2 for e1, e2 in x]
    ln.set_data(xdata, ydata)
    return ln,


ani = FuncAnimation(fig, update,
                    # frames=np.linspace(0, 2*np.pi, 128),
                    interval=100,
                    init_func=init, blit=True)
plt.show()
