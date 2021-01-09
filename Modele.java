import java.util.concurrent.ThreadLocalRandom;

public class Modele  {
	private static final long serialVersionUID = 1L;
	///////////////////////////////// TIC-TAC-TOE ///////////////////////////////////

	private int[][] board;
	private boolean isPlayerWon;
	private boolean isComputerWon;

	public Modele()
	{
		board = new int[][]{
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};

		isPlayerWon = false;
		isComputerWon = false;
	}

	public void playerTurn(String coordinates)
	{
		int x_coord = Character.getNumericValue(coordinates.charAt(0));
		int y_coord = Character.getNumericValue(coordinates.charAt(1));
		board[x_coord][y_coord] = 1;
	}

	public String computerTurn()
	{
		boolean isBoardFull = true;
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				if (board[i][j] == 0)
					isBoardFull = false;
			}
		}

		if (isBoardFull == true)
			return "";

		for(int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				if(board[i][j] == 0)
				{
					board[i][j] = 2;
					if(isWon() && isComputerWon)
					{
						return String.valueOf(i) + String.valueOf(j);
					}
					else
						board[i][j] = 0;
				}
			}
		}

		for(int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length; j++)
			{
				if(board[i][j] == 0)
				{
					board[i][j] = 1;
					if(isWon() && isPlayerWon)
					{
						isPlayerWon = false;
						board[i][j] = 2;
						return String.valueOf(i) + String.valueOf(j);
					}
					else
						board[i][j] = 0;
				}
			}
		}

		int x_rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		int y_rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		while (board[x_rand][y_rand] != 0)
		{
			x_rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);
			y_rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		}
		board[x_rand][y_rand] = 2;
		return String.valueOf(x_rand) + String.valueOf(y_rand);
	}

	public boolean isWon()
	{

		for(int i = 0; i < board.length; i++)
		{
			if (
					((board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1)
							|| (board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1)
							|| (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1)
							|| (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1))
			)
			{
				isPlayerWon = true;
				return true;
			}

			if (
					((board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2)
							|| (board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2)
							|| (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2)
							|| (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2))
			)
			{
				isComputerWon = true;
				return true;
			}
		}

		boolean isDraw = true;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j]== 0)
					isDraw = false;
			}
		}
		if (isDraw)
			return true;

		return false;
	}

	public void resetGame()
	{
		board = new int[][]{
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};
	}

	public boolean isPlayerWon() {
		return isPlayerWon;
	}

	public boolean isComputerWon() {
		return isComputerWon;
	}
}
