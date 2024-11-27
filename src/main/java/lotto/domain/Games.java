package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Games {

    private final AutoGames autoGames;

    public Games(int money) {
        if (money < LottoNumbers.PRICE) {
            throw new IllegalArgumentException("최소 " + LottoNumbers.PRICE + "원이 필요합니다.");
        }
        this.autoGames = new AutoGames(money);
    }

    public Games(List<LottoNumbers> games) {
        this.autoGames = new AutoGames(games);
    }

    public int count() {
        return autoGames.count();
    }

    public List<Rank> checkResult(LottoNumbers winner, LottoNumber bonusLottoNumber) {

        return autoGames.checkResult(winner, bonusLottoNumber);
    }

    @Override
    public String toString() {
        return autoGames.toString();
    }
}
