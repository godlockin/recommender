package com.st.recommender.service.common;

import com.st.recommender.constants.DistanceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Slf4j
@Component
public class DistanceFuncUtils {

    public static BiFunction<double[], double[], Double> findSimilarityFunc(DistanceEnum distanceEnum) {
        switch (distanceEnum) {
            case EUCLIDEAN_DISTANCE:
                return DistanceFuncUtils::euclideanDistance;
            case MANHATTAN_DISTANCE:
                return DistanceFuncUtils::manhattanDistance;
            case COSINE:
            default:
                return DistanceFuncUtils::cosine;
        }
    }

    public static double euclideanDistance(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            return 0D;
        }

        double sum = 0D;
        int size = vector1.length;
        for (int i = 0; i < size; i++) {
            sum += Math.pow((vector1[i] - vector2[i]), 2);
        }

        return Math.sqrt(sum);
    }

    public static double manhattanDistance(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            return 0D;
        }

        double sum = 0D;
        int size = vector1.length;
        for (int i = 0; i < size; i++) {
            sum += Math.abs((vector1[i] - vector2[i]));
        }

        return sum;
    }

    public static double cosine(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            return 0D;
        }

        double numerator = 0D;
        double denominatorV1 = 0D;
        double denominatorV2 = 0D;

        int size = vector1.length;
        for (int i = 0; i < size; i++) {
            double v1 = vector1[i];
            double v2 = vector2[i];
            numerator += v1 * v2;
            denominatorV1 += Math.pow(v1, 2);
            denominatorV2 += Math.pow(v2, 2);
        }

        double denominator = Math.sqrt(denominatorV1) * Math.sqrt(denominatorV2) + 1;

        return numerator / denominator;
    }

}
