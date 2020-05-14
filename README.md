# GameAIFinalSP2020
A Java implementation of Chess incorporating AI techniques from the Spring Semester by David Gralla and Lamonte Brooks.

Game consists of a Board displayed over console, with the human making the first move. Moves are entered in the format of a string from standard input: Piece indicated by a character (K, B, Kn, P, Q, R) followed by its corresponding square (in rank, file format) and then the square it would like to move to"

Example: "P b2 b4" -> Creates move for a Pawn at square B2 and attempts to move it to B4.

Currently, the implemenation supports castling, but not en passsant and pawn promotion. 

Additionally implemented is a recursive Minimax Agent algorithm that consults the available board states (up to a depth of 2, due to recursion) and makes a move based upon the score of the pieces on the board. Alpha-beta pruning has been implemented to reduce the wait time of computation (about 4-8 seconds for a move). The ai is capable of castling, and will attempt to not have its pieces captured. 

Possible improvements: Inclusion of en passant and pawn promotion. Additionally, the minimax algorithm could be applied better in a node structure in place of recursion to assist with the effect of Alpha Beta pruning on performance, and/or implementation of an additional search algorithm such as MCTS.  
