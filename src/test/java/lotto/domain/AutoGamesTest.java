package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AutoGamesTest {

    @ParameterizedTest
    @CsvSource({"999, 0", "1000, 1", "20000, 20"})
    void 구매_금액에_맞는_갯수의_게임을_생성한다(int money, int expected) {
        AutoGames automaticGames = new AutoGames(money);

        assertThat(automaticGames.count()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("gamesAndRank")
    void 모든_게임의_등수를_반환한다(List<LottoNumbers> lottoNumbers, Rank expected) {
        LottoNumbers winner = new LottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(7);

        AutoGames automaticGames = new AutoGames(lottoNumbers);
        List<Rank> result = automaticGames.checkResult(winner, bonusLottoNumber);

        assertThat(result).hasSize(automaticGames.count());
        assertThat(result.get(0)).isEqualTo(expected);
    }

    private static Stream<Arguments> gamesAndRank() {
        return Stream.of(
                arguments(List.of(new LottoNumbers(1, 2, 3, 4, 5, 6)), Rank.FIRST),
                arguments(List.of(new LottoNumbers(1, 2, 3, 4, 5, 7)), Rank.SECOND),
                arguments(List.of(new LottoNumbers(1, 2, 3, 4, 5, 8)), Rank.THIRD),
                arguments(List.of(new LottoNumbers(1, 2, 3, 4, 7, 8)), Rank.FOURTH),
                arguments(List.of(new LottoNumbers(1, 2, 3, 7, 8, 9)), Rank.FIFTH),
                arguments(List.of(new LottoNumbers(1, 2, 7, 8, 9, 10)), Rank.NONE)
        );
    }


    @Test
    @DisplayName("toString()은 [번호, 번호]\n[번호, 번호] 형식의 문자열을 반환한다.")
    void toString은_전체_게임의_로또번호를_반환한다() {
        LottoNumbers game1 = new LottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumbers game2 = new LottoNumbers(7, 8, 9, 10, 11, 12);
        LottoNumbers game3 = new LottoNumbers(13, 14, 15, 16, 17, 18);
        AutoGames games = new AutoGames(List.of(game1, game2, game3));

        String[] toStringArray = {game1.toString(), game2.toString(), game3.toString()};
        String expected = String.join("\n", toStringArray);

        assertThat(games).hasToString(expected);
    }
}