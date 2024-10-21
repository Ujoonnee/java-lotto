package lotto.controller;

import lotto.domain.Games;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoResult;
import lotto.domain.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

import java.util.Random;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        int money = inputView.askPriceToPay();
        Games games = new Games(money, new RandomNumberGenerator(new Random()));

        outputView.printPurchasedGames(games);

        LottoNumbers winner = new LottoNumbers(inputView.askWinnerLottoNumber());
        LottoResult lottoResult = new LottoResult(winner, games);

        outputView.printResult(lottoResult);
    }
}
