package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class GameTest {

    @Nested
    @DisplayName("직접 로또번호를 입력하는 경우")
    class ListConstructor {

        @Test
        void 한_게임에_로또번호는_6개다() {
            List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

            assertThatNoException().isThrownBy(() -> new Game(list));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 5, 7, 10})
        void 한_게임에_로또번호는_6개다(int count) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                list.add(i);
            }

            assertThatIllegalArgumentException().isThrownBy(() -> new Game(list))
                    .withMessage("6개의 로또번호가 필요합니다.");
        }

        @Test
        void 한_게임에_중복된_로또번호가_있으면_안된다() {
            List<Integer> list = List.of(1, 1, 2, 3, 4, 5);

            assertThatIllegalArgumentException().isThrownBy(() -> new Game(list))
                    .withMessage("중복된 로또번호를 사용할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("로또번호 생성기를 이용하는 경우")
    class GeneratorConstructor {

        @Test
        void 한_게임에_로또번호는_6개다() {
            RandomNumberGenerator generator = new RandomNumberGenerator(new Random());
            List<Integer> list = generator.generate(6, 45);

            assertThatNoException().isThrownBy(() -> new Game(list));
        }
    }
}
