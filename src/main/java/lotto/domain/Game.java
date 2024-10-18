package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    public static final int PRICE = 1000;
    public static final int NUMBER_OF_LOTTONUMBER = 6;

    private final List<LottoNumber> lottoNumbers;

    public Game(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicateNumber(numbers);

        this.lottoNumbers = toLottoNumberList(numbers);
    }

    public Game(String input) {
        validateEmptyInput(input);
        List<Integer> numbers = toIntegerList(input);

        validateCount(numbers);
        validateDuplicateNumber(numbers);

        this.lottoNumbers = toLottoNumberList(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6) {
            throw new IllegalArgumentException("6개의 로또번호가 필요합니다.");
        }
    }

    private void validateDuplicateNumber(List<Integer> lottoNumberList) {
        Set<Integer> lottoNumberSet = new HashSet<>(lottoNumberList);

        if (lottoNumberSet.size() != lottoNumberList.size()) {
            throw new IllegalArgumentException("중복된 로또번호를 사용할 수 없습니다.");
        }
    }

    private void validateEmptyInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("당첨번호를 입력해 주세요.");
        }
    }

    private List<Integer> toIntegerList(String input) {
        try {
            String[] split = input.split(",");

            return Arrays.stream(split)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private List<LottoNumber> toLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public int countIdenticalLottoNumber(Game that) {
        Set<LottoNumber> control = new HashSet<>(this.lottoNumbers);
        Set<LottoNumber> compare = new HashSet<>(that.lottoNumbers);
        compare.addAll(control);

        int difference = compare.size() - control.size();

        return control.size() - difference;
    }

    @Override
    public String toString() {
        String[] numbers = lottoNumbers.stream()
                .map(LottoNumber::toString)
                .toArray(String[]::new);

        return "[" + String.join(", ", numbers) + "]";
    }
}

