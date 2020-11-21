package com.st.recommender.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class AlgorithmMapping {
        private AlgorithmMapping() {}

        public static Map<BaseEnum, BaseEnum> MAPPING = new HashMap<>();
        static {
            // als
            MAPPING.put(AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY, AlgorithmEnum.ALS);
        }
    }
}
