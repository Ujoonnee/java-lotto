package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbers {

    public static final int NUMBER_OF_LOTTO_NUMBER = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        this.lottoNumbers = numbers.stream()
                .limit(NUMBER_OF_LOTTO_NUMBER)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public LottoNumbers(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicateNumber(numbers);

        this.lottoNumbers = toLottoNumberList(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers == null || numbers.size() != NUMBER_OF_LOTTO_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OF_LOTTO_NUMBER + "개의 로또번호가 필요합니다.");
        }
    }

    private void validateDuplicateNumber(List<Integer> lottoNumberList) {
        Set<Integer> lottoNumberSet = new HashSet<>(lottoNumberList);

        if (lottoNumberSet.size() != lottoNumberList.size()) {
            throw new IllegalArgumentException("중복된 로또번호를 사용할 수 없습니다.");
        }
    }

    private List<LottoNumber> toLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    public int countIdenticalLottoNumber(LottoNumbers that) {
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

