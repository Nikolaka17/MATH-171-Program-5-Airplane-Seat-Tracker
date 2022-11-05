// Author: Nikolas Leslie
// Date created: 11/5/22
// Last modified: 11/5/22
// Software for choosing a airplane seat

import java.util.Scanner;

public class Airplane {

    final static int NUM_ROWS = 15;
    final static int NUM_COLUMNS = 5;
    final static char OPEN_SEAT = 'O';
    final static char TAKEN_SEAT = 'X';

    public static void main(String[] args){

        boolean running = true;
        Scanner stdin = new Scanner(System.in);
        boolean[][] seatsTaken = new boolean[NUM_ROWS][NUM_COLUMNS];
        String[][] seatsOccupent = new String[NUM_ROWS][NUM_COLUMNS];

        for(int i = 0; i < seatsTaken.length; i++){
            for(int j = 0; j < seatsTaken[i].length; j++){
                seatsTaken[i][j] = false;
                seatsOccupent[i][j] = "";
            }
        }

    }

    public static void arrPrint(boolean[][] arr){

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[j].length; j++){
                System.out.print(((arr[i][j])? TAKEN_SEAT : OPEN_SEAT));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
