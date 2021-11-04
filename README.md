Sudoku
======
The aim is to create sudoku where a user will have a choice to generate any board size (4x4, 16x16, (n^2)x(n^2)) and difficulty (number of empty squares).


# Features
> * Solve randomly generated boards (4x4, 9x9, 16x16)
> * Be able to choose difficulty (1 - 25% of numbers are missing, 2 - 50% and 3 - 75%)
> * Generate a number of boards and save it to a txt file


# In development
> * Generate boards of any size ((n^2)x(n^2) for 1 <= n <= 11)
> * Generate a number of boards and save it to a pdf file (for printing)



Examples of boards
------------------
#### 9x9 difficulty - 3
```java
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
  ┗━━━━━━━┻━━━━━━━┻━━━━━━━┛
  ```
#### 16x16 difficulty - 3
```java
     1  2  3  4    5  6  7  8    9  10 11 12   13 14 15 16   
   ┏━━━━━━━━━━━━━┳━━━━━━━━━━━━━┳━━━━━━━━━━━━━┳━━━━━━━━━━━━━┓
1  ┃    8        ┃    11    5  ┃    4        ┃             ┃
2  ┃             ┃ 2           ┃ 7        6  ┃    12       ┃
3  ┃             ┃ 10          ┃             ┃             ┃
4  ┃             ┃    12 6     ┃    13       ┃ 5     16    ┃
   ┣━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━┫
5  ┃    4        ┃ 13 16       ┃ 8     6  15 ┃       12    ┃
6  ┃             ┃             ┃          13 ┃             ┃
7  ┃             ┃       3     ┃       10    ┃ 7           ┃
8  ┃ 13 15       ┃       5     ┃       3     ┃             ┃
   ┣━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━┫
9  ┃             ┃          4  ┃          8  ┃ 16 11       ┃
10 ┃ 12          ┃    2        ┃    1        ┃             ┃
11 ┃       15    ┃    13       ┃             ┃ 3     9     ┃
12 ┃ 5           ┃    3        ┃ 13       14 ┃ 4  8     1  ┃
   ┣━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━━━━┫
13 ┃             ┃ 3     4     ┃             ┃ 14          ┃
14 ┃    5  6     ┃       13 11 ┃             ┃    2        ┃
15 ┃             ┃          2  ┃    16    5  ┃          3  ┃
16 ┃             ┃ 16          ┃ 6           ┃ 12    4  5  ┃
   ┗━━━━━━━━━━━━━┻━━━━━━━━━━━━━┻━━━━━━━━━━━━━┻━━━━━━━━━━━━━┛
  ```

#### 4x4 difficulty - 2
```java
    1 2   3 4
  ┏━━━━━┳━━━━━┓
1 ┃ 3   ┃   2 ┃
2 ┃   4 ┃     ┃
  ┣━━━━━╋━━━━━┫
3 ┃ 4 2 ┃ 3 1 ┃
4 ┃ 1   ┃     ┃
  ┗━━━━━┻━━━━━┛
  ```
