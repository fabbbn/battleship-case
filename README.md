# battleship-case
Battleship is a game played between 2 players.
1. Each player will be initialised with an M * M grid with ’S’ number of ships placed at specified positions on the grid.
2. One battleship occupies a single position on the grid.
3. It takes a single shot to bring down the battleship of an opponent.
4. The objective of the game is to destroy the opponent's battleships. Each player will be given ’T’ number of missiles to bring down the fleet of opposition. The player who does the most damage/brings down the ships of the opposition wins
5. This will be a simulation of an actual battle wherein each player's actions are given as an input to the program.
6. Based on hits / misses of a missile on the opponent, either of the players might be victorious or the game might end as a draw

Inputs
1. M is the grid size [Matrix of M*M]
2. S is the total ships
3. P1 Ship Positions: 1,1:2,0:2,3:3,4,... (x,y pairs separated by colon)
4. P2 Ship Positions: 0,1:2,0:2,3:3,4,...
5. T: Total Missiles
6. P1 missile positions: 1,1:2,0… (x,y pairs of length ’T’)
7. P2 missile positions: 1,2:3,0… (x,y pairs of length ‘T’)

## SAMPLE INPUT:
```
5
5
1,1:2,0:2,3:3,4:4,3
0,1:2,3:3,0:3,4:4,1
5
0,1:4,3:2,3:3,1:4,1
0,1:0,0:1,1:2,3:4,3
```
## OUTPUT
Output should be written to a file that should contain the following information:
1. Player 1 and Player 2 grids after the battleship simulation.
2. Alive Battleships denoted with “B”
3. Dead Battleships with “X” (if missile hit the battleship) HIT
4. Missile Missed Locations “O” (if the missile location didn’t have a ship) MISS
5. Final result:
▪ P1:total hits
▪ P2:total hits

## SAMPLE OUTPUT:
```
Player1
O O _ _ _ 
_ X _ _ _ 
B _ _ X _ 
_ _ _ _ B 
_ _ _ X _ 

Player2
_ X _ _ _ 
_ _ _ _ _ 
_ _ _ X _ 
B O _ _ B 
_ X _ O _ 

P1:3
P2:3
It is a draw
```
