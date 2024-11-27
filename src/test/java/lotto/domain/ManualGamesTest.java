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

class ManualGamesTest {

    @ParameterizedTest
    @CsvSource({"999, 0", "1000, 1", "20000, 20"})
    void 구매_금액에_맞는_갯수의_게임을_생성한다(int money, int expected) {
        ManualGames manualGames = new ManualGames(money);

        assertThat(manualGames.count()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("gamesAndRank")
    void 모든_게임의_등수를_반환한다(List<String> lottoNumbers, Rank expected) {
        LottoNumbers winner = new LottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(7);

        ManualGames manualGames = new ManualGames(lottoNumbers);
        List<Rank> result = manualGames.checkResult(winner, bonusLottoNumber);

        assertThat(result).hasSize(manualGames.count());
        assertThat(result.get(0)).isEqualTo(expected);
    }

    private static Stream<Arguments> gamesAndRank() {
        return Stream.of(
                arguments(List.of("1, 2, 3, 4, 5, 6"), Rank.FIRST),
                arguments(List.of("1, 2, 3, 4, 5, 7"), Rank.SECOND),
                arguments(List.of("1, 2, 3, 4, 5, 8"), Rank.THIRD),
                arguments(List.of("1, 2, 3, 4, 7, 8"), Rank.FOURTH),
                arguments(List.of("1, 2, 3, 7, 8, 9"), Rank.FIFTH),
                arguments(List.of("1, 2, 7, 8, 9, 10"), Rank.NONE)
        );
    }


    @Test
    @DisplayName("toString()은 [번호, 번호]\n[번호, 번호] 형식의 문자열을 반환한다.")
    void toString은_전체_게임의_로또번호를_반환한다() {
        String input1 = "1, 2, 3, 4, 5, 6";
        String input2 = "7, 8, 9, 10, 11, 12";

        ManualGames games = new ManualGames(List.of(input1, input2));

        LottoNumbers game1 = new LottoNumbers(input1);
        LottoNumbers game2 = new LottoNumbers(input2);

        assertThat(games).hasToString(game1 + "\n" + game2);
    }
}