package baseball.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import baseball.model.Game;
import baseball.validation.NumberValidator;
import baseball.view.InputView;
import camp.nextstep.edu.missionutils.Randoms;

public class ComputerController {
	private static final String NOTHING_MESSAGE = "낫싱";
	private static final String STRIKE_MESSAGE = "스트라이크";
	private static final String BALL_MASSAGE = "볼";
	private static final String SPACE_MESSAGE = " ";
	private static final String NULL_MESSAGE = "";
	private static final int BASEBALL_GAME_NUMBER_LENGTH = 3;

	InputView inputView = new InputView();

	public List<Integer> getComputerNumber() {
		List<Integer> computer = new ArrayList<>();

		while (computer.size() < BASEBALL_GAME_NUMBER_LENGTH) {
			int randomNumber = Randoms.pickNumberInRange(1, 9);
			if (!computer.contains(randomNumber)) {
				computer.add(randomNumber);
			}
		}
		return computer;
	}

	public Game getPlayerNumbers() {
		String playerInputNumber = inputView.enterGameNumber();

		NumberValidator numberValidator = new NumberValidator(playerInputNumber);

		Game playerGameData = new Game();
		List<Integer> playerNumbers = convertNumberStringToList(playerInputNumber);
		playerGameData.setGameNumbers(playerNumbers);

		return playerGameData;
	}

	public List<Integer> convertNumberStringToList(String stringNumber) {
		return stringNumber.chars()
			.mapToObj(Character::getNumericValue)
			.collect(Collectors.toList());
	}

	public Game compareNumber(Game computerData, Game playerData) {
		int strikeCount = 0;
		int ballCount = 0;
		for (int i = 0; i < BASEBALL_GAME_NUMBER_LENGTH; i++) {
			for (int j = 0; j < BASEBALL_GAME_NUMBER_LENGTH; j++) {
				if ((computerData.getGameNumbers().get(i) == playerData.getGameNumbers().get(j)) && (i == j)) {
					strikeCount++;
				} else if ((computerData.getGameNumbers().get(i) == playerData.getGameNumbers().get(j)) && (i != j)) {
					ballCount++;
				}
			}
		}

		playerData.setStrikeCountAndBallCount(strikeCount, ballCount);

		return playerData;
	}

	public String strikeAndBallMessage(Game playerData) {
		String message = NULL_MESSAGE;
		if (isNothing(playerData)) {
			message += NOTHING_MESSAGE;
		}
		if (isBall(playerData)) {
			message += playerData.getBallCount() + BALL_MASSAGE + SPACE_MESSAGE;
		}
		if (isStrike(playerData)) {
			message += playerData.getStrikeCount() + STRIKE_MESSAGE;
		}
		return message;
	}

	public boolean isStrike(Game playerData) {
		return playerData.getStrikeCount() > 0;
	}

	public boolean isBall(Game playerData) {
		return playerData.getBallCount() > 0;
	}

	public boolean isNothing(Game playerData) {
		return playerData.getStrikeCount() == 0 && playerData.getBallCount() == 0;
	}

	public boolean isThreeStrike(Game playerData) {
		return playerData.getStrikeCount() == BASEBALL_GAME_NUMBER_LENGTH;
	}
}
