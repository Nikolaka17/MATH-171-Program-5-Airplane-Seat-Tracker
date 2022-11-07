// Author: Nikolas Leslie
// Date created: 11/5/22
// Last modified: 11/6/22
// Software for choosing a airplane seat

import java.util.Scanner;
import java.awt.Point;

public class Reservations {

    final static int NUM_ROWS = 15;
    final static int NUM_COLUMNS = 5;
	final static int FIRST_CLASS_END = 2;
	final static int BUSINESS_CLASS_END = 6;
    final static char OPEN_SEAT = 'O';
    final static char TAKEN_SEAT = 'X';
	
	enum Classes {
		FIRST,
		BUSINESS,
		ECONOMY
	}

    public static void main(String[] args){

        Scanner stdin = new Scanner(System.in);
        boolean[][] seatsTaken = new boolean[NUM_ROWS][NUM_COLUMNS];
		int column;
		int row;

        for(int i = 0; i < seatsTaken.length; i++){
            for(int j = 0; j < seatsTaken[i].length; j++){
                seatsTaken[i][j] = false;
            }
        }
		
		do{
			
			try{
				isValid(new Point(column, row));
			}catch(IndexOutOfBoundsException e){
				System.out.println(e.getMessage());
			}catch(IllegalArgumentException e){
				System.out.println("Exiting program");
				System.exit(0);
			}
			
		}while(true);
    }

    public static void arrPrint(boolean[][] arr){
		System.out.print("  ");
		for(int i = 1; i <= arr[i].length; i++){
			System.out.print((char)(i + '@'));
			System.out.print(" ");
		}
		System.out.println();
        
		for(int i = 0; i < arr.length; i++){
			System.out.print(i);
			System.out.print(" ");
			for(int j = 0; j< arr[i].length; j++){
				System.out.print((arr[i][j])? TAKEN_SEAT : OPEN_SEAT);
				System.out.print(" ");
			}
			System.out.println();
		}
    }
	
	public static void isValid(Point seat){
		if(seat.x == -1 && seat.y == -1){
			throw new IllegalArgumentException("Default exception input given");
		}else if(seat.x < 1 || seat.x > NUM_COLUMNS){
			if(seat.y < 1 || seat.y > NUM_ROWS){
				throw new IndexOutOfBoundsException("Both column and row are invalid");
			}else{
				throw new IndexOutOfBoundsException("Column is invalid");
			}
		}else if(seat.y < 1 || seat.y > NUM_ROWS){
			throw new IndexOutOfBoundsException("Row is invalid");
		}
	}
}
