package src.Model;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    //Manages all the opponents including forts creation
    private ArrayList<Fort> fortList = new ArrayList<>();
    private ArrayList<Cell> cellUsedInForts = new ArrayList<>();

    public ArrayList<Fort> getFortList() {
        return fortList;
    }

    public ArrayList<Cell> getCellUsedInForts() {
        return cellUsedInForts;
    }

    public void setFortList(ArrayList<Fort> fortList) {
        this.fortList = fortList;
    }

    public void addFort(Fort newFort){
        this.fortList.add(newFort);
    }

    //to check if a cell is already in Fort
    public char isCellUsedInFort(String position){
        if(cellUsedInForts.isEmpty()){
            return ' ';
        }
        for (Cell cellUsedInFort : cellUsedInForts) {
            if (cellUsedInFort.getPosition().equals(position)) {
                return cellUsedInFort.getFortName();
            }
        }
        return ' ';
    }

    public boolean isValidIndex(int i,int j){
        return (i < 10 && i >= 0) && (j < 10 && j >= 0);
    }


    public boolean exploreCells(int count,Cell startCell,char fortName){
        int row = (startCell.getPosition().charAt(0)-'A');
        int size = startCell.getPosition().length();
        int col = Character.getNumericValue(startCell.getPosition().charAt(1))-1;
        String position;
        if(size == 3){
            col = 9;
        }
        int startingIndex = cellUsedInForts.size()-1;

        int leftRow = row;
        int leftCol = col -1;
        if(isValidIndex(leftRow,leftCol)){
            position = (char)(leftRow+'A') + Integer.toString(leftCol+1);
            if(isCellUsedInFort(position)==' '){
                Cell newcell = new Cell(position,'~',fortName);
                this.cellUsedInForts.add(newcell);
                count++;
            }
        }
        if(count == 5){
            return true;
        }

        int rightRow = row;
        int rightCol = col+1;
        if(isValidIndex(rightRow,rightCol)){
            position  =  (char)(rightRow+'A') + Integer.toString(rightCol+1);
            if(isCellUsedInFort(position)==' '){
                Cell newcell = new Cell(position,'~',fortName);
                this.cellUsedInForts.add(newcell);
                count++;
            }
        }
        if(count == 5){
            return true;
        }

        int upRow = row-1;
        int upCol = col;
        if(isValidIndex(upRow,upCol)){
            position  =  (char)(upRow+'A') + Integer.toString(upCol+1);
            if(isCellUsedInFort(position)==' '){
                Cell newcell = new Cell(position,'~',fortName);
                this.cellUsedInForts.add(newcell);
                count++;
            }
        }
        if(count == 5){
            return true;
        }

        int downRow = row+1;
        int downCol = col;
        if(isValidIndex(downRow,downCol)){
            position  =  (char)(downRow+'A') + Integer.toString(downCol+1);
            if(isCellUsedInFort(position)==' '){
                Cell newcell = new Cell(position,'~',fortName);
                this.cellUsedInForts.add(newcell);
                count++;
            }
        }
        if(count == 5){
            return true;
        }

        int lastIndex = cellUsedInForts.size()-1;

        if(lastIndex == startingIndex)
            return false;
        else{
            exploreCells(count,cellUsedInForts.get(lastIndex),fortName);
        }
        return true;
    }

    //randomFortGenerator
    public boolean randomFortGenerator(char fortName){
        // create a connected cellArrayForFort

        boolean flag = false;
        boolean exit = false;

        Random random = new Random();
        int col;
        char row;
        String position;
        Cell startCell;
        int counter =0;

        while(!exit){
            int count =0;
            do {
                col = random.nextInt(10);
                row = (char) ('A' + random.nextInt(10));

                position = row + Integer.toString(col+1);

                startCell = new Cell(position, '~', ' ');

            } while (isCellUsedInFort(position) != ' ');

            int startingSize = cellUsedInForts.size();

            startCell.setCellFortName(fortName);
            count++;
            cellUsedInForts.add(startCell);

            flag = exploreCells(count, startCell, fortName);

            int currentSize = cellUsedInForts.size();
            int difference = currentSize- startingSize;
            if(difference<5){
                for(int i=0;i<difference;i++){
                    cellUsedInForts.removeLast();
                }
                counter++;
                if(counter == 10){
                    exit = true;
                }
            }
            else{
                exit = true;
            }
        }
        return flag;
    }
    //FortList creation
    public boolean fortListCreation(int number){
        char fortName ='A';
        for(int i=0;i<number;i++){
            boolean isCreated = randomFortGenerator(fortName);
            if(!isCreated){
                return isCreated;
            }
            Fort newFort = new Fort(fortName,false,5,0,20);
            this.addFort(newFort);
            fortName++;
        }
        return true;
    }

    public void calculatePointsForFortList(){
        int[] points ={0,1,2,5,20,20};
        for (Fort fort : fortList) {
            fort.setLastPointScored(points[fort.getUndamagedCells()]);
            fort.setPoints(fort.getPoints() + points[fort.getUndamagedCells()]);
        }
    }


    //Calculates their total points
    public int getTotalPoints(){
        int sum = 0;
        for (Fort fort : fortList) {
            sum += fort.getPoints();
        }
        return sum;
    }

    //Checks winning and losing condition
    public boolean hasPlayerWon(){
        for (Fort fort : fortList) {
            if (fort.getUndamagedCells() != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasPlayerLost(){
        return this.getTotalPoints() > 2500;
    }
}
