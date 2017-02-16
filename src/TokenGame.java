
import java.util.*;

public class TokenGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard game = new GameBoard();
		
		Scanner console = new Scanner(System.in);
		
		// game starts with player 1
		int player = 1;
		
		boolean exit = false;
		
		while (!exit) {
			
			// get user input
			System.out.print("> ");
			String[] command = parseInput(console.nextLine());
			
			if (command.length < 1 || command.length > 2) {
				
				System.out.println("INVALID COMMAND");
				
			} else if (command[0].equals("exit")) {
				exit = true;
			} else if (command[0].equals("put")) {
				
				// make sure put comes with a column number
				if (command.length == 2) {
					
					int column = Integer.valueOf(command[1]);
					
					if (column > 4 || column < 1) {
						
						System.out.println("INVALID COMMAND: COLUMN MUST BE BETWEEN 1 AND 4");
						
					} else if (!game.put(player, Integer.valueOf(command[1]))) {
						
						// invalid put, column is full
						System.out.println("ERROR");
					} else {
						
						// successful put, check for win and draw for the current player
						if (game.isWin(player)) {
							
							System.out.println("WIN");
							
						} else if (game.isDraw(player)) {
							
							System.out.println("DRAW");
							
						} else {
							
							System.out.println("OK");
							
						}
						
						// alternate turns
						player = player == 1 ? 2 : 1; 
					}
				} else {
					System.out.println("INVALID COMMAND");
				}
				
			} else if (command.length > 1) {
				
				// if it's not a put command, it's invalid to have more than 1 argument
				System.out.println("INVALID COMMAND");
				
			} else if (command[0].equals("get")) {
				
				List<Integer> history = game.getHistory();
				printHistory(history);
				
			} else if (command[0].equals("board")) {
				
				int[][] board = game.getBoard();
				printBoard(board);
			} else {
				System.out.println("INVALID COMMAND");
			}
			
		}
		
		// close the input stream, exit the program
		console.close();
		
	}
	
	public static String[] parseInput(String command) {
		
		String lowerCase = command.toLowerCase();
		
		// breaks the input into two parts by the space
		// command put has two arguments
		String[] args = lowerCase.split(" ");
		
		return args;
	}
	
	public static void printHistory(List<Integer> history) {
		
		for (int column: history) {
			System.out.println(column);
		}
	}
	
	public static void printBoard(int[][] board) {
		for (int i = 0; i < 4; i++) {
			
			System.out.print("|");
			
			for (int j = 0; j < 4; j++) {
				System.out.print(" " + board[i][j]);
				
			}
			
			System.out.println();
		}
		
		System.out.println("+--------");
		System.out.println("  1 2 3 4");
	}

}
