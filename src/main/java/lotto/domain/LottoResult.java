package lotto.domain;

import java.util.Map;

public class LottoResult {

    private final RankResult rankResult;
    private final double earningRate;

    public LottoResult(RankResult rankResult, double earningRate) {
        this.rankResult = rankResult;
        this.earningRate = earningRate;
    }

    public LottoResult(Map<Rank, Integer> rankFrequency, double earningRate) {
        this(new RankResult(rankFrequency), earningRate);
    }

    public LottoResult(Map<Rank, Integer> rankFrequency) {
        this(new RankResult(rankFrequency), calculateEarningRate(rankFrequency));
    }

    private static double calculateEarningRate(Map<Rank, Integer> rankFrequency) {
        Long prize = calculatePrize(rankFrequency);
        Integer numberOfGames = calculateNumberOfGames(rankFrequency);

        return roundToTwoDecimals((double) prize / numberOfGames / LottoNumbers.PRICE);
    }

    private static Integer calculateNumberOfGames(Map<Rank, Integer> rankFrequency) {
        return rankFrequency.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    private static Long calculatePrize(Map<Rank, Integer> rankFrequency) {
        return rankFrequency.entrySet()
                .stream()
                .map(entry -> {
                    Rank rank = entry.getKey();
                    Integer count = entry.getValue();
                    return (long) rank.wins() * count;
                })
                .reduce(0L, Long::sum);
    }

    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public Integer of(Rank rank) {
        return rankResult.of(rank);
    }

    public double getEarningRate() {
        return earningRate;
    }
}
