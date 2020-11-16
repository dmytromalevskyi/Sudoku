Sudoku
======
The aim is to create sudoku where a user will have a choice to generate any board size (4x4, 10 x 10 and n^2 x n^2) and difficulty (number of empty squares).

Features (in development)
-------------------------
* Solve randomly generated boards
* Generate boards of any size (n^2 * n^2)
* Be able to choose difficulty (1 - 25% of numbers are missing, 2 - 50% and 3 - 75%)
* Generate a number of boards and save it to a txt file ()
* Generate a number of boards and save it to a pdf file (for printing)

Examples of boards
------------------
### 9x9 difficulty - 3
java'''
    1 2 3   4 5 6   7 8 9
  ┏━━━━━━━┳━━━━━━━┳━━━━━━━┓
1 ┃       ┃     8 ┃   9   ┃
2 ┃ 6 4   ┃ 9     ┃       ┃
3 ┃       ┃       ┃ 4   7 ┃
  ┣━━━━━━━╋━━━━━━━╋━━━━━━━┫
4 ┃       ┃     9 ┃     5 ┃
5 ┃       ┃   3 2 ┃       ┃
6 ┃     5 ┃   8   ┃       ┃
  ┣━━━━━━━╋━━━━━━━╋━━━━━━━┫
7 ┃   2   ┃ 7 9 4 ┃       ┃
8 ┃ 5   9 ┃       ┃       ┃
9 ┃       ┃   5   ┃       ┃
  ┗━━━━━━━┻━━━━━━━┻━━━━━━━┛'''
  
### 4x4 difficulty - 2
    1 2   3 4
  ┏━━━━━┳━━━━━┓
1 ┃ 3   ┃   2 ┃
2 ┃   4 ┃     ┃
  ┣━━━━━╋━━━━━┫
3 ┃ 4 2 ┃ 3 1 ┃
4 ┃ 1   ┃     ┃
  ┗━━━━━┻━━━━━┛
