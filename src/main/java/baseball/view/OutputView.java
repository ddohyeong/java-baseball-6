package baseball.view;

public class OutputView {
	public static final String GAME_END_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

	public void printEndGame() {
		System.out.println(GAME_END_MESSAGE);
	}

	public void printStrikeAndBallMessage(String message) {
		System.out.println(message);
	}
}