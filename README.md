# Forts Attack

This is a simple console-based game implemented in Java. The game revolves around the concept of forts and cells. Each fort is made up of cells and the goal is to destroy the opponent's forts by hitting their cells.

## Classes

The project consists of several classes:

- `Fort`: Represents a fort in the game. It has properties such as name, points, undamaged cells, etc.
- `Cell`: Represents a cell in the game. It has properties such as position, value, and the name of the fort it belongs to.
- `BoardManager`: Manages the game board. It is responsible for creating the board and determining if a user's move is a hit or miss.
- `Main`: The entry point of the application. It parses command line arguments and starts the game.

## How to Run

To run the game, you need to have Java installed on your machine. You can then compile and run the `Main` class. The game accepts two optional command line arguments:

- The first argument is the number of forts.
- The second argument is `--cheat`, which enables cheat mode.

Example:

```bash
java Main 5 --cheat
```

This will start the game with 5 forts and cheat mode enabled.

## Game Rules

The game board is a 10x10 grid. Each cell in the grid can be empty, contain a part of a fort, or be a hit or miss. The goal of the game is to destroy all of the opponent's forts. A fort is destroyed when all of its cells have been hit.

## Contributing

Contributions are welcome. Please feel free to submit a pull request or open an issue.
