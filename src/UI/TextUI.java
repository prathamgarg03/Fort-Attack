package src.UI;

import src.Model.BoardManager;
import src.Model.Cell;
import src.Model.Fort;
import src.Model.GameManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TextUI {

    //prints the board
    public void printBoard(BoardManager boardManager, GameManager gameManager){
        ArrayList<ArrayList<Cell>> board = boardManager.getBoard();
        System.out.println("Game Board:");
        int col = 1;
        char row = 'A';
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-3d", col++);
        }
        System.out.println();

        // Print row headers and board values
//        for (int i = 0; i < 10; i++) {
//            System.out.printf("%-2c", row++);
//            for (int j = 0; j < 10; j++) {
//                System.out.printf("%-3s", board.get(i).get(j).getValue());
//            }
//            System.out.println();
//        }
        IntStream.range(0, 10)
                .forEach(i -> {
                    char currentRow = (char) ('A' + i);
                    System.out.printf("%-2c", currentRow);
                    IntStream.range(0, 10)
                            .forEach(j -> System.out.printf("%-3s", board.get(i).get(j).getValue()));
                    System.out.println();
                });

        System.out.println("Opponents points:"+ gameManager.getTotalPoints() +"/ 2500.");
    }

    //prints the final board
    public void printFinalBoard(BoardManager boardManager, GameManager gameManager){
        ArrayList<ArrayList<Cell>> board = boardManager.getBoard();
        System.out.println("Game Board:");
        int col = 1;
        char row = 'A';
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-3d", col++);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-2c", row++);
            for (int j = 0; j < 10; j++) {
                Cell cell = board.get(i).get(j);
                if(cell.getValue() == 'X'){
                    System.out.printf("%-3c", Character.toLowerCase(cell.getFortName()));
                }
                else if(cell.getFortName() == ' '){
                    if(cell.getValue() =='~'){
                        System.out.print(".  ");
                    }
                    else{
                        System.out.print("   ");
                    }
                }
                else{
                    System.out.printf("%-3c",cell.getFortName());
                }
            }
            System.out.println();
        }

        System.out.println("Opponents points:"+ gameManager.getTotalPoints() +"/ 2500.");
        System.out.println("(Lower case fort letters are where you shot.)");
    }

    //print Cheat Board
    public void printCheatBoard(BoardManager boardManager, GameManager gameManager){
        ArrayList<ArrayList<Cell>> board = boardManager.getBoard();
        System.out.println("Game Board:");
        int col = 1;
        char row = 'A';
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%-3d", col++);
        }
        System.out.println();

        IntStream.range(0, 10).forEach(i -> {
            char currentRow = (char) ('A' + i);
            System.out.printf("%-2c", currentRow);
            IntStream.range(0, 10).forEach(j -> {
                Cell cell = board.get(i).get(j);
                if (cell.getFortName() == ' ') {
                    System.out.print(".  ");
                } else {
                    System.out.printf("%-3c", cell.getFortName());
                }
            });
            System.out.println();
        });

        System.out.println("Opponents points:"+ gameManager.getTotalPoints() +"/ 2500.");
        System.out.println("(Lower case fort letters are where you shot.)");
        System.out.println("");
    }

    //Show Oppoenets Points
    public void showOpponentsPoints(GameManager gameManager){
        ArrayList<Fort> fortList = gameManager.getFortList();
        IntStream.range(0, fortList.size())
                .forEach(i -> System.out.println("Opponent #" +
                        (i + 1) + " of " + fortList.size() +
                        " shot you for " + fortList.get(i).getLastPointScored() +
                        " points!"));

    }

    public int start(int forts, boolean isCheat){
        boolean isDone = false;
        BoardManager boardManager = new BoardManager();
        GameManager gameManager = new GameManager();
        boolean isGameCreated = gameManager.fortListCreation(forts);
        if(!isGameCreated){
            return 1;
        }
        boardManager.boardCreation(gameManager);

        if(isCheat){
            printCheatBoard(boardManager,gameManager);
        }

        System.out.println("Starting game with " +forts+ " forts.");
        System.out.println("------------------------");
        System.out.println("Welcome to Fort Defense!");
        System.out.println("by Ruben and Pratham");
        System.out.println("------------------------");

        while(!isDone) {
            printBoard(boardManager,gameManager);

            System.out.println("Enter Your Move:");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            //Give the result as hit or miss
            //returns false if input was invalid and loop continues
            int flagBoard = boardManager.isHit(userInput,gameManager);
            if(flagBoard == -1){
                System.out.println("Invalid target. Please enter a coordinate such as D10.");
                continue;
            } else if (flagBoard == 1) {
                System.out.println("Miss.");
            } else if (flagBoard == 2) {
                System.out.println("Hit.");
            }

            //Show Opponents point;
            showOpponentsPoints(gameManager);

            //Check winning or losing condition to exit the loop
            if(gameManager.hasPlayerLost()){
                isDone = true;
                System.out.println("I'm sorry, your fort is all wet! They win!");
            }
            if(gameManager.hasPlayerWon()){
                isDone = true;
                System.out.println("Congratulations! You won!");
            }
        }
        printFinalBoard(boardManager,gameManager);
        return 0;

    }
}
