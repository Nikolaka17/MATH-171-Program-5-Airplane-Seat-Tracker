// Author: Nikolas Leslie
// Date created: 11/5/22
// Last modified: 11/10/22
// Software for reserving an airplane seat

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

        boolean[][] seatsTaken = new boolean[NUM_ROWS][NUM_COLUMNS];
		Point seat;

        for(int i = 0; i < seatsTaken.length; i++){
            for(int j = 0; j < seatsTaken[i].length; j++){
                seatsTaken[i][j] = false;
            }
        }
		
		do{
			
			seat = displayMenu(seatsTaken);

			if (seat == null){
				continue;
			}

			try{
				isValid(seat);
			}catch(IndexOutOfBoundsException e){
				System.out.println(e.getMessage());
				continue;
			}catch(IllegalArgumentException e){
				System.out.println("Exiting program");
				System.exit(0);
			}

			if(seatsTaken[seat.y][seat.x]){
				System.out.println("Sorry, that seat is currently taken.");
			}else{
				System.out.print("You have reserved the seat ");
				System.out.print(seat.x + 1);
				System.out.print((char)(seat.y + 'A'));
				System.out.print(" .\n");
				seatsTaken[seat.y][seat.x] = true;
			}
			
		}while(true);
    }

	/**
	 * Prints a formatted representation of the argument
	 * @author Nikolas Leslie
	 * @param arr An array of booleans to print
	 */
    public static void arrPrint(boolean[][] arr){
		String[][] table = new String[NUM_ROWS + 1][NUM_COLUMNS + 1];
		table[0][0] = " ";
		for(int i = 1; i <= arr[i].length; i++){
			table[0][i] = Character.toString((char)(i + '@'));
		}
        
		for(int i = 0; i < arr.length; i++){
			table[i+1][0] = Integer.toString(i + 1);
			for(int j = 0; j < arr[i].length; j++){
				table[i+1][j+1] = Character.toString((arr[i][j])? TAKEN_SEAT : OPEN_SEAT);
			}
		}

		for(String[] row : table){
			for(String item : row){
				System.out.printf("%5s", item);
			}
			System.out.println();
		}
    }
	
	/**
	 * Tests if the given input point is valid
	 * @author Nikolas Leslie
	 * @param seat The point (x,y) value to be tested
	 * @throws IndexOutOfBoundsException Tells if given column and/or row number is in the bounds
	 * @throws IllegalArgumentException Thrown if exit input is given
	 */
	public static void isValid(Point seat){
		if(seat.x == -1 && seat.y == -1){
			throw new IllegalArgumentException("Default exception input given");
		}else if(seat.x < 0 || seat.x > NUM_COLUMNS){
			if(seat.y < 0 || seat.y > NUM_ROWS){
				throw new IndexOutOfBoundsException("Both column and row are invalid");
			}else{
				throw new IndexOutOfBoundsException("Column is invalid");
			}
		}else if(seat.y < 0 || seat.y > NUM_ROWS){
			throw new IndexOutOfBoundsException("Row is invalid");
		}
	}

	/**
	 * Gathers input from the user
	 * @author Nikolas Leslie
	 * @param seats The array to print out for the menu
	 * @return A point representation or the seat number the user gives
	 */
	public static Point displayMenu(boolean[][] seats){
		Scanner stdin = new Scanner(System.in);

		System.out.println("Current reservations: ");
		arrPrint(seats);
		System.out.println("\nWould you like to reserve a seat or exit?\n");
		System.out.println("\tA) Reserve a seat\n\tB) Quit\n");

		String choice = stdin.nextLine();
		switch (choice.toUpperCase()){
			case "RESERVE": case "R": case "A": case "RESERVE A SEAT":
				int column = 0;
				int row = 0;
				Classes section = null;
				boolean notValidSection = true;
				boolean notValidRow = true;
				boolean notValidColumn = true;
				while(notValidSection){
					System.out.print("What area would you like to sit in? ");
					String area = stdin.nextLine();
					switch(area.toUpperCase()){
						case "FIRST": case "FIRST CLASS": case "F":
							section = Classes.FIRST;
							notValidSection = false;
							break;
						case "BUSINESS": case "BUSINESS CLASS": case "B":
							section = Classes.BUSINESS;
							notValidSection = false;
							break;
						case "ECONOMY": case "ECON": case "ECONOMY CLASS": case "ECON CLASS": case "E":
							section = Classes.ECONOMY;
							notValidSection = false;
							break;
						default: 
							System.out.println("That isn't a valid area. Valid areas are First, Business, and Economy.");
							break;
					}
				}
				while(notValidRow){
					System.out.print("What row number is your seat? (Numbers for ");
					switch(section){
						case FIRST:
							System.out.print("first class are ");
							for (int i = 1; i <= FIRST_CLASS_END; i++){
								System.out.print(i);
								System.out.print(", ");
							}
							System.out.print("\b\b) ");
							break;
						case BUSINESS:
							System.out.println("business class are ");
							for (int i = FIRST_CLASS_END + 1; i<= BUSINESS_CLASS_END; i++){
								System.out.print(i);
								System.out.print(", ");
							}
							System.out.print("\b\b) ");
							break;
						case ECONOMY:
							System.out.println("economy class are ");
							for (int i = BUSINESS_CLASS_END + 1; i <= NUM_ROWS; i++){
								System.out.print(i);
								System.out.print(", ");
							}
							System.out.print("\b\b) ");
							break;
					}
					if(stdin.hasNextInt()){
						row = stdin.nextInt() - 1;
						notValidRow = false;
					}else{
						System.out.println("Please enter a valid number");
					}
				}
				while(notValidColumn){
					stdin.nextLine();
					System.out.print("What column would you like? (Columns are ");
					for(int i = 0; i < NUM_COLUMNS; i++){
						System.out.print((char)(i + 'A'));
						System.out.print(", ");
					}
					System.out.print("\b\b) ");
					String columnChoice = stdin.nextLine();

					if (columnChoice.length() == 1){
						column = columnChoice.toUpperCase().charAt(0) - 'A';
						notValidColumn = false;
					}else{
						System.out.println("Please enter a single letter representing the column");
					}
				}
				return new Point(column, row);
			case "QUIT": case "Q": case "B":
				return new Point(-1, -1);
			default:
				System.out.println("Input not reconized please try again.");
				return null;
		}
	}
}
