package src.Model;

import java.util.ArrayList;

public class BoardManager {
    //Manages the array of board
    private ArrayList<ArrayList<Cell>> board = new ArrayList<>();

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    //board creation
    public void boardCreation(GameManager gameManager){
        char row ='A';
        for(int i=0;i<10;i++,row++){
            ArrayList<Cell> cellRow = new ArrayList<>();
            int col = 1;
            for(int j=0;j<10;j++,col++){
                String position = row+ Integer.toString(col);
                Cell newCell = new Cell(position,'~',' ');
                char fortName = gameManager.isCellUsedInFort(position);
                newCell.setCellFortName(fortName);
                cellRow.add(newCell);
            }
            board.add(cellRow);
        }
    }

    //returns hit or miss on userInput
    public int isHit(String userInput,GameManager gameManager){
        //return true for hit and false for miss

        int size = userInput.length();
        if(size >3 || size <2){     //For cases like A or A100
            return -1;
        }

        int col = Character.getNumericValue(userInput.charAt(1))-1;
        int row = (int)(Character.toUpperCase(userInput.charAt(0))-'A');

        if(row > 10){               //For cases like Z10
            return -1;
        }
        if(size == 3){
            int check = Character.getNumericValue(userInput.charAt(2));
            if( col>1){             // for cases like A90
                return -1;
            }
            else if(check >0){      //For cases like A11
                return -1;
            }
            else{
                col = 9;
            }
        }

        char value = board.get(row).get(col).getValue();

        gameManager.calculatePointsForFortList();

        if(value == '~'){
            char fortName = board.get(row).get(col).getFortName();
            if(fortName ==' '){
                board.get(row).get(col).setValue(' ');
                return 1;
            }
            else{
                board.get(row).get(col).setValue('X');
                ArrayList<Fort> fortList = gameManager.getFortList();
                for(int i=0;i<fortList.size();i++){
                    if(fortList.get(i).getName() == board.get(row).get(col).getFortName()){
                        fortList.get(i).setUndamagedCells(fortList.get(i).getUndamagedCells()-1);
                        break;
                    }
                }
                gameManager.setFortList(fortList);
                return 2;
            }
        }
        else if(value == 'X'){
            return 2;
        }
        else if(value == ' '){
            return 1;
        }
        return 0;
    }
}
