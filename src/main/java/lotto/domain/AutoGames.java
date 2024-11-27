package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoGames {

    private final List<LottoNumbers> games;

    public AutoGames(List<LottoNumbers> games) {
        this.games = games;
    }

    public AutoGames(int money) {
        int numberOfGames = money / LottoNumbers.PRICE;

        List<LottoNumbers> games = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            games.add(new LottoNumbers());
        }

        this.games = Collections.unmodifiableList(games);
    }

    public int count() {
        return games.size();
    }

    public List<Rank> checkResult(LottoNumbers winner, LottoNumber bonusLottoNumber) {
        return games.stream()
                .map(game -> Rank.of(
                        game.countIdenticalLottoNumberSet(winner),
                        game.contains(bonusLottoNumber)
                ))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return games.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
    }
}
