/*
 * 
 * This class represents a game instance with the 4 x 4 game board. 
 * 
 */

import java.util.*;

public class GameBoard {
	
	int[][] board;
	List<Integer> putHistory;
	int[] columnSize;
	int[] columnValue;
	int[] rowValue;
	int numberOfTokens;
	
	public GameBoard() {
		
		// create a 4 x 4 board 
		board = new int[4][4];
		
		// initialize a list to keep track of columns of successful put
		putHistory = new LinkedList<Integer>();	
		
		// initialize an array to keep track of the number of token in each columns
		columnSize = new int[4];
		
		// initialize an array to represent the value of each column (used when checking for win)
		columnValue = new int[4];
		
		// initialize an array to represent the value of each row (used when checking for win)
		rowValue = new int[4];
		
		// no token has been placed;
		numberOfTokens = 0;
	}
	
	public boolean put(int player, int column) {
		
		// assign 1 to player 1, -1 to player 2
		int value = player == 1 ? 1 : -1;
		
		if (columnSize[column - 1] == 4) {
			
			// column is full, error
			return false;
		} else {
			
			// bottom row as the first row
			int currentRow = 3 - columnSize[column - 1];
			
			// insert the player into the correct location in the board
			board[currentRow][column - 1] = player;

			// update the column value
			columnValue[column - 1] += value;
			
			// update the row value
			rowValue[currentRow] += value;
			
			// increment the corresponding column by 1
			columnSize[column - 1]++;
						
			// add the column to list of history
			putHistory.add(column);
			
			numberOfTokens++;
			
			return true;
		}
	}
	
	public List<Integer> getHistory() {
		return putHistory;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public boolean isWin(int player) {
		
		// check columns and rows
		for (int i = 0; i < columnValue.length; i++) {
			
			// check every column and row if there's a 4 or -4
			// meaning a player placed token in that row or column 4 times
			if (Math.abs(columnValue[i]) == 4) return true;
			if (Math.abs(rowValue[i]) == 4) return true;
		}
		
		// two counters for two diagonals
		int first = 0;
		int second = 0;
		
		// check two diagonals
		for (int i = 0; i < 4; i++) {
			if (board[i][i] == player) first++;
			if (board[i][3 - i] == player) second++;
		}
		
		// if any diagonal has 4 of player's token, then win 
		return first == 4 || second == 4;
		
	}
	
	public boolean isDraw(int player) {
		// if not win and board is full
		return !isWin(player) && numberOfTokens == 16;
	}
}
