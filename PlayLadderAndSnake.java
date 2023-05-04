import java.util.Scanner;

public class PlayLadderAndSnake {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		LadderAndSnake game = new LadderAndSnake();

		game.play();

		scan.close();
	}

}
