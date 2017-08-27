# Artificial Intelligence State Space Search

## - Introduction

State space search is a process used in the field of computer science, including artificial intelligence (AI), in which successive configurations or states of an instance are considered, with the goal of finding a goal state with a desired property. [1]

## - Searches Covered Here

The following are the searches with their frontiers as:
- **Depth First Search (DFS)** - Stack/Recursion
- **Breadth First Search (BFS)** - Queue
- **Iterative Deepening Search (IDS)** - Stack/Recursion + Queue
- **Greedy Best First Search (Greedy)** - Priority Queue
- **A Star Search (A\*)** - Priority Queue

## - Problem Statement

We are given a 2D grid (N x M dimensions) in which our goal is to reach from source to destination using the minimum (optimal) path. 

```
Example:

For Grid:
0 0 0 0 0 
1 0 1 1 0
1 1 0 1 0
1 1 1 0 0
1 1 1 1 0

Source: (0, 0)
Destination: (4, 4)
Optimal Path Cost: 5
Optimal Path: {(0, 0) -> (1, 1) -> (2, 2) -> (3, 3) -> (4, 4)}

Each Problem will run with their own time complexity but will give the same output as above for the same configuration
```

**Solution**

The solution is for N x M dimensional grid. Algorithms optimize their route to find the shortest path as fast as possible. There are 3 input files given that can be tested, whilst other (your own or you friends) test cases can run aswell. 

To create the testcase file, the format is as:

```
N M
source_x source_y
destination_x destination_y
grid of NxM dimensions
```

## - Animations

- **DFS vs BFS Animation**

![BFSvsDFS](http://i1.wp.com/blog.hackerearth.com/wp-content/uploads/2015/05/dfsbfs_animation_final.gif)

- **Iterative Deepening Search Animation**

![IDS](http://www.how2examples.com/artificial-intelligence/images/Iterative-Depth-First-Search.gif)

- **A* Animation**

![A*Animation](https://upload.wikimedia.org/wikipedia/commons/9/98/AstarExampleEn.gif)

## - References

[1] [Wikipedia - State Space Search](https://en.wikipedia.org/wiki/State_space_search)
