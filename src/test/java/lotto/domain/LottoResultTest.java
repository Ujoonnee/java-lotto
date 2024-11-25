package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static lotto.domain.Rank.FIFTH;
import static lotto.domain.Rank.FIRST;
import static lotto.domain.Rank.FOURTH;
import static lotto.domain.Rank.NONE;
import static lotto.domain.Rank.SECOND;
import static lotto.domain.Rank.THIRD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LottoResultTest {

    @ParameterizedTest
    @MethodSource("frequencyAndEarningRate")
    void 전체_게임에_대한_수익률을_구할_수_있다(int fifth, int none, double expected) {
        LottoResult lottoResult = new LottoResult(Map.of(
                FIRST, 0,
                SECOND, 0,
                THIRD, 0,
                FOURTH, 0,
                FIFTH, fifth,
                NONE, none
        ));

        assertThat(lottoResult.getEarningRate()).isEqualTo(expected);
    }

    private static Stream<Arguments> frequencyAndEarningRate() {
        double twice = 2.00;
        double breakEven = 1.00;
        double twoThird = 0.67;
        double half = 0.50;
        double oneThird = 0.33;

        return Stream.of(
                arguments(2, 3, twice),
                arguments(1, 4, breakEven),
                arguments(2, 13, twoThird),
                arguments(1, 9, half),
                arguments(1, 14, oneThird)
        );
    }

    @ParameterizedTest
    @MethodSource("rankAndFrequency")
    void 등수의_빈도를_반환한다(Rank rank, Integer expected) {
        LottoResult lottoResult = new LottoResult(Map.of(
                FIRST, 1,
                SECOND, 2,
                THIRD, 3,
                FOURTH, 4,
                FIFTH, 5,
                NONE, 6
        ), 0);

        assertThat(lottoResult.of(rank)).isEqualTo(expected);
    }

    private static Stream<Arguments> rankAndFrequency() {
        return Stream.of(
                Arguments.arguments(FIRST, 1),
                Arguments.arguments(SECOND, 2),
                Arguments.arguments(THIRD, 3),
                Arguments.arguments(FOURTH, 4),
                Arguments.arguments(FIFTH, 5),
                Arguments.arguments(NONE, 6)
        );
    }

}