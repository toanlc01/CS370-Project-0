import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Tictactoegame {
	private static final Random random = new Random();
	private final TicTacToeBoard board = new TicTacToeBoard();
	private final Scanner scanner = new Scanner(System.in);

	public void play() {
		Boolean isHumanTurn = random.nextInt(2) == 0;
		System.out.println("Let's play tic tac toe!");
		System.out.println("You are X and the computer is O.");

		if (isHumanTurn) {
			System.out.println("You go first.");
		} else {
			System.out.println("Computer goes first.");
		}

		scanner.nextLine();
		System.out.println("Press Enter to begin:");

		while (true) {
			if (isHumanTurn) {
				humanTurn(board);
			} else {
				computerTurn(board);
			}
			System.out.println();

			if (board.isWonBy(isHumanTurn ? "X" : "O")) {
				String player = isHumanTurn ? "You" : "The Computer";
				System.out.println(String.format("%s won!", player));
				break;
			} else if (board.isTied()) {
				System.out.println("It's a tie!");
				break;
			}
			isHumanTurn = !isHumanTurn;
		}
		System.out.println("=== GAME OVER ===");

		return;
	}

	public void humanTurn(TicTacToeBoard board) {
		System.out.println("It's your turn.");
		while (true) {
			int row = getInput("row", board.getSize());
			int column = getInput("column", board.getSize());

			try {
				board.markSpace(row - 1, column - 1, 'X');
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("That space is out of bounds. Please try again.");
			} catch (SpaceTakenException stex) {
				System.out.println("That space is taken. Please try again.");
			}
		}
		return;
	}

	public int getInput(String rowOrColumn, int size) {
		while (true) {
			String question = String.format("What %s ? (1- %d)", rowOrColumn, size);
			System.out.println(question);

			try {
				int number = scanner.nextInt();

				if (number < 1 || number > size) {
					String msg = String.format("Please enter a number in the range 1-%d.", size);
					System.out.println(msg);
				}

				return number;
			} catch (InputMismatchException ex) {
				System.out.println("Please enter an integer.");
			}
		}

	}

	public void computerTurn(TicTacToeBoard board) {
		while(true) {
			int row = random.nextInt(board.getSize());
			int column = random.nextInt(board.getSize());
			
			try {
				board.markSpace(row, column, '0');
				String msg = String.format("The computer marks %d,%d.", row + 1, column + 1);
				System.out.println(msg);
			}
			catch(IndexOutofBoundsException ex)
			catch(SpaceTakenException stex) {
				continue;
			}
		}
	}
}
