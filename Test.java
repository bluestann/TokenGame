
public class Test {

	public static void main(String[] args) {
		GameBoard game = new GameBoard();
		testPut(game);
		testError(game);
		testHistory(game);
	}
	
	public static void testHistory(GameBoard game) {
		assert game.putHistory.get(0) == 1;
		assert game.putHistory.get(1) == 1;
		assert game.putHistory.get(2) == 1;
		assert game.putHistory.get(3) == 1;
		assert game.putHistory.get(4) == 2;
		assert game.putHistory.get(5) == 3;
	}
	
	public static void testError(GameBoard game) {
		// column full, should return false for put
		assert game.put(1, 1) == false;
		assert game.put(2, 1) == false;
		
		// other column still has space
		assert game.put(1, 2) == true;
		assert game.put(1, 3) == true;
	}
	
	public static void testPut(GameBoard game) {
		game.put(1, 1);
		game.put(2, 1);
		game.put(2, 1);
		game.put(1, 1);
		assert game.columnSize[0] == 4;
		assert game.columnSize[1] == 0;
		assert game.columnSize[2] == 0;
		assert game.columnSize[3] == 0;
		
		assert game.board[0][0] == 1;
		assert game.board[1][0] == 2;
		assert game.board[2][0] == 2;
		assert game.board[3][0] == 1;
	}

}
