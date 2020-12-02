package com.st.recommender.service.common;

import com.st.recommender.constants.NormalizationEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NormalizeFuncUtils {

    public static BiFunction<Double, double[], double[]> findNormalizeFunc(NormalizationEnum normalizationEnum) {
        switch (normalizationEnum) {
            case MAX_MIN_RANGE:
                return NormalizeFuncUtils::maxMinRangeRescale;
            case Z_SCORE:
                return NormalizeFuncUtils::zScore;
            case MODE_DISTANCE:
            default:
                return NormalizeFuncUtils::modeDistance;
        }
    }

    public static Function<List<MutablePair<String, Double>>, Map<String, List<MutablePair<String, Double>>>> findItemsNormalizeFunc(NormalizationEnum normalizationEnum) {
        switch (normalizationEnum) {
            case GROUP_AVG:
                return NormalizeFuncUtils::groupAverage;
            case GLOBAL_AVG:
            default:
                return NormalizeFuncUtils::globalAverage;
        }
    }

    private static Map<String, List<MutablePair<String, Double>>> groupAverage(List<MutablePair<String, Double>> items) {
        Map<String, List<MutablePair<String, Double>>> result = new ConcurrentHashMap<>();
        if (CollectionUtils.isEmpty(items)) {
            return result;
        }

        Map<String, List<MutablePair<String, Double>>> groups = items.stream().collect(Collectors.groupingBy(MutablePair::getKey));
        groups.forEach((k, v) -> {
            List<Double> itemsToKey = CommonFuncUtils.itemsToDoubleList(v, MutablePair::getValue);
            MutablePair<Double, Double> minMax = CommonFuncUtils.maxMinItemFinder(itemsToKey);
            Double max = minMax.getValue();
            List<MutablePair<String, Double>> outList = (max.equals(0D)) ? v :
                    v.stream().map(pair -> MutablePair.of(pair.getKey(), pair.getValue() / max))
                            .collect(Collectors.toList());
            result.put(k, outList);
        });
        return result;
    }

    private static Map<String, List<MutablePair<String, Double>>> globalAverage(List<MutablePair<String, Double>> items) {
        Map<String, List<MutablePair<String, Double>>> result = new ConcurrentHashMap<>();
        if (CollectionUtils.isEmpty(items)) {
            return result;
        }

        List<Double> itemsToKey = CommonFuncUtils.itemsToDoubleList(items, MutablePair::getValue);
        MutablePair<Double, Double> minMax = CommonFuncUtils.maxMinItemFinder(itemsToKey);
        Double max = minMax.getValue();

        Map<String, List<MutablePair<String, Double>>> groups = items.stream().collect(Collectors.groupingBy(MutablePair::getKey));
        if (max.equals(0D)) {
            return groups;
        }

        groups.forEach((k, v) -> {
            List<MutablePair<String, Double>> outList =
                    v.stream().map(pair -> MutablePair.of(pair.getKey(), pair.getValue() / max))
                            .collect(Collectors.toList());
            result.put(k, outList);
        });
        return result;
    }

    public static double[] maxMinRangeRescale(double range, double[] doubleArray) {
        List<Double> items = CommonFuncUtils.doubleArrayToList(doubleArray);
        int size = items.size();

        MutablePair<Double, Double> minMax = CommonFuncUtils.maxMinItemFinder(items);
        Double min = minMax.getLeft();
        Double max = minMax.getRight();
        double delta = (max.equals(min)) ? 1 : max - min;

        double[] result = new double[size];
        for (int i = 0; i < size; i ++) {
            result[i] = buildRescaleScore(range, items.get(i), min,delta);
        }

        return result;
    }

    public static Double buildRescaleScore(Double rate, Double score, Double min, Double delta) {
        return rate * (score - min) / delta;
    }

    public static double[] zScore(double range, double[] doubleArray) {
        List<Double> items = CommonFuncUtils.doubleArrayToList(doubleArray);
        int size = items.size();

        MutablePair<Double, Double> standardDeviationWithAverage = CommonFuncUtils.standardDeviationWithAverage(items);
        double avg = standardDeviationWithAverage.getLeft();
        double standardDeviation = standardDeviationWithAverage.getRight();

        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = (doubleArray[i] - avg) / standardDeviation;
        }

        return result;
    }

    public static double[] modeDistance(double range, double[] doubleArray) {
        List<Double> items = CommonFuncUtils.doubleArrayToList(doubleArray);
        int size = items.size();

        double mD = CommonFuncUtils.modeDistance(items);
        double norm = (0 == mD) ? 1 : mD;

        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = (doubleArray[i] - norm);
        }

        return result;
    }
}
