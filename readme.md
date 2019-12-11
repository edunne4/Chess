# Brian Queen
#### A customizable, 3D, multi-player chess game for anyone aged 3 to 103.


## Chess Rules:
In the game of chess chess, two players attempt to strategically capture each other's king by first putting the King in check. This can be done by a player who strategically places the threat of capture on their opponent using one or more of their game pieces. 

Each piece has specific allowable moves. They are as follows:
* The King can move exactly one square horizontally, vertically, or diagonally.
* The Queen can move any number of vacant squares diagonally, horizontally, or vertically.
* The Rook can move any number of vacant squares vertically or horizontally.
* The Bishop can move any number of vacant squares in any diagonal direction.
* The Knight can move one square along any rank or file and then at an angle (in the shape of an "L").
* Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn can be moved two squares forward. Pawns may capture an enemy piece on either of the two spaces diagonally adjacent to the space in front of them.

![Chess Game 2D](/design/screenshot2D.png)
![Chess Game 3D](/design/screenshot3D.png)
## Chess Implementation
This project is a GUI representation of the game described above. In its most basic form, this game can be payed by two players at the same computer. The bottom team always starts and the player can begin by simply choosing a piece to move. Gameplay continues with alternating turns between the bottom and the top team. 

For users wishing to play the game with advanced graphics, the "Settings" menu will allow 3D graphics to be enabled. In addition, a player may customize the board by selecting one of the 9 different colors for his/her game pieces.

This implementation of Chess also supports network play. If a player desires to engage in competition with someone on another computer, the player can use the "Multiplayer" menu to chose to host or join a game. In network play, each user has the opportunity to customize their view and independent of their opponent's view preferences. If a user wishes to quit or restart the game, these options are presented in the "Quit/Restart Game" menu.

## Installation and Run
To run the source code, please use your package manager of choice. If you would like a prebuilt version of the game, download and run the .jar file. 

## Project Authors
This project was completed by Ryan Bailis, James Campbell, Ethan Dunne, and Jake Schaeffer. All work is their own. Citations and references are provided in file headers as appropriate. 

## Libraries and Resources
[JavaFX] (https://openjfx.io) 
[Interactive Mesh] (http://www.interactivemesh.org/models/jfx3dimporter.html)

All other resources are documented in file headers as appropriate.

## Contributing
The MasterMind Model developers are not accepting outside contributors at this time.

## Project Details
This project was developed as part of a class assignment. CSCI205, Software Engineering & Design at [Bucknell University](https://bucknell.edu) is a course focused on exposing students to real-world Java development, class design, and the principles of software engineering.

## License
[MIT](https://choosealicense.com/licenses/mit/)



