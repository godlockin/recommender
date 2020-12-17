package com.st.recommender.service.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@Slf4j
@Component
public class CommonFuncUtils {

    public static <T> double[] itemsToDoubleArray(List<T> items, Function<T, Double> doublePicker) {

        return streamToT(items, doublePicker, stream -> stream.mapToDouble(i -> i).toArray()).getValue();
    }

    public static <T> List<Double> itemsToDoubleList(List<T> items, Function<T, Double> doublePicker) {

        return streamToT(items, doublePicker, stream -> stream.collect(Collectors.toList())).getValue();
    }

    public static double[] doubleListToArray(List<Double> doubleList) {
        return itemsToDoubleArray(doubleList, Double::doubleValue);
    }

    public static List<Double> doubleArrayToList(double[] doubleArray) {
        return DoubleStream.of(doubleArray).boxed().collect(Collectors.toList());
    }

    public static MutablePair<Double, Double> maxMinItemFinder(List<Double> items) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (Double item : items) {
            min = min > item ? item : min;
            max = max < item ? item : max;
        }
        return MutablePair.of(min, max);
    }

    public static MutablePair<Double, Double> standardDeviationWithAverage(List<Double> items) {
        double avg = absAverage(items);
        double avgSquaresSum = streamToT(items, Double::doubleValue, stream -> stream.mapToDouble(x -> Math.pow((x - avg), 2)).sum()).getValue();
        double standardDeviation = Math.sqrt(avgSquaresSum);
        return MutablePair.of(avg, standardDeviation);
    }

    public static double absAverage(List<Double> items) {
        MutablePair<Integer, Double> sumCount = sumCount(items);
        return 0 < sumCount.getKey() ? sumCount.getValue() / sumCount.getKey() : 1;
    }

    public static MutablePair<Integer, Double> sumCount(List<Double> items) {
        return streamToT(items, Double::doubleValue, stream -> stream.mapToDouble(x -> x).sum());
    }

    public static Boolean scoreFilter(Double score) {
        return Objects.nonNull(score) && !(score.isNaN() || score.isInfinite());
    }

    public static double modeDistance(List<Double> items) {
        return Math.sqrt(squaresSum(items));
    }

    public static double squaresSum(List<Double> items) {
        return streamToT(items, Double::doubleValue, stream -> stream.mapToDouble(x -> Math.pow(x, 2)).sum()).getValue();
    }

    public static <T, R> MutablePair<Integer, R> streamToT(List<T> items, Function<T, Double> doublePicker, Function<Stream<Double>, R> streamConverter) {

        AtomicInteger counter = new AtomicInteger(0);
        Stream<Double> stream = CollectionUtils.isEmpty(items) ? Stream.empty() :
                items.stream()
                        .map(doublePicker)
                        .filter(CommonFuncUtils::scoreFilter)
                        .peek(item -> counter.incrementAndGet());

        return MutablePair.of(counter.intValue(), streamConverter.apply(stream));
    }

}
